package com.deepexi.content.service;

import com.deepexi.content.domain.dto.MemberInterestIdRelationDto;
import com.deepexi.content.domain.eo.MemberInterestIdRelation;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

public interface MemberInterestIdRelationService {

    /**
     * 分页查询
     * @param eo dto传输对象集合
     * @param page 页号
     * @param size 每页数据数
     * @return 返回值
     */
    PageBean<MemberInterestIdRelation> findPage(MemberInterestIdRelationDto eo, Integer page, Integer size);

    /**
     * 查询所有数据
     * @param eo dto传输对象集合
     * @return 返回值
     */
    List<MemberInterestIdRelation> findAll(MemberInterestIdRelationDto eo);

    /**
     * 查询数据明细
     * @param pk id
     * @return 返回值
     */
    MemberInterestIdRelation detail(String pk);

    /**
     * 更新数据
     * @param pk id
     * @param eo dto传输对象
     * @return 返回值
     */
    Boolean update(String pk, MemberInterestIdRelationDto eo);

    /**
     * 新增数据
     * @param eo dto传输对象
     * @return 返回值
     */
    Boolean create(MemberInterestIdRelationDto eo);

    /**
     * 新增数据
     * @param eo eo传输对象
     * @return 返回值
     */
    Boolean create(MemberInterestIdRelation eo);

    /**
     * 删除数据
     * @param pk id数组
     * @return 返回值
     */
    Boolean delete(String... pk);

    /**
     * 更新数据
     * @param memberInterestIdRelationDto dto传输对象
     * @return 返回值
     */
    Boolean updateByInterestId(MemberInterestIdRelationDto memberInterestIdRelationDto);

    /**
     * 查询数据
     * @param memberId 会员id
     * @return返回值
     */
    List<String> selectInterestIdByMemberId(String memberId);
}