package com.deepexi.content.service;

import com.deepexi.common.domain.vo.TreeVo;
import com.deepexi.content.domain.dto.*;
import com.deepexi.content.domain.eo.EnterpriseZone;
import com.deepexi.content.domain.vo.EnterpriseZoneDetailVo;
import com.deepexi.content.domain.vo.EnterpriseZoneVo;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

public interface EnterpriseZoneService {
    /**
     * 企业专区分页
     * @param eo
     * @param page
     * @param size
     * @return
     */
    PageBean<EnterpriseZoneVo> findPage(EnterpriseZoneDto eo, Integer page, Integer size);

    /**
     * 企业专区列表
     * @param eo
     * @return
     */
    List<EnterpriseZoneVo> findAll(EnterpriseZoneDto eo);

    /**
     * 企业专区详情
     * @param pk
     * @return
     */
    EnterpriseZoneDetailVo externalDetail(String pk);


    /**
     * 企业专区修改
     * @param pk
     * @param eo
     * @return
     */
    Boolean update(String pk, EnterpriseZoneUpdateDto eo);

    /**
     * 企业专区创建
     * @param eo
     * @return
     */
    Boolean create(EnterpriseZoneCreateDto eo);

    /**
     * 通过企业专区名称获取企业专区
     * @param name
     * @return
     */
    EnterpriseZone getByName(String name);
    /**
     * 企业专区删除
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
    List<TreeVo> getTree(EnterpriseZoneDto dto, String parentId);

    /**
     * 获取楼层树类型
     *
     * @param dto
     * @return
     */
    List<TreeVo> getTreeByEnterprise(EnterpriseZoneDto dto);

    /**
     * 获取最大权重
     * @return
     */
    EnterpriseZone getMaxWeight();

    /**
     * 移动排序位置
     * @param eo
     * @return
     */
    Boolean move(MovePageDto eo);

    /**
     * 获得企业专区信息，通过ID
     * @param id
     * @return
     */
    EnterpriseZone getById(String id);

    /**
     * 增加粉丝
     * @return
     */
    Boolean addUvCount(EnterpriseUvCountDto dto);
}
