package com.deepexi.content.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.deepexi.activity.service.ActivityService;
import com.deepexi.common.enums.ResultEnum;
import com.deepexi.common.enums.StatusEnum;
import com.deepexi.content.domain.dto.*;
import com.deepexi.content.domain.eo.AdvertContentRelation;
import com.deepexi.content.domain.eo.TopicPage;
import com.deepexi.content.domain.vo.TopicPageDetailVo;
import com.deepexi.content.enums.ContentSetTypeEnum;
import com.deepexi.content.enums.StatusTypeEnum;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.TopicPageMapper;
import com.deepexi.content.service.AdvertContentRelationService;
import com.deepexi.content.service.AdvertContentService;
import com.deepexi.content.service.TopicPageService;
import com.deepexi.content.utils.NumberTypeUtils;
import com.deepexi.product.service.ProductService;
import com.deepexi.user.service.AccountService;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.klock.annotation.Klock;
import org.springframework.boot.autoconfigure.klock.annotation.KlockKey;
import org.springframework.boot.autoconfigure.klock.model.LockTimeoutStrategy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hongchunegn
 * @date 2019/10/20 20:44
 */
@Component
@Service(version = "${demo.service.version}")
public class TopicPageServiceImpl implements TopicPageService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectPageServiceImpl.class);

    @Autowired
    private TopicPageMapper topicPageMapper;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private AdvertContentRelationService advertContentRelationService;

    @Autowired
    private AdvertContentService advertContentService;

    @Reference(version = "${demo.service.version}",check = false)
    private ProductService productService;

    @Reference(version = "${demo.service.version}",check = false)
    private ActivityService activityService;

    @Reference(version = "${demo.service.version}", check = false)
    private AccountService accountService;

    /**
     * 条件分页查询
     * @param eo
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageBean findPage(TopicQueryDto eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<TopicPage> list = topicPageMapper.findList(eo, appRuntimeEnv.getTenantId());
        PageBean<TopicPage> topicPageStateDtoPageBean = new PageBean<>(list);
        List<TopicPageStateDto> topicPageStateDtoList = new ArrayList<>();
        for (TopicPage topicPage: list) {
            TopicPageStateDto topicPageStateDto = new TopicPageStateDto();
            BeanUtils.copyProperties(topicPage, topicPageStateDto);
            topicPageStateDto.setCreatedBy(accountService.getUsernameById(topicPage.getCreatedBy()));
            long startTime = topicPage.getStartTime().getTime();
            long endTime = topicPage.getEndTime().getTime();
            long date = System.currentTimeMillis();
            if (date > endTime || NumberTypeUtils.ONE == topicPage.getStatus()) {
                topicPageStateDto.setState(StatusTypeEnum.LOSE_EFFICACY.getValue());
            } else if (date < startTime) {
                topicPageStateDto.setState(StatusTypeEnum.TU_BE_EFFECTIVE.getValue());
            } else {
                topicPageStateDto.setState(StatusTypeEnum.RUNNING.getValue());
            }
            topicPageStateDtoList.add(topicPageStateDto);
        }
        PageBean<TopicPageStateDto> topicPageStateDtoPageBean1 = new PageBean<>();
        BeanUtil.copyProperties(topicPageStateDtoPageBean, topicPageStateDtoPageBean1);
        topicPageStateDtoPageBean1.setContent(topicPageStateDtoList);
        return topicPageStateDtoPageBean1;
    }

    /**
     * 创建话题
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime = NumberTypeUtils.FIVE)
    public Boolean topicPageCreate(TopicPageCreateDto dto) {
        TopicPage topicPage = new TopicPage();
        BeanUtils.copyProperties(dto, topicPage);
        topicPage.setCreatedBy(accountService.getLoginAccountIdByToken(appRuntimeEnv.getToken()));
        int insert = topicPageMapper.insert(topicPage);
        if (insert > NumberTypeUtils.ZERO) {
            return advertContentRelationService.createAdvertContentRelation(ContentSetTypeEnum.TOPIC_CONFIG, topicPage.getId(), dto.getJsonDtoList());
        }
        return false;
    }

    /**
     * 创建时判断专题页是否可以创建
     * @param eo
     */
    public void verificationCreateTopicPage(TopicPageCreateDto eo) {
        TopicPage topicPage = this.getByName(eo.getName());
        if (null != topicPage){
            throw new ApplicationException(ResultEnum.getParameterError("话题名称不允许重复"));
        }
    }

    /**
     * 通过名字获取话题
     * @param name
     * @return
     */
    @Override
    public TopicPage getByName(String name) {
        return topicPageMapper.getByName(name, appRuntimeEnv.getTenantId());
    }

    /**
     * 话题更新
     * @param pk
     * @param eo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime = NumberTypeUtils.FIVE)
    public Boolean update(@KlockKey String pk, TopicPageUpdateDto eo) {
        eo.setId(pk);
        List<String> list = advertContentRelationService.getIdBySetTypeId(eo.getId());
        TopicPage topicPage = new TopicPage();
        BeanUtils.copyProperties(eo, topicPage);
        //更新话题基本信息
        int result = topicPageMapper.updateById(topicPage);
        if (result > NumberTypeUtils.ZERO){
            //删除关系表，重新创建
            advertContentRelationService.deleteBatchIds(list);
            return advertContentRelationService.createAdvertContentRelation(ContentSetTypeEnum.TOPIC_CONFIG, topicPage.getId(), eo.getJsonDtoList());
        }
        return false;
    }

    /**
     * 判断是否允许更新，话题名称是否重复
     */
    public void verificationUpdateTopicPage(TopicPageUpdateDto eo) {
        TopicPage topicPage = this.getByName(eo.getName());
        if (null != topicPage && ObjectUtils.notEqual(topicPage.getId(), eo.getId())){
            throw new ApplicationException(ResultEnum.getParameterError("话题名称不允许重复"));
        }
    }

    /**
     * 删除话题
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime = NumberTypeUtils.FIVE)
    public Boolean delete(@KlockKey String id) {
        List<String> list = advertContentRelationService.getIdBySetTypeId(id);
        int result = topicPageMapper.deleteById(id);
        if(result > NumberTypeUtils.ZERO){
            return advertContentRelationService.deleteBatchIds(list);
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime = NumberTypeUtils.FIVE)
    public Boolean updateStatus(@KlockKey String id, StatusEnum statusEnum) {
        return topicPageMapper.updateStatus(id, statusEnum);
    }

    @Override
    public TopicPageDetailVo details(String pk) {
        TopicPage topicPage = topicPageMapper.selectById(pk);
        if (null == topicPage){
            throw new ApplicationException(ResultEnum.getParameterError("不存在此话题ID:"+pk));
        }
        TopicPageDetailVo vo = getTopicPageDetailVo(topicPage);
        return vo;
    }

    /**
     * 查询话题详情
     * @return
     */
    public TopicPageDetailVo getTopicPageDetailVo(TopicPage topicPage) {
        TopicPageDetailVo vo = new TopicPageDetailVo();
        BeanUtils.copyProperties(topicPage, vo);
        long startTime = topicPage.getStartTime().getTime();
        long endTime = topicPage.getEndTime().getTime();
        long date = System.currentTimeMillis();
        if (date > endTime || NumberTypeUtils.ONE == topicPage.getStatus()) {
            vo.setState(StatusTypeEnum.LOSE_EFFICACY.getValue());
        } else if (date < startTime) {
            vo.setState(StatusTypeEnum.TU_BE_EFFECTIVE.getValue());
        } else {
            vo.setState(StatusTypeEnum.RUNNING.getValue());
        }
        List<AdvertContentRelation> relationList = advertContentRelationService.getBySetTypeId(topicPage.getId());
        if (null == relationList){
            throw new ApplicationException(ResultEnum.getParameterError("查询不到相关信息"));
        }

        //商品和活动分开储存
        List<AdvertContentRelation> relation1 = new ArrayList<>();
        List<AdvertContentRelation> relation2 = new ArrayList<>();
        List<AdvertContentJsonDto> jsonDtoList1 = new ArrayList<>();
        List<AdvertContentJsonDto> jsonDtoList2 = new ArrayList<>();
        for (AdvertContentRelation list: relationList) {
            if (list.getAdvertType() == NumberTypeUtils.ONE) {
                relation1.add(list);
            }
            if (list.getAdvertType() == NumberTypeUtils.TWO) {
                relation2.add(list);
            }
        }
        List<AdvertContentJsonDto> advertContentJsonDtoList = advertContentService.productAdvert(relation1, jsonDtoList1);
        vo.setProductJsonDtoList(advertContentJsonDtoList);
        List<AdvertContentJsonDto> advertContentJsonDtoList1 = advertContentService.activityAdvert(relation2, jsonDtoList2);
        vo.setActivityJsonDtoList(advertContentJsonDtoList1);

        //规定返回前端数据，商品和活动中无数据时返回值为null
        if (relation1.size() == NumberTypeUtils.ZERO) {
            vo.setProductJsonDtoList(null);
        }
        if (relation2.size() == NumberTypeUtils.ZERO) {
            vo.setActivityJsonDtoList(null);
        }
        return vo;
    }
}
