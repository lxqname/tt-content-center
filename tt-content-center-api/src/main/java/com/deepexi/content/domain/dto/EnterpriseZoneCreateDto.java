package com.deepexi.content.domain.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class EnterpriseZoneCreateDto implements Serializable {
    private String id;
    /**
     * 企业专区名称
     */
    @NotNull(message = "企业专区名称不允许为空")
    private String name;
    private String imgUrl;
    private String details;
    /**
     * 状态0：启用，1：禁用
     */
    @NotNull(message = "企业专区状态不允许为空")
    private Integer status;
    private Integer uvCount;

    @NotNull(message = "关联组织ID不允许为空")
    private String organizationId;
    /**
     * 权重（创建时增长）
     */
    private Integer weight;
    /**
     * 父级id（默认0）
     */
    private String pId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUvCount() {
        return uvCount;
    }

    public void setUvCount(Integer uvCount) {
        this.uvCount = uvCount;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    public String toString() {
        return "EnterpriseZoneCreateDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", details='" + details + '\'' +
                ", status=" + status +
                ", uvCount=" + uvCount +
                ", organizationId='" + organizationId + '\'' +
                ", weight=" + weight +
                ", pId='" + pId + '\'' +
                '}';
    }
}
