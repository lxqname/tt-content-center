package com.deepexi.content.domain.vo;

import com.deepexi.common.domain.vo.TreeVo;

import java.util.List;

/**
 * @author hongchungen
 * @date 2019/09/26 1:58
 */
public class ProjectPageVo {
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
