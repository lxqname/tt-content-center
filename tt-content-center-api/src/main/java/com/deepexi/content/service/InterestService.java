package com.deepexi.content.service;

import com.deepexi.content.domain.dto.MemberInterestDto;
import com.deepexi.content.domain.dto.MemberInterestRpcDto;
import com.deepexi.content.domain.vo.InterestToH5Vo;
import com.deepexi.content.domain.vo.InterestVo;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.content.domain.eo.Interest;
import com.deepexi.content.domain.dto.InterestDto;


import java.util.*;

public interface InterestService {

    /**
     * 分页查询
     * @param page 页号
     * @param size 每页数据数
     * @return 返回值
     */
    PageBean<InterestVo> findPage(Integer page, Integer size);

    /**
     * 查询所有数据
     * @param eo dto传输对象
     * @return 返回值
     */
    List<Interest> findAll(InterestDto eo);

    /**
     * 查询数据明细
     * @param pk id
     * @return 返回值
     */
    Interest detail(String pk);

    /**
     * 更新数据
     * @param pk id
     * @param eo dto传输对象
     * @return 返回值
     */
    Boolean update(String pk, InterestDto eo);

    /**
     * 更新状态
     * @param pk id
     * @param eo dto传输对象
     * @return 返回值
     */
    Boolean updateStatus(String pk,InterestDto eo);

    /**
     * 新增数据
     * @param eo dto传输对象
     * @return 返回值
     */
    Boolean create(InterestDto eo);

    /**
     * 删除数据
     * @param pk id数组
     * @return 返回值
     */
    Boolean delete(String... pk);

    /**
     * 删除数据
     * @param id id
     * @return 返回值
     */
    Boolean delete(String id);

    /**
     * 查询引导语
     * @param id id
     * @return 返回值
     */
    InterestVo selectGuideName(String id);

    /**
     * 上升
     * @param id id
     * @return 返回值
     */
    Boolean updateLevelUp(String id);

    /**
     * 下降
     * @param id id
     * @return 返回值
     */
    Boolean updateLevelDown(String id);

    /**
     * 查询所有兴趣项
     * @return 返回值
     */
    List<InterestToH5Vo> findAllInterest();

    /**
     * 保存兴趣项
     * @param memberInterestDto dto传输对象
     * @return 返回值
     */
    Boolean saveInterest(MemberInterestDto memberInterestDto);

    /**
     * 查找会员选择的兴趣项
     * @param memberId 会员id
     * @return 返回值
     */
    List<MemberInterestRpcDto> findMemberInterest(String memberId);
}