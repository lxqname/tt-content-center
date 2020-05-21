package com.deepexi.content.service;

import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.content.domain.eo.MemberInfoItemValue;
import com.deepexi.content.domain.dto.MemberInfoItemValueDto;
import java.util.*;

public interface MemberInfoItemValueService {

    /**
     * 分页查询
     * @param eo dto传输对象
     * @param page 页号
     * @param size 每页数据数
     * @return 返回值
     */
    PageBean<MemberInfoItemValue> findPage(MemberInfoItemValueDto eo, Integer page, Integer size);

    /**
     * 查询所有数据
     * @param eo dto数据传输对象
     * @return 返回值
     */
    List<MemberInfoItemValue> findAll(MemberInfoItemValueDto eo);

    /**
     * 查询数据明细
     * @param pk id
     * @return 返回值
     */
    MemberInfoItemValue detail(String pk);

    /**
     * 更新数据
     * @param pk id
     * @param eo dto数据传输对象
     * @return 返回值
     */
    Boolean update(String pk, MemberInfoItemValueDto eo);

    /**
     * 新增数据
     * @param eo dto传输对象
     * @return 返回值
     */
    Boolean create(MemberInfoItemValueDto eo);

    /**
     * 删除数据
     * @param valueIds 值id集合
     * @return 返回值
     */
    Boolean delete(List<String> valueIds);

    /**
     * 增加数据
     * @param values 会员信息项值集合
     * @param memberInfoItemId 会员信息项id
     * @return 返回值
     */
    Boolean insertMemberInfoItemValue(List<String> values,String memberInfoItemId);

    /**
     * 查询数据
     * @param valueIds 值id集合
     * @return 返回值
     */
    List<String> selectValuesByIds(List<String> valueIds);
}