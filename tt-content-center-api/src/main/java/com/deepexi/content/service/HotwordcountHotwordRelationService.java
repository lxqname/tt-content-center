package com.deepexi.content.service;

import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.content.domain.eo.HotwordcountHotwordRelation;
import com.deepexi.content.domain.dto.HotwordcountHotwordRelationDto;
import java.util.*;

public interface HotwordcountHotwordRelationService {

    /**
     * 分页查询
     * @param eo 传输对象
     * @param page 第几页
     * @param size 每页数据数
     * @return 返回值
     */
    PageBean<HotwordcountHotwordRelation> findPage(HotwordcountHotwordRelationDto eo, Integer page, Integer size);

    /**
     * 查询所有信息
     * @param eo 传输对象
     * @return 返回值
     */
    List<HotwordcountHotwordRelation> findAll(HotwordcountHotwordRelationDto eo);

    /**
     * 查询明细
     * @param pk id
     * @return 返回值
     */
    HotwordcountHotwordRelation detail(String pk);

    /**
     * 更新数据
     * @param pk id
     * @param eo 传输对象
     * @return 返回值
     */
    Boolean update(String pk, HotwordcountHotwordRelationDto eo);

    /**
     * 新增数据
     * @param eo 传输对象dto
     * @return 返回值
     */
    Boolean create(HotwordcountHotwordRelationDto eo);

    /**
     * 新增数据
     * @param eo eo对象
     * @return 返回值
     */
    Boolean create(HotwordcountHotwordRelation eo);

    /**
     * 删除数据
     * @param pk id
     * @return 返回值
     */
    Boolean delete(String... pk);

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
     * 查询数据
     * @param hotWordId 热词id
     * @return 返回值
     */
    String selectHotWordCountIdByHotWordId(String hotWordId);

    /**
     * 查询数据
     * @param hotWordCountId 热词统计id
     * @return 返回值
     */
    List<String> selectHotWordIdByHotWordCountId(String hotWordCountId);

    /**
     * 删除数据
     * @param hotWordCountId 热词统计id
     * @return 返回值
     */
    Boolean deleteRelationByHotWordCountId(String hotWordCountId);

    /**
     * 查询数据
     * @param hotWordCountId 热词统计id
     * @return 返回值
     */
    List<String> selectHistoryHotWordIds(String hotWordCountId);
}