package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.content.domain.dto.BannerManageDto;
import com.deepexi.content.domain.eo.BannerManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * @author admin
 */
@Mapper
public interface BannerManageMapper extends BaseMapper<BannerManage> {

    /**
     * 查询列表
     * @param eo
     * @return
     */
    List<BannerManage> findList(@Param("eo") BannerManageDto eo);

    /**
     * 根据id删除
     * @param pks
     * @return
     */
    int deleteByIds(@Param("pks") String... pks);

    /**
     * 批量插入
     * @param eo
     * @return
     */
    int batchInsert(@Param("eo") List<BannerManageDto> eo);

    /**
     * 批量更新
     * @param eo
     * @return
     */
    int batchUpdate(@Param("eo") List<BannerManageDto> eo);
}
