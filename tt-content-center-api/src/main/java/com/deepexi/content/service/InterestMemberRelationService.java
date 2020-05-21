package com.deepexi.content.service;

import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.content.domain.eo.InterestMemberRelation;
import com.deepexi.content.domain.dto.InterestMemberRelationDto;
import java.util.*;

public interface InterestMemberRelationService {

    /**
     * 分页查询
     * @param eo dto传输对象
     * @param page 页号
     * @param size 每页数据数
     * @return 返回值
     */
    PageBean<InterestMemberRelation> findPage(InterestMemberRelationDto eo, Integer page, Integer size);

    /**
     * 查询所有数据
     * @param eo dto传输对象
     * @return 返回值
     */
    List<InterestMemberRelation> findAll(InterestMemberRelationDto eo);

    /**
     * 查看明细
     * @param pk id
     * @return 返回值
     */
    InterestMemberRelation detail(String pk);

    /**
     * 更新数据
     * @param pk id
     * @param eo dto传输对象
     * @return 返回值
     */
    Boolean update(String pk, InterestMemberRelationDto eo);

    /**
     * 新增数据
     * @param interestId 兴趣id
     * @param memberInfoId 会员信息id
     * @return 返回值
     */
    Boolean create(String interestId,String memberInfoId);

    /**
     * 删除数据
     * @param pk id数组
     * @return 返回值
     */
    Boolean delete(String... pk);

    /**
     * 删除数据
     * @param interestId 兴趣项id
     * @return 返回值
     */
    Boolean deleteByInterestId(String interestId);

    /**
     * 查询数据
     * @param interestId 兴趣项id
     * @return 返回值
     */
    String selectMemberInfoIdByInterestId(String interestId);

    /**
     * 查询数据
     * @param memberInfoId 会员信息id
     * @return 返回值
     */
    String selectInterestIdByMemberInfoId(String memberInfoId);
}