package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.content.domain.eo.BannerManageSetRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hongchungen
 * @date 2019/09/27 9:57
 */
@Mapper
public interface BannerManageSetRelationMapper extends BaseMapper<BannerManageSetRelation> {

    /**
     * 通过manageId获取关系
     * @param manageId
     * @param tenantId
     * @return
     */
    List<String> getSetIdByManageId(@Param("manageId") String manageId, @Param("tenantId") String tenantId);

    /**
     * 删除
     * @param id
     * @param tenantId
     * @return
     */
    Boolean deleteByBannerSetId(@Param("id") String id, @Param("tenantId") String tenantId);
}
