package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.common.domain.vo.LinearTreeVo;
import com.deepexi.content.domain.dto.EnterpriseFloorPageDto;
import com.deepexi.content.domain.dto.FloorPageDto;
import com.deepexi.content.domain.eo.EnterpriseFloorPage;
import com.deepexi.content.domain.eo.FloorPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lxq
 */
@Mapper
public interface EnterpriseFloorPageMapper extends BaseMapper<EnterpriseFloorPage> {

    /**
     * 楼层页列表
     * @param eo
     * @param tenantId
     * @return
     */
    List<EnterpriseFloorPage> findList(@Param("eo") EnterpriseFloorPageDto eo, @Param("tenantId") String tenantId);

    /**
     * 根据id删除楼层页
     * @param pks
     * @return
     */
    int deleteByIds(@Param("pks") String... pks);

    /**
     * 根据楼层页名称获取楼层页
     * @param name
     * @param tenantId
     * @return
     */
    EnterpriseFloorPage getByName(@Param("name") String name, @Param("tenantId") String tenantId,@Param("organizationId")String organizationId);

    /**
     * 获取楼层树
     *
     * @param dto
     * @param tenantId
     * @return
     */
    List<LinearTreeVo> getLinearTree(@Param("dto") EnterpriseFloorPageDto dto, @Param("tenantId") String tenantId);

    /**
     *获取最大权重
     * @param tenantId
     * @return
     */
    Integer getMaxWeight(@Param("tenantId") String tenantId,@Param("organizationId")String organizationId);

    /**
     * 排序全部加一处理
     * @param weight 起始值
     * @param tenantId
     */
    void addWeight(@Param("weight") Integer weight, @Param("tenantId") String tenantId);
}
