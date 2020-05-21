package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.content.domain.eo.AdvertContentRelation;
import com.deepexi.content.enums.ContentSetTypeEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import scala.Int;

import java.util.List;

/**
 * @author admin
 */
@Mapper
public interface AdvertContentRelationMapper extends BaseMapper<AdvertContentRelation> {

    /**
     * 获取关系
     * @param setTypeId
     * @param tenantId
     * @return
     */
    List<AdvertContentRelation> getBySetTypeId(@Param("setTypeId") String setTypeId, @Param("tenantId") String tenantId);

    /**
     * 获取广告ID
     * @param setTypeId
     * @param tenantId
     * @return
     */
    List<String> getAdvertIdBySetTypeId(@Param("setTypeId") String setTypeId, @Param("tenantId") String tenantId);

    /**
     * 删除关系
     * @param setTypeId
     * @return
     */
    int deleteBySetTypeId(@Param("setTypeId") String setTypeId);

    /**
     * 获取主键id
     * @param id
     * @param tenantId
     * @return
     */
    List<String> getIdBySetTypeId(@Param("id") String id, @Param("tenantId") String tenantId);

    /**
     * 根据bannerId获取广告类型
     * @param id
     * @param tenantId
     * @return
     */
    List<Integer> getAdvertTypeBySetTypeId(@Param("id") String id, @Param("tenantId") String tenantId);
}
