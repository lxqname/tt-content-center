package com.deepexi.content.service;

import com.deepexi.activity.domain.dto.ActivityDetailDto;
import com.deepexi.content.domain.dto.AdvertContentJsonAndActivityDetailDto;
import com.deepexi.content.domain.dto.AdvertContentJsonDto;
import com.deepexi.content.domain.eo.AdvertContentRelation;
import com.deepexi.content.enums.AdvertTypeEnum;

import java.util.List;

/**
 * @author admin
 */
public interface AdvertContentService {

    /**
     * 获取活动详情集合
     * @param setTypeId
     * @return
     */
    List<ActivityDetailDto> getActivityDetailDto(String setTypeId);

    /**
     * 获得广告牌内容
     * @param advertTypeEnum
     * @param setTypeId
     * @return
     */
    List<AdvertContentJsonDto> getAdvertContentJsonDto(AdvertTypeEnum advertTypeEnum, String setTypeId);

    /**
     * 获取商品
     * @param relationList
     * @param jsonDtoList
     */
    List<AdvertContentJsonDto> productAdvert(List<AdvertContentRelation> relationList, List<AdvertContentJsonDto> jsonDtoList);

    /**
     * 获取活动
     * @param relationList
     * @param jsonDtoList
     * @return
     */
    List<AdvertContentJsonDto> activityAdvert(List<AdvertContentRelation> relationList, List<AdvertContentJsonDto> jsonDtoList);
}
