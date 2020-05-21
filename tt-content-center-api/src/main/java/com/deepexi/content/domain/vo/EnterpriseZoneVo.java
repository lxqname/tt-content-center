package com.deepexi.content.domain.vo;

import com.deepexi.common.domain.eo.SuperEntity;

import java.io.Serializable;

public class EnterpriseZoneVo extends SuperEntity implements Serializable {
    private String name;
    private String imgUrl;
    private String enterpriseName;
    private String enterpriseShortName;
    private String logo;
    private String details;
    private Integer status;
    private Integer uvCount;
    private String organizationId;
    private Integer weight;
    private String pId;

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

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseShortName() {
        return enterpriseShortName;
    }

    public void setEnterpriseShortName(String enterpriseShortName) {
        this.enterpriseShortName = enterpriseShortName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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
        return "EnterpriseZoneVo{" +
                "name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", enterpriseShortName='" + enterpriseShortName + '\'' +
                ", logo='" + logo + '\'' +
                ", details='" + details + '\'' +
                ", status=" + status +
                ", uvCount=" + uvCount +
                ", organizationId='" + organizationId + '\'' +
                ", weight=" + weight +
                ", pId='" + pId + '\'' +
                '}';
    }
}
