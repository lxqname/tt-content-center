package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.content.domain.dto.MemberIdSequenceDto;
import com.deepexi.content.domain.dto.MemberInfoItemConditionDto;
import com.deepexi.content.domain.dto.MemberInfoItemDto;
import com.deepexi.content.domain.eo.MemberInfoItem;
import com.deepexi.content.domain.vo.MemberInfoItemVo;
import com.deepexi.content.domain.vo.MemberToInterestVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface MemberInfoItemMapper extends BaseMapper<MemberInfoItem> {

    List<MemberInfoItem> findList(@Param("eo") MemberInfoItemDto eo);

    int deleteByIds(@Param("pks") String... pks);

    int batchInsert(@Param("eo") List<MemberInfoItemDto> eo);

    int batchUpdate(@Param("eo") List<MemberInfoItemDto> eo);

    /**
     * 获取会员信息有关字段
     * @param dto 查询条件
     * @return 会员信息项
     */
    List<MemberInfoItemVo> findPage(MemberInfoItemConditionDto dto);

    /**
     * 分页查询会员信息项展示到兴趣项选择页面
     * @param tenantCode 租户id
     * @return 会员信息封装对象
     */
    List<MemberToInterestVo> findPageToInterest(String tenantCode);


    /**
     * 查询最大序列号
     * @return 最大序列号
     */
    int selectMaxSequence();


    /**
     * 根据id查询是否满足删除条件
     * @param id id
     * @return 会员信息封装对象
     */
    MemberInfoItemVo selectDeleteConditionsById(String id);


    /**
     * 根据id查sequence
     * @param id id
     * @return 序列号
     */
    int selectSequenceById(String id);

    /**
     * 查询id
     * @param sequence 序列号
     * @return 会员信息项封装对象
     */
    MemberInfoItemVo selectIdByUpSequence(int sequence);


    /**
     * 查询id
     * @param sequence 序列号
     * @return 会员信息项封装对象
     */
    MemberInfoItemVo selectIdByDownSequence(int sequence);

    /**
     * 查询相关会员信息到H5
     * @param memberInfoId 会员信息项id
     * @return 返回值
     */
    MemberInfoItemDto selectInfoToH5ById(String memberInfoId);

    /**
     * 验证lowerTableAttribute唯一性
     * @param lowerTableAttribute 关联表属性名
     * @return 返回值
     */
    List<String> checkLowerTableAttribute(String lowerTableAttribute);
}
