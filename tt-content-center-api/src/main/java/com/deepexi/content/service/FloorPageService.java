package com.deepexi.content.service;

/**
 * @author hongchungen
 * @date
 */

import com.deepexi.common.domain.vo.TreeVo;
import com.deepexi.content.domain.dto.MovePageDto;
import com.deepexi.content.domain.dto.FloorPageCreateDto;
import com.deepexi.content.domain.dto.FloorPageDto;
import com.deepexi.content.domain.dto.FloorPageUpdateDto;
import com.deepexi.content.domain.eo.FloorPage;
import com.deepexi.content.domain.vo.FloorPageDetailVo;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

public interface FloorPageService {

    /**
     * 楼层页分页
     * @param eo
     * @param page
     * @param size
     * @return
     */
    PageBean<FloorPage> findPage(FloorPageDto eo, Integer page, Integer size);

    /**
     * 楼层页列表
     * @param eo
     * @return
     */
    List<FloorPage> findAll(FloorPageDto eo);

    /**
     * 楼层页详情
     * @param pk
     * @return
     */
    FloorPage detail(String pk);

    /**
     * 通过id获取楼层页详情
     * @param pk
     * @return
     */
    FloorPageDetailVo details(String pk);

    /**
     * 楼层页修改
     * @param pk
     * @param eo
     * @return
     */
    Boolean update(String pk, FloorPageUpdateDto eo);

    /**
     * 楼层页创建
     * @param eo
     * @return
     */
    Boolean create(FloorPageCreateDto eo);

    /**
     * 通过楼层页名称获取楼层页
     * @param name
     * @return
     */
    FloorPage getByName(String name);
    /**
     * 楼层页删除
     * @param pk
     * @return
     */
    Boolean delete(String pk);

    /**
     * 获取树
     *
     * @param dto
     * @param parentId
     * @return
     */
    List<TreeVo> getTree(FloorPageDto dto, String parentId);

    /**
     * 获取楼层树类型
     *
     * @param dto
     * @return
     */
    List<TreeVo> getTreeByFloor(FloorPageDto dto);

    /**
     * 获取最大权重
     * @return
     */
    FloorPage getMaxWeight();

    /**
     * 移动排序位置
     * @param eo
     * @return
     */
    Boolean move(MovePageDto eo);

    /**
     * 获得楼层页信息，通过ID
     * @param id
     * @return
     */
    FloorPage getById(String id);
}