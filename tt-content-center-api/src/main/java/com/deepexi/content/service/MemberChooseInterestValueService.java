package com.deepexi.content.service;

import com.deepexi.content.domain.dto.MemberChooseInterestValueDto;
import com.deepexi.content.domain.eo.MemberChooseInterestValue;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

public interface MemberChooseInterestValueService {

    /**
     * 分页查询
     * @param eo dto传输对象
     * @param page 页号
     * @param size 每页数据数
     * @return 返回值
     */
    PageBean<MemberChooseInterestValue> findPage(MemberChooseInterestValueDto eo, Integer page, Integer size);

    /**
     * 查询所有数据
     * @param eo dto传输对象
     * @return 返回值
     */
    List<MemberChooseInterestValue> findAll(MemberChooseInterestValueDto eo);

    /**
     * 查询数据明细
     * @param pk id
     * @return 返回值
     */
    MemberChooseInterestValue detail(String pk);

    /**
     * 更新数据
     * @param pk id
     * @param eo dto传输对象
     * @return 返回值
     */
    Boolean update(String pk, MemberChooseInterestValueDto eo);

    /**
     * 新增数据
     * @param eo dto传输对象
     * @return 返回值
     */
    Boolean create(MemberChooseInterestValueDto eo);

    /**
     * 新增数据
     * @param eo eo传输对象
     * @return 返回值
     */
    Boolean create(MemberChooseInterestValue eo);

    /**
     * 删除数据
     * @param pk id数组
     * @return 返回值
     */
    Boolean delete(String... pk);

    /**
     * 查询兴趣项值
     * @param valueId 值id
     * @return 返回值
     */
    String selectInterestValue(String valueId);
}