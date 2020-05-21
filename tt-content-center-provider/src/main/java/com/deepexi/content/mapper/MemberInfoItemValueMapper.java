package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.content.domain.dto.MemberInfoItemValueDto;
import com.deepexi.content.domain.eo.MemberInfoItemValue;
import com.deepexi.content.domain.vo.MemberInfoItemValueVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface MemberInfoItemValueMapper extends BaseMapper<MemberInfoItemValue> {

    List<MemberInfoItemValue> findList(@Param("eo") MemberInfoItemValueDto eo);

    int deleteByIds(@Param("pks") List<String> valueIds);

    int batchInsert(@Param("eo") List<MemberInfoItemValueDto> eo);

    int batchUpdate(@Param("eo") List<MemberInfoItemValueDto> eo);

    /**
     * 根据id查询会员信息项的值
     * @param list id集合
     * @return 会员信息项值
     */
    List<String> findById(@Param("list") List<String> list);


}
