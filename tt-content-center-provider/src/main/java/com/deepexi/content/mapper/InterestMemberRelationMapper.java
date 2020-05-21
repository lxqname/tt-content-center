package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.content.domain.dto.InterestMemberRelationDto;
import com.deepexi.content.domain.eo.InterestMemberRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface InterestMemberRelationMapper extends BaseMapper<InterestMemberRelation> {

    List<InterestMemberRelation> findList(@Param("eo") InterestMemberRelationDto eo);

    int deleteByIds(@Param("pks") String... pks);

    int batchInsert(@Param("eo") List<InterestMemberRelationDto> eo);

    int batchUpdate(@Param("eo") List<InterestMemberRelationDto> eo);

    /**
     * 删除数据
     * @param interestId 兴趣项id
     * @return 返回值
     */
    int deleteByInterestId(String interestId);

    /**
     * 查询会员信息项id
     * @param interestId  兴趣项id
     * @return 返回值
     */
    String selectMemberInfoIdByInterestId(String interestId);

    /**
     * 查询兴趣项id
     * @param memberInfoId 会员信息项id
     * @return 返回值
     */
    String selectInterestIdByMemberInfoId(String memberInfoId);
}
