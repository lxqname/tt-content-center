package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.common.domain.vo.LinearTreeVo;
import com.deepexi.content.domain.dto.EnterpriseZoneDto;
import com.deepexi.content.domain.eo.EnterpriseZone;
import com.deepexi.content.domain.vo.EnterpriseZoneVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.Mapping;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lufeng
 */
@Mapper
public interface EnterpriseZoneMapper extends BaseMapper<EnterpriseZone> {
    /**
     * 企业专区列表
     * @param eo
     * @param tenantId
     * @return
     */
    List<EnterpriseZoneVo> findList(@Param("eo") EnterpriseZoneDto eo, @Param("tenantId") String tenantId);


    /**
     * 根据id删除企业专区
     * @param pks
     * @return
     */
    int deleteByIds(@Param("pks") String... pks);

    /**
     * 根据企业专区名称获取企业专区
     * @param name
     * @param tenantId
     * @return
     */
    EnterpriseZone getByName(@Param("name") String name, @Param("tenantId") String tenantId);

    /**
     * 获取楼层树
     *
     * @param dto
     * @param tenantId
     * @return
     */
    List<LinearTreeVo> getLinearTree(@Param("dto") EnterpriseZoneDto dto, @Param("tenantId") String tenantId);

    /**
     *获取最大权重
     * @param tenantId
     * @return
     */
    Integer getMaxWeight(@Param("tenantId") String tenantId);

    /**
     * 排序全部加一处理
     * @param weight 起始值
     * @param tenantId
     */
    void addWeight(@Param("weight") Integer weight,@Param("tenantId")  String tenantId);


    /**
     * 增加粉丝数
     */
    int addUvCount(@Param("id") String id);
}
