package com.deepexi.content.service;

import com.deepexi.common.domain.vo.TreeVo;
import com.deepexi.content.domain.dto.*;
import com.deepexi.content.domain.vo.ProjectPageDetailVo;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.content.domain.eo.ProjectPage;

import java.util.*;

/**
 * @author hongchungen
 */
public interface ProjectPageService {

    /**
     * 专题页分页
     * @param eo
     * @param page
     * @param size
     * @return
     */
    PageBean<ProjectPage> findPage(ProjectPageDto eo, Integer page, Integer size);

    /**
     * 获得所有专题页
     * @param eo
     * @return
     */
    List<ProjectPage> findAll(ProjectPageDto eo);

    /**
     * 专题页详情
     * @param pk
     * @return
     */
    ProjectPage detail(String pk);

    /**
     * 专题页详情获得通过id
     * @param pk
     * @return
     */
    ProjectPageDetailVo details(String pk);

    /**
     * 专题页更新
     * @param pk
     * @param eo
     * @return
     */
    Boolean update(String pk, ProjectPageUpdateDto eo);

    /**
     * 专题页创建
     * @param eo
     * @return
     */
    Boolean create(ProjectPageCreateDto eo);

    /**
     * 专题页删除通过id
     * @param pk
     * @return
     */
    Boolean delete(String pk);

    /**
     * 获取专题页通过name
     * @param name
     * @return
     */
    ProjectPage getByName(String name);

    /**
     * 获取树
     *
     * @param dto
     * @param parentId
     * @return
     */
    List<TreeVo> getTree(ProjectPageDto dto, String parentId);

    /**
     * 获取楼层树类型
     *
     * @param dto
     * @return
     */
    List<TreeVo> getTreeByProject(ProjectPageDto dto);

    /**
     * 获取最大权重
     * @return
     */
    ProjectPage getMaxWeight();

    /**
     * 移动排序位置
     * @param eo
     * @return
     */
    Boolean move(MovePageDto eo);

    /**
     * 获得专题页信息 通过ID
     * @param id
     * @return
     */
    ProjectPage getById(String id);
}