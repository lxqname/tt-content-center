package com.deepexi.content.domain.vo;

import com.deepexi.common.domain.vo.TreeVo;

import java.util.List;

/**
 * @author lxq
 * @date 2019/09/25 10:59
 */
public class EnterpriseFloorPageVo {
    /**
     * 楼层页id
     */
    private String id;

    /**
     * 父级id
     */
    private String pId;

    /**
     * 楼层页名称
     */
    private String name;

    /**
     * 组织id
     */
    private String organizationId;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 树结构
     */
    private List<TreeVo> treeArr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public List<TreeVo> getTreeArr() {
        return treeArr;
    }

    public void setTreeArr(List<TreeVo> treeArr) {
        this.treeArr = treeArr;
    }
}
