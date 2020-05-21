package com.deepexi.content.domain.vo;

import io.swagger.models.auth.In;

import java.util.List;

/**
 * @author ：wujie
 * @date ：Created in 2019/9/17 19:26
 * @description：会员信息项展示到兴趣引导项选择界面的Vo
 * @version: 1.0.0
 */
public class MemberToInterestVo {

    /**
     * 会员信息项的id
     */
    private String id;

    /**
     * 会员信息项名称
     */
    private String name;

    /**
     * 字段类型 1=选项-多选,2=选项-单选,3=文本,4=日期,5=省市区,6=数值
     */
    private Integer fieldType;

    /**
     * 会员信息项类型 1=默认-不可操作,2=默认-可操作,3=自定义
     */
    private Integer itemType;

    /**
     * 会员信息项的值
     */
    private List<String> itemValues;

    /**
     * 状态 0=禁用，1=启用
     */
    private Integer status;

    public MemberToInterestVo() {
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

    public List<String> getItemValues() {
        return itemValues;
    }

    public void setItemValues(List<String> itemValues) {
        this.itemValues = itemValues;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MemberToInterestVo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", fieldType=" + fieldType +
                ", itemType=" + itemType +
                ", itemValues=" + itemValues +
                ", status=" + status +
                '}';
    }
}
