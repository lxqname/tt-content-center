package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.content.domain.dto.MemberChooseInterestIdRelationDto;
import com.deepexi.content.domain.eo.MemberChooseInterestIdRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberChooseInterestIdRelationMapper extends BaseMapper<MemberChooseInterestIdRelation> {

    List<MemberChooseInterestIdRelation> findList(@Param("eo") MemberChooseInterestIdRelationDto eo);

    int deleteByIds(@Param("pks") String... pks);

    int batchInsert(@Param("eo") List<MemberChooseInterestIdRelationDto> eo);

    int batchUpdate(@Param("eo") List<MemberChooseInterestIdRelationDto> eo);

    /**
     * 查询会员所选的值的id
     * @param interestId 兴趣项id memberId 会员id
     * @return 返回值
     */
    List<String> selectMemberChooseValueId(@Param("interestId") String interestId,@Param("memberId") String memberId);
}
