package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.content.domain.dto.InterestDto;
import com.deepexi.content.domain.eo.Interest;
import com.deepexi.content.domain.vo.InterestToH5Vo;
import com.deepexi.content.domain.vo.InterestVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface InterestMapper extends BaseMapper<Interest> {

    List<Interest> findList(@Param("eo") InterestDto eo);

    int deleteByIds(@Param("pks") String... pks);

    int batchInsert(@Param("eo") List<InterestDto> eo);

    int batchUpdate(@Param("eo") List<InterestDto> eo);

    /**
     * 查询最大序号
     * @return 返回值
     */
    int selectMaxLevel();

    /**
     * 分页查询
     * @param tenantCode 租户
     * @return 返回值
     */
    List<InterestVo> findPage(String tenantCode);

    /**
     * 查询引导语
     * @param id id
     * @return 返回值
     */
    InterestVo selectGuideName(String id);

    /**
     * 查询序号
     * @param id id
     * @return 返回值
     */
    int selectLevelById(String id);

    /**
     * 上升
     * @param level 权重
     * @return 返回值
     */
    InterestVo selectIdByUpLevel(int level);

    /**
     * 下降
     * @param level 权重
     * @return 返回值
     */
    InterestVo selectIdByDownLevel(int level);

    /**
     * 查询兴趣项
     * @return 返回值
     */
    List<InterestToH5Vo> selectAllInterest();

}
