package com.deepexi.content.service;

/**
 * @author lxq
 * @date
 */

import com.deepexi.common.domain.vo.TreeVo;
import com.deepexi.content.domain.dto.EnterpriseFloorPageCreateDto;
import com.deepexi.content.domain.dto.EnterpriseFloorPageDto;
import com.deepexi.content.domain.dto.EnterpriseFloorPageUpdateDto;
import com.deepexi.content.domain.dto.MovePageDto;
import com.deepexi.content.domain.eo.EnterpriseFloorPage;
import com.deepexi.content.domain.vo.EnterpriseFloorPageDetailVo;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

public interface EnterpriseFloorPageMemberService {

    /**
     * 楼层页分页
     * @param eo
     * @param page
     * @param size
     * @return
     */
    PageBean<EnterpriseFloorPage> findPage(EnterpriseFloorPageDto eo, Integer page, Integer size);

    /**
     * 楼层页列表
     * @param eo
     * @return
     */
    List<EnterpriseFloorPageDetailVo> findAll(EnterpriseFloorPageDto eo);

    /**
     * 楼层页详情
     * @param pk
     * @return
     */
    EnterpriseFloorPage detail(String pk);

    /**
     * 通过id获取楼层页详情
     * @param pk
     * @return
     */
    EnterpriseFloorPageDetailVo details(String pk);

    /**
     * 楼层页修改
     * @param pk
     * @param eo
     * @return
     */
    Boolean update(String pk, EnterpriseFloorPageUpdateDto eo);

    /**
     * 楼层页创建
     * @param eo
     * @return
     */
    Boolean create(EnterpriseFloorPageCreateDto eo);

    /**
     * 通过楼层页名称获取楼层页
     * @param name
     * @return
     */
    EnterpriseFloorPage getByName(String name);
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
    List<TreeVo> getTree(EnterpriseFloorPageDto dto, String parentId);

    /**
     * 获取楼层树类型
     *
     * @param dto
     * @return
     */
    List<TreeVo> getTreeByFloor(EnterpriseFloorPageDto dto);

    /**
     * 获取最大权重
     * @return
     */
    EnterpriseFloorPage getMaxWeight();

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
    EnterpriseFloorPage getById(String id);
}