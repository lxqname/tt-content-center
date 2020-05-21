package com.deepexi.content.service;

import com.deepexi.content.domain.dto.MemberInfoItemValueDto;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.content.domain.eo.MemberInfoItemValueRelation;
import com.deepexi.content.domain.dto.MemberInfoItemValueRelationDto;
import java.util.*;

public interface MemberInfoItemValueRelationService {

    /**
     * 分页查询
     * @param eo dto传输对象
     * @param page 页号
     * @param size 每页数据数
     * @return 返回值
     */
    PageBean<MemberInfoItemValueRelation> findPage(MemberInfoItemValueRelationDto eo, Integer page, Integer size);

    /**
     * 查询所有数据
     * @param eo dto传输对象
     * @return 返回值
     */
    List<MemberInfoItemValueRelation> findAll(MemberInfoItemValueRelationDto eo);

    /**
     * 查询数据明细
     * @param pk id
     * @return 返回值
     */
    MemberInfoItemValueRelation detail(String pk);

    /**
     * 更新数据
     * @param pk id
     * @param eo dto传输对象
     * @return 返回值
     */
    Boolean update(String pk, MemberInfoItemValueRelationDto eo);

    /**
     * 新增数据
     * @param eo dto传输对象
     * @return 返回值
     */
    Boolean create(MemberInfoItemValueRelationDto eo);

    /**
     * 删除数据
     * @param pk id数组
     * @return 返回值
     */
    Boolean delete(String... pk);

    /**
     * 删除数据
     * @param itemId id
     * @return 返回值
     */
    Boolean deleteById(String itemId);

    /**
     * 查询数据
     * @param itemId id
     * @return 返回值
     */
    List<String> selectByItemId(String itemId);

    /**
     * 增加数据
     * @param memberInfoItemValueDtos dto传输对象集合
     * @param memberInfoItemId 会员信息项id
     * @return 返回值
     */
    Boolean insertItemIdAndItemValueId(List<MemberInfoItemValueDto> memberInfoItemValueDtos,String memberInfoItemId);
}