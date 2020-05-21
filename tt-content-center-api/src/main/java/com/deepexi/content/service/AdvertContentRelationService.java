package com.deepexi.content.service;

import com.deepexi.content.domain.dto.AdvertContentJsonDto;
import com.deepexi.content.domain.eo.AdvertContentRelation;
import com.deepexi.content.enums.AdvertTypeEnum;
import com.deepexi.content.enums.ContentSetTypeEnum;

import java.util.List;

/**
 * @author admin
 */
public interface AdvertContentRelationService {

    /**
     * 获取关系
     * @param setTypeId
     * @return
     */
    List<AdvertContentRelation> getBySetTypeId(String setTypeId);

    /**
     * 获取所关联的广告ID
     * @param setTypeId
     * @return
     */
    List<String> getAdvertIdBySetTypeId(String setTypeId);

    /**
     * 删除关系
     * @param advertTypeEnum
     * @param setTypeId
     * @return
     */
    Boolean deleteByAdvertTypeAndSetTypeId(AdvertTypeEnum advertTypeEnum, String setTypeId);

    /**
     * 创建关联关系和内容
     * @param setTypeEnum
     * @param setTypeId
     * @param jsonDtoList
     * @return
     */
    Boolean createAdvertContentRelation(ContentSetTypeEnum setTypeEnum, String setTypeId, List<AdvertContentJsonDto> jsonDtoList);

    /**
     * 批量删除关联根据id
     * @param ids
     * @return
     */
    Boolean deleteBatchIds(List<String> ids);

    /**
     * 根据设置类型id获取主键id
     * @param id
     * @return
     */
    List<String> getIdBySetTypeId(String id);

    /**
     * 根据bannerId获取广告类型
     * @param id
     * @return
     */
    List<Integer> getAdvertTypeBySetTypeId(String id);

}