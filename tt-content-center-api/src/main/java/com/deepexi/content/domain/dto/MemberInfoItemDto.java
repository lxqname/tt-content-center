package com.deepexi.content.domain.dto;

import com.deepexi.content.domain.eo.MemberInfoItem;
import java.util.Collection;
import java.util.List;
import java.util.Date;
import java.io.Serializable;

/**
 * @desc member_info_item
 * @author 
 */
public class MemberInfoItemDto implements Serializable {

    /**
     * 数据库自生成
     */
    private String id;

    /**
     * 会员信息项名称
     */
    private String name;

    /**
     * 会员信息项关联属性表
     */
    private String tableAttribute;

    /**
     * 字段类型
     * 对应 1为"选项-多选";2为"选项-单选";
     * 3为"文本";4为"日期";5为"省市区";6为"数值"
     */
    private Integer  fieldType;

    /**
     * 会员信息项类型
     * 所有后期添加的都默认为3(自定义)
     */
    private Integer  itemType;

    /**
     * 是否为兴趣项，默认为0(否),随着兴趣项选择而改变
     */
    private Integer  isInterest;

    /**
     * 是否同步为标签 0为否  1为是
     */
    private Integer  isLabel;

    /**
     * 状态 0为禁用 1为启用
     */
    private Integer  status;

    /**
     * 排列序号,查询再增加
     */
    private Integer  sequence;

    /**
     * 会员信息项的值
     */
    private List<String> values;

    private String tenantCode;

    private Date createdAt;

    private String createdBy;

    private Date updatedAt;

    private String updatedBy;

    private Integer  version;

    private Integer  dr;


    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setTableAttribute(String tableAttribute){
        this.tableAttribute = tableAttribute;
    }

    public String getTableAttribute(){
        return this.tableAttribute;
    }

    public void setFieldType(Integer  fieldType){
        this.fieldType = fieldType;
    }

    public Integer  getFieldType(){
        return this.fieldType;
    }

    public void setItemType(Integer  itemType){
        this.itemType = itemType;
    }

    public Integer  getItemType(){
        return this.itemType;
    }

    public void setIsInterest(Integer  isInterest){
        this.isInterest = isInterest;
    }

    public Integer  getIsInterest(){
        return this.isInterest;
    }

    public void setIsLabel(Integer  isLabel){
        this.isLabel = isLabel;
    }

    public Integer  getIsLabel(){
        return this.isLabel;
    }

    public void setStatus(Integer  status){
        this.status = status;
    }

    public Integer  getStatus(){
        return this.status;
    }

    public void setSequence(Integer  sequence){
        this.sequence = sequence;
    }

    public Integer  getSequence(){
        return this.sequence;
    }

    public void setTenantCode(String tenantCode){
        this.tenantCode = tenantCode;
    }

    public String getTenantCode(){
        return this.tenantCode;
    }

    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt;
    }

    public Date getCreatedAt(){
        return this.createdAt;
    }

    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }

    public String getCreatedBy(){
        return this.createdBy;
    }

    public void setUpdatedAt(Date updatedAt){
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt(){
        return this.updatedAt;
    }

    public void setUpdatedBy(String updatedBy){
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy(){
        return this.updatedBy;
    }

    public void setVersion(Integer  version){
        this.version = version;
    }

    public Integer  getVersion(){
        return this.version;
    }

    public void setDr(Integer  dr){
        this.dr = dr;
    }

    public Integer  getDr(){
        return this.dr;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}

