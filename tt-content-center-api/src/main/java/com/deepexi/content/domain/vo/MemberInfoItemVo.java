package com.deepexi.content.domain.vo;

import java.util.List;

/**
 * @author ：wujie
 * @date ：Created in 2019/9/16 10:42
 * @description：会员信息返回值的Vo
 * @version: 1.0.0
 */
public class MemberInfoItemVo {

    /**
     * id主键
     */
    private String id;

    /**
     *会员信息项名称
     */
    private String name;

    /**
     * 关联表属性名
     */
    private String tableAttribute;

    /**
     * 字段类型：1=选项-多选,2=选项-单选,3=文本,4=日期,5=省市区,6=数值
     */
    private Integer fieldType;

    /**
     * 会员信息项类型：1=默认-不可操作,2=默认-可操作,3=自定义
     */
    private Integer itemType;

    /**
     * 是否为兴趣项; 0=否 1=是
     */
    private  Integer isInterest;

    /**
     * 是否同步为原生标签; 0=否 1=是
     */
    private Integer isLabel;

    /**
     * 状态：0=禁用，1=启用
     */
    private Integer status;

    /**
     * 序号,排序用(后端)
     */
    private Integer sequence;

    /**
     * 会员信息项值
     */
    private List<String> itemValues;

    /**
     * 序号，排序用(前端)
     */
    private Integer orderNum;

    public MemberInfoItemVo() {
    }

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

    public String getTableAttribute() {
        return tableAttribute;
    }

    public void setTableAttribute(String tableAttribute) {
        this.tableAttribute = tableAttribute;
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

    public Integer getIsInterest() {
        return isInterest;
    }

    public void setIsInterest(Integer isInterest) {
        this.isInterest = isInterest;
    }

    public Integer getIsLabel() {
        return isLabel;
    }

    public void setIsLabel(Integer isLabel) {
        this.isLabel = isLabel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public List<String> getItemValues() {
        return itemValues;
    }

    public void setItemValues(List<String> itemValues) {
        this.itemValues = itemValues;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "MemberInfoItemVo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", tableAttribute='" + tableAttribute + '\'' +
                ", fieldType=" + fieldType +
                ", itemType=" + itemType +
                ", isInterest=" + isInterest +
                ", isLabel=" + isLabel +
                ", status=" + status +
                ", sequence=" + sequence +
                ", itemValues=" + itemValues +
                '}';
    }
}
