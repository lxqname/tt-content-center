package com.deepexi.content.domain.dto;

import javax.ws.rs.QueryParam;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ：wujie
 * @date ：Created in 2019/9/16 19:26
 * @description：会员信息项条件查询的Dto
 * @version: 1.0.0
 */
public class MemberInfoItemConditionDto implements Serializable {

    /**
     * 会员信息项名称
     */
    @QueryParam("name")
    private String name;

    /**
     * 会员信息项字段类型 对应 0为"全部";1为"选项-多选";2为"选项-单选";
     * 3为"文本";4为"日期";5为"省市区";6为"数值"
     */
    @QueryParam("fieldType")
    private Integer fieldType;

    /**
     * 会员信息项类型 对应0为"全部";1为"默认(数据库中默认的字段为1和2)";3为"自定义"
     */
    @QueryParam("itemType")
    private Integer itemType;

    private String tenantCode;

    private Date createdAt;

    private String createdBy;

    private Date updatedAt;

    private String updatedBy;

    private Integer  version;

    private Integer  dr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFieldType() {
        return fieldType;
    }

    public void setFieldType(Integer fieldType) {
        this.fieldType = fieldType;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDr() {
        return dr;
    }

    public void setDr(Integer dr) {
        this.dr = dr;
    }

    @Override
    public String toString() {
        return "MemberInfoItemConditionDto{" +
                "name='" + name + '\'' +
                ", fieldType=" + fieldType +
                ", itemType=" + itemType +
                ", tenantCode='" + tenantCode + '\'' +
                ", createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                ", updatedAt=" + updatedAt +
                ", updatedBy='" + updatedBy + '\'' +
                ", version=" + version +
                ", dr=" + dr +
                '}';
    }
}
