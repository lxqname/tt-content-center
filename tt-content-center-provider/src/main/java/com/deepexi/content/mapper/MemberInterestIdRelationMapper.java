package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.content.domain.dto.MemberInterestIdRelationDto;
import com.deepexi.content.domain.eo.MemberInterestIdRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberInterestIdRelationMapper extends BaseMapper<MemberInterestIdRelation> {

    List<MemberInterestIdRelation> findList(@Param("eo") MemberInterestIdRelationDto eo);

    int deleteByIds(@Param("pks") String... pks);

    int batchInsert(@Param("eo") List<MemberInterestIdRelationDto> eo);

    int batchUpdate(@Param("eo") List<MemberInterestIdRelationDto> eo);

    int updateByInterestId(MemberInterestIdRelationDto memberInterestIdRelationDto);

    /**
     * 查询兴趣项id
     * @param memberId 会员id
     * @return 返回值
     */
    List<String> selectInterestIdByMemberId(String memberId);
}
