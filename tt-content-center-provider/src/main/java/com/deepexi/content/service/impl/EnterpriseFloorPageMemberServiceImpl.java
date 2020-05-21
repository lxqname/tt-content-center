package com.deepexi.content.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.deepexi.activity.domain.dto.ActivityDetailDto;
import com.deepexi.business.domain.vo.LoginBusinessInfoVo;
import com.deepexi.business.service.BusinessAccountService;
import com.deepexi.common.constant.Constants;
import com.deepexi.common.domain.vo.LinearTreeVo;
import com.deepexi.common.domain.vo.TreeVo;
import com.deepexi.common.enums.ResultEnum;
import com.deepexi.common.util.TreeUtils;
import com.deepexi.content.domain.dto.*;
import com.deepexi.content.domain.eo.AdvertContentRelation;
import com.deepexi.content.domain.eo.EnterpriseFloorPage;
import com.deepexi.content.domain.vo.EnterpriseFloorPageDetailVo;
import com.deepexi.content.enums.AdvertTypeEnum;
import com.deepexi.content.enums.ContentSetTypeEnum;
import com.deepexi.content.enums.MoveAdjacentTypeEnum;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.EnterpriseFloorPageMapper;
import com.deepexi.content.service.AdvertContentRelationService;
import com.deepexi.content.service.AdvertContentService;
import com.deepexi.content.service.EnterpriseFloorPageMemberService;
import com.deepexi.content.service.EnterpriseFloorPageService;
import com.deepexi.content.utils.NumberTypeUtils;
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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author lxq
 * @date 2019/09/25 15:51
 */
@Component
@Service(version = "${demo.service.version}")
public class EnterpriseFloorPageMemberServiceImpl implements EnterpriseFloorPageMemberService {

    private static final Logger logger = LoggerFactory.getLogger(EnterpriseFloorPageMemberServiceImpl.class);

    /**
     * 父级等级
     */
    public static final String PARENT_LABEL = "";

    /**
     * 楼层页最大数
     */
    private final static int FLOOR_MAX_NUM = 10;

    /**
     * 楼层页父级ID
     */
    private final static String FLOOR_PARENT_ID = "0";

    @Autowired
    private EnterpriseFloorPageMapper enterpriseFloorPageMapper;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private AdvertContentRelationService advertContentRelationService;

    @Reference(version = "${demo.service.version}", check = false)
    private BusinessAccountService businessAccountService;

    @Autowired
    private AdvertContentService advertContentService;

    @Override
    public PageBean findPage(EnterpriseFloorPageDto eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<EnterpriseFloorPage> list = enterpriseFloorPageMapper.findList(eo,appRuntimeEnv.getTenantId());
        return new PageBean<>(list);
    }

