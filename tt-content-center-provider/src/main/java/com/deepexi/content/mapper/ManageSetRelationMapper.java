package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.content.domain.dto.ManageSetRelationDto;
import com.deepexi.content.domain.eo.ManageSetRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface ManageSetRelationMapper extends BaseMapper<ManageSetRelation> {

    List<ManageSetRelation> findList(@Param("eo") ManageSetRelationDto eo);

    int deleteByIds(@Param("pks") String... pks);

    int batchInsert(@Param("eo") List<ManageSetRelationDto> eo);

    int batchUpdate(@Param("eo") List<ManageSetRelationDto> eo);
}
