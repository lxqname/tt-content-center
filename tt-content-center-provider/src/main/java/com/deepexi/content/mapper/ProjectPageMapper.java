package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.common.domain.vo.LinearTreeVo;
import com.deepexi.content.domain.dto.ProjectPageDto;
import com.deepexi.content.domain.eo.ProjectPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * @author hongchungen
 */
@Mapper
public interface ProjectPageMapper extends BaseMapper<ProjectPage> {

    /**
     * 获取专题页列表
     * @param eo
     * @param tenantId
     * @return
     */
    List<ProjectPage> findList(@Param("eo") ProjectPageDto eo, @Param("tenantId") String tenantId);

    /**
     * 根据id删除
     * @param pks
     * @return
     */
    int deleteByIds(@Param("pks") String pks);

    /**
     * 批量增加
     * @param eo
     * @return
     */
    int batchInsert(@Param("eo") List<ProjectPageDto> eo);

    /**
     * 批量更新
     * @param eo
     * @return
     */
    int batchUpdate(@Param("eo") List<ProjectPageDto> eo);

    /**
     * 根据名称获取专题页
     * @param name
     * @param tenantId
     * @return
     */
    ProjectPage getByName(@Param("name") String name, @Param("tenantId") String tenantId);

    /**
     * 获取专题树
     *
     * @param dto
     * @param tenantId
     * @return
     */
    List<LinearTreeVo> getLinearTree(@Param("dto") ProjectPageDto dto, @Param("tenantId") String tenantId);

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
}
