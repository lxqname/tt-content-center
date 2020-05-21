package com.deepexi.content.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.deepexi.common.enums.ResultEnum;
import com.deepexi.common.enums.StatusEnum;
import com.deepexi.content.domain.dto.*;
import com.deepexi.content.domain.eo.AdvertContentRelation;
import com.deepexi.content.domain.eo.BannerSet;
import com.deepexi.content.domain.eo.DivUrl;
import com.deepexi.content.domain.vo.BannerFrontPageVo;
import com.deepexi.content.domain.vo.BannerSetDetailVo;
import com.deepexi.content.enums.AdvertTypeEnum;
import com.deepexi.content.enums.ContentSetTypeEnum;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.BannerSetMapper;
import com.deepexi.content.service.*;
import com.deepexi.content.utils.NumberTypeUtils;
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
 * @author hongchungen
 * @date 2019/09/27 15:30
 */
@Component
@Service(version = "${demo.service.version}")
public class BannerSetServiceImpl implements BannerSetService {

    private static final Logger logger = LoggerFactory.getLogger(BannerSetServiceImpl.class);

    @Autowired
    private BannerSetMapper bannerSetMapper;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private AdvertContentRelationService advertContentRelationService;

    @Autowired
    private AdvertContentService advertContentService;

    @Autowired
    private BannerManageSetRelationService bannerManageSetRelationService;

    @Autowired
    private DivUrlService divUrlService;

    @Reference(version = "${demo.service.version}", check = false)
    private AccountService accountService;

    @Override
    public PageBean findPage(BannerQueryDto eo, Integer page, Integer size, String id) {
        //查询bannerId
        List<String> setIdByManageId = bannerManageSetRelationService.getSetIdByManageId(id);
        PageHelper.startPage(page, size);
        List<BannerSet> list = bannerSetMapper.findList(eo, appRuntimeEnv.getTenantId(), setIdByManageId);
        //判断是否为空，为空则表示当前manage下没有banner页
        if (null == setIdByManageId || setIdByManageId.size() == NumberTypeUtils.ZERO) {
            return null;
        }
        PageBean<BannerSet> bannerSetDetailVoPageBean = new PageBean<>(list);
        List<BannerSetDetailVo> bannerSetDetailVos = new ArrayList<>();
        list.stream().forEach(bannerSet -> {
            BannerSetDetailVo bannerSetDetail = getBannerSetDetail(bannerSet.getId());
            bannerSetDetail.setCreatedBy(accountService.getUsernameById(bannerSet.getCreatedBy()));
            bannerSetDetailVos.add(bannerSetDetail);
        });
        PageBean<BannerSetDetailVo> bannerSetDetailVoPageBean1 = new PageBean<>();
        BeanUtil.copyProperties(bannerSetDetailVoPageBean, bannerSetDetailVoPageBean1);
        bannerSetDetailVoPageBean1.setContent(bannerSetDetailVos);
        return bannerSetDetailVoPageBean1;
    }


    @Override
    public List<BannerSet> findBySetIds(String tenantId, List<String> pks) {
        List<BannerSet> list = bannerSetMapper.findListAboutManage(appRuntimeEnv.getTenantId(), pks);
        return list;
    }

    @Override
    public BannerSet detail(String pk) {
        return bannerSetMapper.selectById(pk);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime = NumberTypeUtils.FIVE)
    public Boolean create(BannerSetCreateDto eo) {
        //判断是否符合创建条件
        verificationCreateBannerSet(eo);
        //创建时设置权重
        BannerSetCreateDto bannerSetCreateDto = weightSet(eo);
        BannerSet bannerSet = new BannerSet();
        BeanUtils.copyProperties(bannerSetCreateDto,bannerSet);
        bannerSet.setCreatedBy(accountService.getLoginAccountIdByToken(appRuntimeEnv.getToken()));

        //创建banner
        int result = bannerSetMapper.insert(bannerSet);
        if (result > NumberTypeUtils.ZERO){
            BannerSet bannerSet1 = bannerSetMapper.getByName(eo.getName(), appRuntimeEnv.getTenantId());
            String id = bannerSet1.getId();
            //创建banner管理与设置关系表
            bannerManageSetRelationService.create(id);
            return advertContentRelationService.createAdvertContentRelation(ContentSetTypeEnum.BANNER_IMG, bannerSet.getId(), eo.getJsonDtoList());
        }
        return false;
    }

    /**
     * 创建banner页时判断是否可以创建
     * @param eo
     */
    public void verificationCreateBannerSet(BannerSetCreateDto eo){
        BannerSet bannerSet = this.getByName(eo.getName());
        if (null != bannerSet){
            throw new ApplicationException(ResultEnum.getParameterError("banner名称不允许重复"));
        }
    }

