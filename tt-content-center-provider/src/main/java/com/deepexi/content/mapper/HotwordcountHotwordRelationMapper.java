package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.content.domain.dto.HotwordcountHotwordRelationDto;
import com.deepexi.content.domain.eo.HotwordcountHotwordRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface HotwordcountHotwordRelationMapper extends BaseMapper<HotwordcountHotwordRelation> {

    List<HotwordcountHotwordRelation> findList(@Param("eo") HotwordcountHotwordRelationDto eo);

    int deleteByIds(@Param("pks") List<String> pks);

    int deleteByIds(@Param("pks") String... pks);

    int batchInsert(@Param("eo") List<HotwordcountHotwordRelationDto> eo);

    int batchUpdate(@Param("eo") List<HotwordcountHotwordRelationDto> eo);

    /**
     * 查询id
     * @param hotWordId 热词id
     * @return 返回值
     */
    List<String> selectIdByHotWordId(String hotWordId);

    /**
     * 查询热词统计id
     * @param hotWordId 热词id
     * @return 返回值
     */
    String selectHotCountIdByHotWordId(String hotWordId);

    /**
     * 查询热词id
     * @param hotWordCountId 热词统计id
     * @return 返回值
     */
    List<String> selectHotWordIdByHotWordCountId(String hotWordCountId);

    /**
     * 删除数据
     * @param hotWordCountId 热词统计id
     * @return 返回值
     */
    int deleteByHotWordCountId(String hotWordCountId);

    /**
     * 删除数据
     * @param hotWordIds 热词统计id集合
     * @return 返回值
     */
    int deleteByHotWordIds(@Param("list")List<String> hotWordIds);

    /**
     * 查询历史热词id
     * @param hotWordCountId 热词统计id
     * @return 返回值
     */
    List<String> selectHistoryIds(String hotWordCountId);
}
