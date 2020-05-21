package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.content.domain.dto.HotwordGoodslablesRelationDto;
import com.deepexi.content.domain.eo.HotwordGoodslablesRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface HotwordGoodslablesRelationMapper extends BaseMapper<HotwordGoodslablesRelation> {

    List<HotwordGoodslablesRelation> findList(@Param("eo") HotwordGoodslablesRelationDto eo);

    int deleteByIds(@Param("pks") List<String> pks);

    int batchInsert(@Param("eoi") List<HotwordGoodslablesRelationDto> eo);

    int batchUpdate(@Param("eou") List<HotwordGoodslablesRelationDto> eo);

    /**
     * 查询数据
     * @param hotWordId 热词id
     * @return 返回值
     */
    List<String> selectByHotWordId(String hotWordId);

    /**
     * 查询商品id
     * @param hotWordId 热词id
     * @return 返回值
     */
    List<String> selectGoodsLabelIdsByHotWordId(String hotWordId);

    /**
     * 删除数据
     * @param hotWordIds 热词id集合
     * @return 返回值
     */
    int deleteByHotWordIds(@Param("list")List<String> hotWordIds);
}
