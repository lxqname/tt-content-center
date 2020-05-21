package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.content.domain.dto.DateConditionDto;
import com.deepexi.content.domain.dto.HotWordCountDto;
import com.deepexi.content.domain.eo.HotWordCount;
import com.deepexi.content.domain.vo.HotWordCountVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface HotWordCountMapper extends BaseMapper<HotWordCount> {

    List<HotWordCount> findList(@Param("eo") HotWordCountDto eo);

    List<HotWordCountVo> findPage(DateConditionDto eo);

    int deleteByIds(@Param("pks") String... pks);

    int batchInsert(@Param("eo") List<HotWordCountDto> eo);

    int batchUpdate(@Param("eo") List<HotWordCountDto> eo);

    /**
     * 查询id
     * @param date 日期
     * @return 返回值
     */
    String selectIdByShowDate(String date);
}

