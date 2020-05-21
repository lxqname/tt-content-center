package com.deepexi.content.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.deepexi.activity.service.ActivityService;
import com.deepexi.content.domain.dto.AdvertContentJsonDto;
import com.deepexi.content.domain.eo.AdvertContentRelation;
import com.deepexi.content.domain.eo.DivUrl;
import com.deepexi.content.enums.AdvertTypeEnum;
import com.deepexi.content.enums.ContentSetTypeEnum;
import com.deepexi.common.enums.ResultEnum;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.AdvertContentRelationMapper;
import com.deepexi.content.service.AdvertContentRelationService;
import com.deepexi.content.service.BannerSetService;
import com.deepexi.content.service.DivUrlService;
import com.deepexi.product.service.ProductService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

/**
 * @version V2.0
 * @author: LuFeng
 * @Package com.deepexi.content.service.impl
 * @Description:
 * @date: 2019/9/19 10:16
 */
@Component
@Service(version = "${demo.service.version}")
public class AdvertContentRelationServiceImpl implements AdvertContentRelationService {


    private static final Logger logger = LoggerFactory.getLogger(AdvertContentRelationServiceImpl.class);
    public static final int ZERO = 0;
    public static final int ONE = 1;
    /**
     * 自定义URL的ID被代替符和代替符
     */
    private static final String REGEX ="-";
    private static final String REPLACEMENT = "";

    @Autowired
    private AdvertContentRelationMapper advertContentRelationMapper;

    @Autowired
    private DivUrlService divUrlService;

    @Autowired
    private BannerSetService bannerSetService;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Reference(version = "${demo.service.version}",check = false)
    private ProductService productService;

    @Reference(version = "${demo.service.version}",check = false)
    private ActivityService activityService;

    @Override
    public List<AdvertContentRelation> getBySetTypeId(String setTypeId) {
        return advertContentRelationMapper.getBySetTypeId(setTypeId, appRuntimeEnv.getTenantId());
    }

    @Override
    public List<String> getAdvertIdBySetTypeId(String setTypeId) {
        return advertContentRelationMapper.getAdvertIdBySetTypeId(setTypeId, appRuntimeEnv.getTenantId());
    }

    @Override
    public Boolean deleteByAdvertTypeAndSetTypeId(AdvertTypeEnum advertTypeEnum, String setTypeId) {
        switch (advertTypeEnum){
            case BE_ITEM: case BE_ACTIVITY: case BE_NULL:
                deleteBySetTypeId(setTypeId);
                break;
            case BE_DIV_URL:
                List<String> advertIdList = getAdvertIdBySetTypeId(setTypeId);
                if (CollectionUtil.isEmpty(advertIdList)){
                    throw new ApplicationException(ResultEnum.getParameterError("操作失败"));
                }
                Boolean deleteFlag = divUrlService.delete(advertIdList.get(ZERO));
                if (!deleteFlag){
                    throw new ApplicationException(ResultEnum.getParameterError("操作失败"));
                }
                Boolean flag = deleteBySetTypeId(setTypeId);
                if (flag){
                    break;
                }
                throw new ApplicationException(ResultEnum.getParameterError("操作失败"));
            default:
        }
        return true;
    }

    private Boolean deleteBySetTypeId(String setTypeId){
        int result = advertContentRelationMapper.deleteBySetTypeId(setTypeId);
        if (result>ZERO){
            return true;
        }
        return false;
    }