    @Override
    public List<EnterpriseFloorPageDetailVo> findAll(EnterpriseFloorPageDto eo) {
        List<EnterpriseFloorPage> list = enterpriseFloorPageMapper.findList(eo,appRuntimeEnv.getTenantId());
        return list.stream().map(t->{
            EnterpriseFloorPageDetailVo vo = new EnterpriseFloorPageDetailVo();
            BeanUtils.copyProperties(t,vo);
            List<AdvertContentRelation> relationList = advertContentRelationService.getBySetTypeId(t.getId());
            if (com.deepexi.util.CollectionUtil.isEmpty(relationList)){
                throw new ApplicationException(ResultEnum.getParameterError("查询不到相关信息"));
            }
            int advertType = relationList.get(NumberTypeUtils.ZERO).getAdvertType();
            vo.setAdvertTypeEnum(AdvertTypeEnum.getEnumByValue(advertType));
            List<ActivityDetailDto> activityDetailDto = advertContentService.getActivityDetailDto(t.getId());
            vo.setActivityDetailDto(activityDetailDto);
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public EnterpriseFloorPage detail(String pk){
        EnterpriseFloorPage floorPage = enterpriseFloorPageMapper.selectById(pk);
        return floorPage;
    }

    @Override
    public EnterpriseFloorPageDetailVo details(String pk) {
        EnterpriseFloorPage floorPage = enterpriseFloorPageMapper.selectById(pk);
        if (null == floorPage){
            throw new ApplicationException(ResultEnum.getParameterError("不存在此楼层页ID:"+pk));
        }
        //查询详情信息
        EnterpriseFloorPageDetailVo vo = getFloorPageDetailVo(floorPage);
        return vo;
    }

    /**
     * 查询楼层页详情信息
     * @param floorPage
     * @return
     */
    private EnterpriseFloorPageDetailVo getFloorPageDetailVo(EnterpriseFloorPage floorPage) {
        EnterpriseFloorPageDetailVo vo = new EnterpriseFloorPageDetailVo();
        BeanUtils.copyProperties(floorPage, vo);
        List<AdvertContentRelation> relationList = advertContentRelationService.getBySetTypeId(floorPage.getId());
        if (com.deepexi.util.CollectionUtil.isEmpty(relationList)){
            throw new ApplicationException(ResultEnum.getParameterError("查询不到相关信息"));
        }
        int advertType = relationList.get(NumberTypeUtils.ZERO).getAdvertType();
        vo.setAdvertTypeEnum(AdvertTypeEnum.getEnumByValue(advertType));
        List<ActivityDetailDto> activityDetailDto = advertContentService.getActivityDetailDto(floorPage.getId());
        vo.setActivityDetailDto(activityDetailDto);
        return vo;
    }

    /**
     * 创建
     * @param eo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime = NumberTypeUtils.FIVE)
    public Boolean create(EnterpriseFloorPageCreateDto eo) {
        verificationFloorConfig(eo);
        EnterpriseFloorPageCreateDto enterpriseFloorPageCreateDto = weightSet(eo);
        enterpriseFloorPageCreateDto.setpId(FLOOR_PARENT_ID);
        EnterpriseFloorPage floorPage = new EnterpriseFloorPage();
        BeanUtils.copyProperties(enterpriseFloorPageCreateDto, floorPage);
        int result = enterpriseFloorPageMapper.insert(floorPage);
        if (result > NumberTypeUtils.ZERO){
            return advertContentRelationService.createAdvertContentRelation(ContentSetTypeEnum.FLOOR_CONFIG, floorPage.getId(), eo.getJsonDtoList());
        }
        return false;
    }

    /**
     * 创建时判断楼层页是否可以创建
     * @param eo
     */
    private void verificationFloorConfig(EnterpriseFloorPageCreateDto eo) {
        EnterpriseFloorPage floorPage = this.getByName(eo.getName());
        if (null != floorPage){
            throw new ApplicationException(ResultEnum.getParameterError("楼层页名称不允许重复"));
        }
    }

    /**
     * 楼层页创建时设置权重
     * @param eo
     * @return
     */
    private EnterpriseFloorPageCreateDto weightSet(EnterpriseFloorPageCreateDto eo){
        EnterpriseFloorPage enterpriseFloorPage = getMaxWeight();
        Integer maxWeight = enterpriseFloorPage.getWeight();
        if (null == maxWeight){
            eo.setWeight(NumberTypeUtils.ONE);
        } else {
            eo.setWeight(maxWeight+NumberTypeUtils.ONE);
        }
        return eo;
    }

    /**
     * 根据名称获取楼层页
     * @param name
     * @return
     */
    @Override
    public EnterpriseFloorPage getByName(String name) {
        return enterpriseFloorPageMapper.getByName(name,appRuntimeEnv.getTenantId(),"tt");
    }

    /**
     * 更新
     * @param pk
     * @param eo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime = NumberTypeUtils.FIVE)
    public Boolean update(@KlockKey String pk, EnterpriseFloorPageUpdateDto eo) {
        eo.setId(pk);
        verificationUpdateFloorConfig(eo);
        EnterpriseFloorPage floorPage = new EnterpriseFloorPage();
        BeanUtils.copyProperties(eo, floorPage);
        int result = enterpriseFloorPageMapper.updateById(floorPage);
        if (result > NumberTypeUtils.ZERO){
            advertContentRelationService.deleteByAdvertTypeAndSetTypeId(AdvertTypeEnum.BE_ACTIVITY, floorPage.getId());
            return advertContentRelationService.createAdvertContentRelation(ContentSetTypeEnum.FLOOR_CONFIG, floorPage.getId(),eo.getJsonDtoList());
        }
        return false;
    }

    /**
     * 更新时判断楼层页是否可以更新
     * @param eo
     */
    private void verificationUpdateFloorConfig(EnterpriseFloorPageUpdateDto eo) {
        EnterpriseFloorPage floorPage = this.getByName(eo.getName());
        if (null != floorPage && ObjectUtils.notEqual(floorPage.getId(), eo.getId())){
            throw new ApplicationException(ResultEnum.getParameterError("楼层页名称不允许重复"));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime = NumberTypeUtils.FIVE)
    public Boolean delete(@KlockKey String pk) {
        int result = enterpriseFloorPageMapper.deleteByIds(pk);
        if (result > NumberTypeUtils.ZERO){
           return advertContentRelationService.deleteByAdvertTypeAndSetTypeId(AdvertTypeEnum.BE_ITEM, pk);
        }
        return false;
    }

    /**
     * 获取树结构
     * @param dto
     * @param parentId
     * @return
     */
    @Override
    public List<TreeVo> getTree(EnterpriseFloorPageDto dto, String parentId) {
        List<LinearTreeVo> linearTreeVoList = enterpriseFloorPageMapper.getLinearTree(dto, appRuntimeEnv.getTenantId());
        String parentLabel = PARENT_LABEL;
        EnterpriseFloorPage floorPage = this.detail(parentId);
        if (floorPage != null) {
            parentLabel = floorPage.getName();
        }
        List<TreeVo> treeVoList = TreeUtils.linearToTree(linearTreeVoList, parentId, parentLabel);
        return treeVoList;
    }

    /**
     * 获取楼层页树结构
     * @param dto
     * @return
     */
    @Override
    public List<TreeVo> getTreeByFloor(EnterpriseFloorPageDto dto) {
        if (dto == null) {
            dto = new EnterpriseFloorPageDto();
        }
        // 获取树形结构的楼层架构
        List<TreeVo> treeVoList = this.getTree(dto, Constants.DEFAULT_P_ID);
        if (CollectionUtil.isEmpty(treeVoList)) {
            return null;
        }
        return treeVoList;
    }

    /**
     * 获取最后一个权重
     * @return
     */
    @Override
    public EnterpriseFloorPage getMaxWeight() {
        Integer maxWeight = enterpriseFloorPageMapper.getMaxWeight(appRuntimeEnv.getTenantId(),"tt");
        EnterpriseFloorPage floorPage = new EnterpriseFloorPage();
        floorPage.setWeight(maxWeight);
        return floorPage;
    }

    /**
     * 楼层页拖拽
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime = NumberTypeUtils.FIVE)
    public Boolean move(MovePageDto dto) {
        EnterpriseFloorPage floorPage = this.getById(dto.getId());
        EnterpriseFloorPage locationFloorConfig = this.getById(dto.getLocationId());
        // 后往前移
        if (Objects.equals(MoveAdjacentTypeEnum.AFTER.getState(), dto.getType())) {
            floorPage.setWeight(locationFloorConfig.getWeight());
            enterpriseFloorPageMapper.addWeight(locationFloorConfig.getWeight(),appRuntimeEnv.getTenantId());

        } else if (Objects.equals(MoveAdjacentTypeEnum.BEFORE.getState(), dto.getType())) {
            // 前往后
            floorPage.setWeight(locationFloorConfig.getWeight()+NumberTypeUtils.ONE);
            enterpriseFloorPageMapper.addWeight(floorPage.getWeight(),appRuntimeEnv.getTenantId());

        }
        enterpriseFloorPageMapper.updateById(floorPage);
        return true;
    }

    @Override
    public EnterpriseFloorPage getById(String id) {
        EnterpriseFloorPage floorPage = enterpriseFloorPageMapper.selectById(id);
        if (null == floorPage){
            throw new ApplicationException(ResultEnum.getParameterError("楼层页ID错误，未找到对应信息:id="+id));
        }
        return floorPage;
    }
}