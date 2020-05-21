package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.content.domain.dto.MemberInfoItemValueRelationDto;
import com.deepexi.content.domain.eo.MemberInfoItemValueRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface MemberInfoItemValueRelationMapper extends BaseMapper<MemberInfoItemValueRelation> {

    List<MemberInfoItemValueRelation> findList(@Param("eo") MemberInfoItemValueRelationDto eo);

    int deleteByIds(@Param("pks") String... pks);

    int batchInsert(@Param("eo") List<MemberInfoItemValueRelationDto> eo);

    int batchUpdate(@Param("eo") List<MemberInfoItemValueRelationDto> eo);

    /**
     * 根据会员信息项的id查询会员信息值的id
     * @param itemId 会员信息项id
     * @return 会员信息项值的id集合
     */
    List<String> findById(@Param("itemId") String itemId);


    /**
     * 根据itemId删除关联表数据
     * @param itemId 会员项id
     * @return 影响数值
     */
    int deleteByItemId(String itemId);


}