    /**
     * 创建 设置与广告关系
     * @param setTypeEnum
     * @param setTypeId
     * @param jsonDtoList
     * @return
     */
    @Override
    public Boolean createAdvertContentRelation(ContentSetTypeEnum setTypeEnum, String setTypeId, List<AdvertContentJsonDto> jsonDtoList) {
        verificationAdvertContent(jsonDtoList, setTypeEnum, setTypeId);
        jsonDtoList.stream().forEach(jsonList->{
            switch (jsonList.getAdvertType()) {
                case BE_ITEM: case BE_ACTIVITY:
                    AdvertContentRelation relation = new AdvertContentRelation();
                    relation.setSetType(setTypeEnum.getValue());
                    relation.setSetTypeId(setTypeId);
                    relation.setWeight(jsonList.getWeight());
                    relation.setAdvertId(jsonList.getItemId());
                    relation.setAdvertType(jsonList.getAdvertType().getValue());
                    updateUrl(jsonList.getItemId(), jsonList.getImgUrl(), jsonList.getAdvertType());
                    advertContentRelationMapper.insert(relation);
                    break;
                case BE_DIV_URL:
                    AdvertContentRelation relation1 = new AdvertContentRelation();
                    relation1.setSetType(setTypeEnum.getValue());
                    relation1.setSetTypeId(setTypeId);
                    relation1.setAdvertType(jsonList.getAdvertType().getValue());
                    DivUrl divUrl=new DivUrl();
                    divUrl.setDivUrl(jsonList.getDivUrl());
                    String divUrlPk = UUID.randomUUID().toString().replaceAll(REGEX, REPLACEMENT);
                    if (null != divUrlPk && !REPLACEMENT.equals(divUrlPk)){
                        divUrl.setId(divUrlPk);
                    }else {
                        throw new ApplicationException(ResultEnum.getParameterError("自定义URL创建失败"));
                    }
                    Boolean result = divUrlService.create(divUrl);
                    if (result){
                        relation1.setAdvertId(divUrlPk);
                        int divInsert = advertContentRelationMapper.insert(relation1);
                        if (divInsert< ONE){
                            throw new ApplicationException(ResultEnum.getParameterError("自定义URL创建失败"));
                        }
                    }else {
                        throw new ApplicationException(ResultEnum.getParameterError("自定义URL创建失败"));
                    }
                    break;
                case BE_NULL:
                    jsonDtoList.stream().forEach(advert->{
                        AdvertContentRelation relation2 = new AdvertContentRelation();
                        relation2.setSetType(setTypeEnum.getValue());
                        relation2.setSetTypeId(setTypeId);
                        relation2.setAdvertId(advert.getItemId());
                        relation2.setAdvertType(advert.getAdvertType().getValue());
                        advertContentRelationMapper.insert(relation2);
                    });
                    break;
                default:
            }
        });
        return true;
    }

    /**
     * 更新图片
     */
    public Boolean updateUrl(String id, String url, AdvertTypeEnum advertType) {
        if (url != null) {
            if (advertType == AdvertTypeEnum.BE_ITEM) {
                productService.updateTopicImageUrl(id, url);
            }
            if (advertType == AdvertTypeEnum.BE_ACTIVITY) {
                activityService.updateTopicImageUrl(id, url);
            }
        }
        return true;
    }

    /**
     * 验证创建内容是否合法
     * @param eo
     */
    private void verificationAdvertContent(List<AdvertContentJsonDto> eo, ContentSetTypeEnum setTypeEnum, String setTypeId) {
        if (null == setTypeEnum){
            throw new ApplicationException(ResultEnum.getParameterError("关联设置类型不能为空"));
        }
        if (null == setTypeId){
            throw new ApplicationException(ResultEnum.getParameterError("关联设置ID不能为空"));
        }
        eo.stream().forEach(e->{
            if (null == e.getAdvertType()){
                throw new ApplicationException(ResultEnum.getParameterError("关联类型不能为空"));
            }
            switch (e.getAdvertType()) {
                case BE_ITEM:
                    if (StringUtils.isEmpty(e.getItemId())){
                        throw new ApplicationException(ResultEnum.getParameterError("关联商品ID不能为空"));
                    }
                    if (StringUtils.isEmpty(e.getWeight())){
                        throw new ApplicationException(ResultEnum.getParameterError("权重不能为空"));
                    }
                    break;
                case BE_ACTIVITY:
                    if (StringUtils.isEmpty(e.getItemId())){
                        throw new ApplicationException(ResultEnum.getParameterError("关联活动ID不能为空"));
                    }
                    break;
                case BE_DIV_URL:
                    if (StringUtils.isEmpty(e.getDivUrl())){
                        throw new ApplicationException(ResultEnum.getParameterError("自定义URL不允许为空"));
                    }
                    break;
                case BE_NULL:
                    break;
                default:
            }
        });
    }

    /**
     * 根据id删除
     * @param ids
     * @return
     */
    @Override
    public Boolean deleteBatchIds(List<String> ids) {
        for (String id: ids) {
            advertContentRelationMapper.deleteById(id);
        }
        return true;
    }

    @Override
    public List<String> getIdBySetTypeId(String id) {
        return advertContentRelationMapper.getIdBySetTypeId(id, appRuntimeEnv.getTenantId());
    }


    /**
     * 根据bannerId获取广告类型
     * @param id
     * @return
     */
    @Override
    public List<Integer> getAdvertTypeBySetTypeId(String id) {
        List<Integer> advertTypeBySetTypeId = advertContentRelationMapper.getAdvertTypeBySetTypeId(id, appRuntimeEnv.getTenantId());
        return advertTypeBySetTypeId;
    }
}