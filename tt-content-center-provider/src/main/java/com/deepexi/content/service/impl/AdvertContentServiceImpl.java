package com.deepexi.content.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.deepexi.activity.domain.dto.ActivityDetailDto;
import com.deepexi.activity.service.ActivityService;
import com.deepexi.content.domain.dto.AdvertContentJsonAndActivityDetailDto;
import com.deepexi.content.domain.dto.AdvertContentJsonDto;
import com.deepexi.content.domain.eo.AdvertContentRelation;
import com.deepexi.content.domain.eo.DivUrl;
import com.deepexi.content.enums.AdvertTypeEnum;
import com.deepexi.common.enums.ResultEnum;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.service.AdvertContentRelationService;
import com.deepexi.content.service.AdvertContentService;
import com.deepexi.content.service.DivUrlService;
import com.deepexi.product.domain.dto.CouponEquityEnterpriseDto;
import com.deepexi.product.domain.dto.ProductPopupDto;
import com.deepexi.product.service.ProductService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version V2.0
 * @author: LuFeng
 * @Package com.deepexi.content.service.impl
 * @Description:
 * @date: 2019/9/19 10:16
 */
@Component
@Service(version = "${demo.service.version}")
public class AdvertContentServiceImpl implements AdvertContentService {

    //组合商品判断
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    //组合产品关联商户名后缀
    private static final String MORE = "等";
    private static final String PACKEGAMERCHANT = "个企业";

    private static final int THREE = 3;

    @Autowired
    private AdvertContentRelationService advertContentRelationService;

    @Autowired
    private DivUrlService divUrlService;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Reference(version = "${demo.service.version}",check = false)
    private ProductService productService;

    @Reference(version = "${demo.service.version}",check = false)
    private ActivityService activityService;

    @Override
    public List<ActivityDetailDto> getActivityDetailDto(String setTypeId) {
        List<AdvertContentRelation> relationList = advertContentRelationService.getBySetTypeId(setTypeId);
        ArrayList<ActivityDetailDto> list = new ArrayList<>();
        for (int i=0;i<THREE;i++){
            if (i<relationList.size()){
                String advertId = relationList.get(i).getAdvertId();
                ActivityDetailDto activityDetailDto = activityService.detail(advertId);
                list.add(activityDetailDto);
            }
        }
        return list;
    }

    /**
     * 获取广告内容
     * @param advertTypeEnum
     * @param id
     * @return
     */
    @Override
    public List<AdvertContentJsonDto> getAdvertContentJsonDto(AdvertTypeEnum advertTypeEnum, String id) {
        List<AdvertContentJsonDto> jsonDtoList = new ArrayList<>();
        List<AdvertContentRelation> relationList = advertContentRelationService.getBySetTypeId(id);
        if (CollectionUtil.isEmpty(relationList)){
            throw new ApplicationException(ResultEnum.getParameterError("查询不到相关信息"));
        }
        switch (advertTypeEnum){
            case BE_ITEM:
                productAdvert(relationList, jsonDtoList);
                break;
            case BE_ACTIVITY:
                activityAdvert(relationList, jsonDtoList);
                break;
            case BE_DIV_URL:
                relationList.stream().forEach(relation->{
                    AdvertContentJsonDto advertContentJsonDto = new AdvertContentJsonDto();
                    DivUrl divUrl = divUrlService.detail(relation.getAdvertId());
                    advertContentJsonDto.setDivUrl(divUrl.getDivUrl());
                    advertContentJsonDto.setAdvertType(advertTypeEnum);
                    jsonDtoList.add(advertContentJsonDto);
                });
                break;
            case BE_NULL:
                break;
            default:
        }
        return jsonDtoList;
    }

    /**
     * 获取广告所关联的商品
     * @param relationList
     * @param jsonDtoList
     */
    @Override
    public List<AdvertContentJsonDto> productAdvert(List<AdvertContentRelation> relationList, List<AdvertContentJsonDto> jsonDtoList) {
        relationList.stream().forEach(relation->{
            String advertId = relation.getAdvertId();
            List<String> advertIds = new ArrayList();
            advertIds.add(advertId);
            List<ProductPopupDto> items = productService.findPopupPage(advertIds);
            for (ProductPopupDto item:items) {
                AdvertContentJsonDto advertContentJsonDto = new AdvertContentJsonDto();
                advertContentJsonDto.setName(item.getName());
                advertContentJsonDto.setItemId(advertId);
                advertContentJsonDto.setAdvertType(AdvertTypeEnum.BE_ITEM);
                advertContentJsonDto.setImgUrl(item.getTopicImageUrl());
                List<List<String>> frontCategoryLinkList = item.getFrontCategoryLinkList();
                advertContentJsonDto.setFrontCategoryLinkList(frontCategoryLinkList);
                advertContentJsonDto.setDescription(item.getDescription());
                if (item.getCouponRelationDetail().getType() == TWO){
                    List<CouponEquityEnterpriseDto> couponList = item.getCouponRelationDetail().getCouponList();
                    List<String> recordName = new ArrayList<>();
                    for (CouponEquityEnterpriseDto dto:couponList) {
                        String merchantName = dto.getEnterpriseName();
                        recordName.add(merchantName);
                    }
                    List<String> merchantNameList = new ArrayList<>();
                    for (int i=ZERO;i<recordName.size();i++){
                        if (!merchantNameList.contains(recordName.get(i))){
                            merchantNameList.add(recordName.get(i));
                        }
                    }
                    if (merchantNameList.size()>ONE){
                        String packageMerchantName =merchantNameList.get(ZERO);
                        packageMerchantName = packageMerchantName + MORE + merchantNameList.size() + PACKEGAMERCHANT;
                        advertContentJsonDto.setBeMerchant(packageMerchantName);
                    }else {
                        advertContentJsonDto.setBeMerchant(merchantNameList.get(ZERO));
                    }
                }else if (item.getCouponRelationDetail().getType() == ONE){
                    advertContentJsonDto.setBeMerchant(item.getCouponRelationDetail().getEnterpriseName());
                }
                advertContentJsonDto.setWeight(relation.getWeight());
                jsonDtoList.add(advertContentJsonDto);
            }
        });
        return jsonDtoList;
    }

    /**
     * 获取广告关联的活动
     * @param relationList
     * @param jsonDtoList
     */
    @Override
    public List<AdvertContentJsonDto> activityAdvert(List<AdvertContentRelation> relationList, List<AdvertContentJsonDto> jsonDtoList) {
        relationList.stream().forEach(relation->{
            AdvertContentJsonDto advertContentJsonDto = new AdvertContentJsonDto();
            String advertId = relation.getAdvertId();
            ActivityDetailDto activityDetailDto = activityService.detail(advertId);
            advertContentJsonDto.setName(activityDetailDto.getName());
            advertContentJsonDto.setItemId(advertId);
            advertContentJsonDto.setImgUrl(activityDetailDto.getTopicImageUrl());
            advertContentJsonDto.setAdvertType(AdvertTypeEnum.BE_ACTIVITY);
            advertContentJsonDto.setType(String.valueOf(activityDetailDto.getType()));
            advertContentJsonDto.setActivityStartTime(activityDetailDto.getStartTime());
            advertContentJsonDto.setActivityEndTime(activityDetailDto.getEndTime());
            advertContentJsonDto.setDescription(activityDetailDto.getDescription());
            jsonDtoList.add(advertContentJsonDto);
        });
        return jsonDtoList;
    }

}
