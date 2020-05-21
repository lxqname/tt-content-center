package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.content.domain.dto.MemberChooseInterestValueDto;
import com.deepexi.content.domain.eo.MemberChooseInterestValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberChooseInterestValueMapper extends BaseMapper<MemberChooseInterestValue> {

    List<MemberChooseInterestValue> findList(@Param("eo") MemberChooseInterestValueDto eo);

    int deleteByIds(@Param("pks") String... pks);

    int batchInsert(@Param("eo") List<MemberChooseInterestValueDto> eo);

    int batchUpdate(@Param("eo") List<MemberChooseInterestValueDto> eo);
}
