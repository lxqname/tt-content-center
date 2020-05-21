package com.deepexi.content.service;

import com.deepexi.content.domain.dto.HotWordDto;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.content.domain.eo.HotwordGoodslablesRelation;
import com.deepexi.content.domain.dto.HotwordGoodslablesRelationDto;
import java.util.*;

public interface HotwordGoodslablesRelationService {

    /**
     * 分页查询
     * @param eo dto传输对象
     * @param page 页数
     * @param size 每页数据数
     * @return 返回值
     */
    PageBean<HotwordGoodslablesRelation> findPage(HotwordGoodslablesRelationDto eo, Integer page, Integer size);

    /**
     * 查询所有数据
     * @param eo dto传输数据
     * @return 返回值
     */
    List<HotwordGoodslablesRelation> findAll(HotwordGoodslablesRelationDto eo);

    /**
     * 查询数据明细
     * @param pk id
     * @return 返回值
     */
    HotwordGoodslablesRelation detail(String pk);

    /**
     * 更新数据
     * @param pk id
     * @param eo dto传输对象
     * @return 返回值
     */
    Boolean update(String pk, HotwordGoodslablesRelationDto eo);

    /**
     * 删除数据
     * @param hotWordId 热词id
     * @return 返回值
     */
    Boolean deleteRelation(String hotWordId);

    /**
     * 删除数据
     * @param hotWordIds 热词id集合
     * @return 返回值
     */
    Boolean deleteRelation(List<String> hotWordIds);

    /**
     * 新增数据
     * @param hotWordId 热词id
     * @param goodLabelIds 商品标签集合
     * @return 返回值
     */
    Boolean create(String hotWordId,List<String> goodLabelIds);

    /**
     * 查询商品标签
     * @param hotWordId 热词id
     * @return 返回值
     */
    List<String> selectGoodsLabelsId(String hotWordId);




}