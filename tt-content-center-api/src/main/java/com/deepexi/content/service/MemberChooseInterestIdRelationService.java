package com.deepexi.content.service;

import com.deepexi.content.domain.dto.MemberChooseInterestIdRelationDto;
import com.deepexi.content.domain.eo.MemberChooseInterestIdRelation;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

public interface MemberChooseInterestIdRelationService {

    /**
     * 分页查询
     * @param eo dto传输对象
     * @param page 页号
     * @param size 每页数据数
     * @return 返回值
     */
    PageBean<MemberChooseInterestIdRelation> findPage(MemberChooseInterestIdRelationDto eo, Integer page, Integer size);

    /**
     * 查询所有数据
     * @param eo dto传输对象
     * @return 返回值
     */
    List<MemberChooseInterestIdRelation> findAll(MemberChooseInterestIdRelationDto eo);

    /**
     * 查询数据明细
     * @param pk id
     * @return 返回值
     */
    MemberChooseInterestIdRelation detail(String pk);

    /**
     * 更新数据
     * @param pk id
     * @param eo dto传输对象
     * @return 返回值
     */
    Boolean update(String pk, MemberChooseInterestIdRelationDto eo);

    /**
     * 新增数据
     * @param eo dto传输对象
     * @return 返回值
     */
    Boolean create(MemberChooseInterestIdRelationDto eo);

    /**
     * 新增数据
     * @param eo eo传输对象
     * @return 返回值
     */
    Boolean create(MemberChooseInterestIdRelation eo);

    /**
     * 删除数据
     * @param pk id数组
     * @return 返回值
     */
    Boolean delete(String... pk);

    /**
     * 查询会员选择的兴趣项值的id
     * @param interestId 兴趣项id  memberId会员id
     * @return 返回值
     */
    List<String> selectMemberChooseValueId(String interestId,String memberId);
}