package com.deepexi.content.service;

import com.deepexi.content.domain.dto.MemberIdSequenceDto;
import com.deepexi.content.domain.dto.MemberInfoItemConditionDto;
import com.deepexi.content.domain.vo.InterestToH5Vo;
import com.deepexi.content.domain.vo.MemberInfoItemVo;
import com.deepexi.content.domain.vo.MemberToInterestVo;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.content.domain.eo.MemberInfoItem;
import com.deepexi.content.domain.dto.MemberInfoItemDto;
import com.github.pagehelper.PageInfo;

import java.util.*;

public interface MemberInfoItemService {

    /**
     * 分页查询(含条件)
     * @param eo dto传输对象
     * @param page 页号
     * @param size 每页数据数
     * @return 返回值
     */
    PageBean<MemberInfoItemVo> findPage(MemberInfoItemConditionDto eo, Integer page, Integer size);

    /**
     * 分页查询
     * @param page 页号
     * @param size 每页数据数
     * @return 返回值
     */
    PageBean<MemberToInterestVo> findPage(Integer page, Integer size);

    /**
     * 查询所有数据
     * @param eo dto传输对象
     * @return 返回值
     */
    List<MemberInfoItem> findAll(MemberInfoItemDto eo);

    /**
     * 查询数据明细
     * @param pk id
     * @return 返回值
     */
    MemberInfoItem detail(String pk);

    /**
     * 更新为1
     * @param pk id
     * @param eo dto传输对象
     * @return 返回值
     */
    Boolean updateToOne(String pk, MemberInfoItemDto eo);

    /**
     * 更新为0
     * @param pk id
     * @param eo dto传输对象
     * @return 返回值
     */
    Boolean updateToZero(String pk, MemberInfoItemDto eo);

    /**
     * 新增数据
     * @param eo dto传输对象
     * @return 返回值
     */
    Boolean create(MemberInfoItemDto eo);

    /**
     * 删除对象
     * @param pk id数组
     * @return 返回值
     */
    Boolean delete(String... pk);

    /**
     * 启动
     * @param id id
     * @return 返回值
     */
    Boolean enable(String id);

    /**
     * 禁用
     * @param id id
     * @return 返回值
     */
    Boolean prohibit(String id);

    /**
     * 上升
     * @param id id
     * @return 返回值
     */
    Boolean sequenceUp(String id);

    /**
     * 下降
     * @param id id
     * @return 返回值
     */
    Boolean sequenceDown(String id);

    /**
     * 删除数据
     * @param id id
     * @return 返回值
     */
    Boolean deleteById(String id);

    /**
     * 查询兴趣到H5页面
     * @param memberInfoId 会员信息项id
     * @return 返回值
     */
    MemberInfoItemDto selectInfoToH5ById(String memberInfoId);
}