    /**
     * 创建时设置权重
     * @param eo
     * @return
     */
    private BannerSetCreateDto weightSet(BannerSetCreateDto eo){
        BannerSet bannerSet = getMaxWeight();
        Integer maxWeight = bannerSet.getWeight();
        if (null == maxWeight){
            eo.setWeight(NumberTypeUtils.ONE);
        } else {
            eo.setWeight(maxWeight+NumberTypeUtils.ONE);
        }
        return eo;
    }

    @Override
    public BannerSet getByName(String name) {
        return bannerSetMapper.getByName(name,appRuntimeEnv.getTenantId());
    }

    @Override
    public BannerSet getMaxWeight() {
        Integer maxWeight = bannerSetMapper.getMaxWeight(appRuntimeEnv.getTenantId());
        BannerSet bannerSet = new BannerSet();
        bannerSet.setWeight(maxWeight);
        return bannerSet;
    }

    /**
     * 上升banner
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime = NumberTypeUtils.FIVE)
    public Boolean upBanner(String id) {
        BannerSet bannerSet1 = bannerSetMapper.selectById(id);
        BannerSet bannerSet2 = bannerSetMapper.getByWeightSmall(bannerSet1.getWeight(), appRuntimeEnv.getTenantId());
        Boolean aBoolean = this.moveWeight(bannerSet1, bannerSet2);
        if (aBoolean) {
            return true;
        }
        return false;
    }

    /**
     * 下降banner
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime = NumberTypeUtils.FIVE)
    public Boolean downBanner(String id) {
        BannerSet bannerSet1 = bannerSetMapper.selectById(id);
        BannerSet bannerSet2 = bannerSetMapper.getByWeightBig(bannerSet1.getWeight(), appRuntimeEnv.getTenantId());
        Boolean aBoolean = this.moveWeight(bannerSet1, bannerSet2);
        if (aBoolean) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime = NumberTypeUtils.FIVE)
    public Boolean updateStatus(@KlockKey String id, StatusEnum statusEnum) {
        return bannerSetMapper.updateStatus(id, statusEnum);
    }

    /**
     * banner页移动
     * @param m1
     * @param m2
     * @return
     */
    private Boolean moveWeight(BannerSet m1, BannerSet m2) {
        if (m1 == null || m2 == null){
            return false;
        }
        int sort = m1.getWeight();
        m1.setWeight(m2.getWeight());
        m2.setWeight(sort);
        bannerSetMapper.updateById(m1);
        bannerSetMapper.updateById(m2);
        return true;
    }

