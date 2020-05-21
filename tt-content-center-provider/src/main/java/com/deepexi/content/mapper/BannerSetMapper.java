package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.common.enums.StatusEnum;
import com.deepexi.content.domain.dto.BannerQueryDto;
import com.deepexi.content.domain.dto.BannerSetDto;
import com.deepexi.content.domain.eo.BannerSet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hongchungen
 * @date 2019/09/26 16:34
 */
@Mapper
public interface BannerSetMapper extends BaseMapper<BannerSet> {

    /**
     * 根据相关setId获取banner列表
     * @param query
     * @param tenantId
     * @param pks
     * @return
     */
    List<BannerSet> findList(@Param("query") BannerQueryDto query, @Param("tenantId") String tenantId, @Param("pks") List<String> pks);

    /**
     * banner管理页获取列表
     * @param tenantId
     * @param pks
     * @return
     */
    List<BannerSet> findListAboutManage(@Param("tenantId") String tenantId, @Param("pks") List<String> pks);

    /**
     * 根据名称获取banner
     * @param name
     * @param tenantId
     * @return
     */
    BannerSet getByName(@Param("name") String name, @Param("tenantId") String tenantId);

    /**
     * 获取最大权重
     * @param tenantId
     * @return
     */
    Integer getMaxWeight(@Param("tenantId") String tenantId);

    /**
     * 获得排序比传入值小的第一个banner
     * @param weight
     * @param tenantId
     * @return
     */
    BannerSet getByWeightSmall(@Param("weight") Integer weight, @Param("tenantId") String tenantId);

    /**
     * 获得排序比传入值大的第一个banner
     * @param weight
     * @param tenantId
     * @return
     */
    BannerSet getByWeightBig(@Param("weight") Integer weight, @Param("tenantId") String tenantId);

    /**
     * 修改状态
     * @param id ID
     * @param statusEnum 状态
     * @return
     */
    Boolean updateStatus(@Param("id") String id, @Param("statusEnum") StatusEnum statusEnum);

    /**
     * H5前端会员
     * @param tenantId
     * @return
     */
    List<BannerSet> frontPage(@Param("tenantId") String tenantId);
}
