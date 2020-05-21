package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.content.domain.dto.HotWordDto;
import com.deepexi.content.domain.eo.HotWord;
import com.deepexi.content.domain.vo.HotWordVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface HotWordMapper extends BaseMapper<HotWord> {

    List<HotWord> findList(@Param("eo") HotWordDto eo);

    int deleteByIds(@Param("pks") List<String> pks);

    int batchInsert(@Param("eo") List<HotWordDto> eo);

    int batchUpdate(@Param("eo") List<HotWordDto> eo);

    /**
     * 查询搜索次数
     * @param hotWordId 热词id
     * @return 返回值
     */
    int selectSearchNumById(String hotWordId);

    /**
     * 查询自定义的id
     * @param hotWordIds 热词id集合
     * @return 返回值
     */
    List<String> selectCustomIds( @Param("list") List<String> hotWordIds);

    /**
     * 查询热词
     * @param hotWordIds 热词id集合
     * @return 返回值
     */
    List<HotWordVo> selectHotWordsById(@Param("list") List<String> hotWordIds);

    /**
     * 查询历史热词
     * @param yHotWordIds 历史热词id集合
     * @return 返回值
     */
    List<HotWordDto> selectYhotWordsById(@Param("list") List<String> yHotWordIds);

    /**
     * 统计搜索次数
     * @param yHotWordIds 历史热词id集合
     * @return 返回值
     */
    int sumSearchNum(@Param("list")List<String> yHotWordIds);

    /**
     * 更新搜索次数
     * @param hotWordId 热词id
     * @return 返回值
     */
    int updateSearchNum(String hotWordId);



}