    /**
     * 查询banner详情
     * @param pk
     * @return
     */
    @Override
    public BannerSetDetailVo getBannerSetDetail(String pk) {
        BannerSet bannerSet = bannerSetMapper.selectById(pk);
        if (null == pk) {
            throw new ApplicationException(ResultEnum.getParameterError("没有此ID，查询为空"));
        }
        List<AdvertContentRelation> advertContentRelationList = advertContentRelationService.getBySetTypeId(pk);
        Integer advertType = advertContentRelationList.get(NumberTypeUtils.ZERO).getAdvertType();
        List<AdvertContentJsonDto> advertContentJsonDto = advertContentService.getAdvertContentJsonDto(AdvertTypeEnum.getEnumByValue(advertType), pk);
        BannerSetDetailVo bannerSetDetailVo = new BannerSetDetailVo();
        BeanUtils.copyProperties(bannerSet, bannerSetDetailVo);
        bannerSetDetailVo.setJsonDtoList(advertContentJsonDto);
        return bannerSetDetailVo;
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
    public Boolean update(@KlockKey String pk, BannerSetUpdateDto eo) {
        eo.setId(pk);
        verificationUpdateBannerSet(eo);
        List<AdvertContentRelation> relationList = advertContentRelationService.getBySetTypeId(pk);
        BannerSet bannerSet = new BannerSet();
        BeanUtils.copyProperties(eo, bannerSet);
        int result = bannerSetMapper.updateById(bannerSet);
        if (result > NumberTypeUtils.ZERO) {
            advertContentRelationService.deleteByAdvertTypeAndSetTypeId(AdvertTypeEnum.getEnumByValue(relationList.get(NumberTypeUtils.ZERO).getAdvertType()), bannerSet.getId());
            return advertContentRelationService.createAdvertContentRelation(ContentSetTypeEnum.BANNER_IMG, bannerSet.getId(),eo.getJsonDtoList());
        }
        return false;
    }

    /**
     * banner页更新时判断是否可以更新
     * @param eo
     */
    public void verificationUpdateBannerSet(BannerSetUpdateDto eo) {
        BannerSet bannerSet = this.getByName(eo.getName());
        if (null != bannerSet && ObjectUtils.notEqual(bannerSet.getId(), eo.getId())){
            throw new ApplicationException(ResultEnum.getParameterError("banner名称不允许重复"));
        }
    }

    /**
     * 删除
     * @param pk
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime = NumberTypeUtils.FIVE)
    public Boolean delete(@KlockKey String pk) {
        int result = bannerSetMapper.deleteById(pk);
        List<AdvertContentRelation> list = advertContentRelationService.getBySetTypeId(pk);
        if (result > NumberTypeUtils.ZERO) {
            bannerManageSetRelationService.deleteByBannerSetId(pk);
            return advertContentRelationService.deleteByAdvertTypeAndSetTypeId(AdvertTypeEnum.getEnumByValue(list.get(NumberTypeUtils.ZERO).getAdvertType()),pk);
        }
        return false;
    }

    /**
     * H5前端会员
     * @return
     */
    @Override
    public List<BannerFrontPageVo> frontPage() {
        //查询数据库所有banner
        List<BannerSet> bannerSetList = bannerSetMapper.frontPage(appRuntimeEnv.getTenantId());
        List<BannerFrontPageVo> bannerFrontPageVos = new ArrayList<>();

        for (BannerSet list: bannerSetList) {
            List<Integer> advertTypeBySetTypeId = advertContentRelationService.getAdvertTypeBySetTypeId(list.getId());
            if (AdvertTypeEnum.BE_DIV_URL.getValue().equals(advertTypeBySetTypeId.get(NumberTypeUtils.ZERO))) {
                BannerFrontPageVo bannerFrontPageVo = new BannerFrontPageVo();
                List<String> advertIdBySetTypeId = advertContentRelationService.getAdvertIdBySetTypeId(list.getId());
                DivUrl divUrl = divUrlService.getById(advertIdBySetTypeId.get(NumberTypeUtils.ZERO));
                bannerFrontPageVo.setId(list.getId());
                bannerFrontPageVo.setImgUrl(list.getImgUrl());
                bannerFrontPageVo.setThumbnail(list.getThumbnail());
                bannerFrontPageVo.setAbbreviate(list.getAbbreviate());
                bannerFrontPageVo.setDivUrl(divUrl.getDivUrl());
                bannerFrontPageVo.setTypeEnum(AdvertTypeEnum.BE_DIV_URL);
                bannerFrontPageVos.add(bannerFrontPageVo);
            }
            if (AdvertTypeEnum.BE_ITEM.getValue().equals(advertTypeBySetTypeId.get(NumberTypeUtils.ZERO))) {
                BannerFrontPageVo bannerFrontPageVo = new BannerFrontPageVo();
                setFrontPage(list, bannerFrontPageVo, AdvertTypeEnum.BE_ITEM, bannerFrontPageVos);
            }
            if (AdvertTypeEnum.BE_ACTIVITY.getValue().equals(advertTypeBySetTypeId.get(NumberTypeUtils.ZERO))) {
                BannerFrontPageVo bannerFrontPageVo = new BannerFrontPageVo();
                setFrontPage(list, bannerFrontPageVo, AdvertTypeEnum.BE_ACTIVITY, bannerFrontPageVos);
            }
            if (AdvertTypeEnum.BE_NULL.getValue().equals(advertTypeBySetTypeId.get(NumberTypeUtils.ZERO))) {
                BannerFrontPageVo bannerFrontPageVo = new BannerFrontPageVo();
                setFrontPage(list, bannerFrontPageVo, AdvertTypeEnum.BE_NULL, bannerFrontPageVos);
            }
        }
        return bannerFrontPageVos;
    }

    /**
     * H5会员端信息展示
     * @param list
     * @param bannerFrontPageVo
     */
    public void setFrontPage(BannerSet list, BannerFrontPageVo bannerFrontPageVo, AdvertTypeEnum advertTypeEnum, List<BannerFrontPageVo> bannerFrontPageVos) {
        bannerFrontPageVo.setId(list.getId());
        bannerFrontPageVo.setImgUrl(list.getImgUrl());
        bannerFrontPageVo.setThumbnail(list.getThumbnail());
        bannerFrontPageVo.setAbbreviate(list.getAbbreviate());
        bannerFrontPageVo.setTypeEnum(advertTypeEnum);
        List<AdvertContentJsonDto> advertContentJsonDto = advertContentService.getAdvertContentJsonDto(advertTypeEnum, list.getId());
        bannerFrontPageVo.setJsonDtoList(advertContentJsonDto);
        bannerFrontPageVos.add(bannerFrontPageVo);
    }
}