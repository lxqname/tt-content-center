package com.deepexi.content.domain.dto;


import java.io.Serializable;
import java.util.List;

/**
 * @author ：wujie
 * @date ：Created in 2019/10/8 15:03
 * @description：会员选择的兴趣项的封装对象
 * @version: 1.0.0
 */
public class MemberSaveInterestDto implements Serializable {

    /**
     * 兴趣项的id
     */
    private String id;

    /**
     * 兴趣项名称
     */
    private String interestName;

    /**
     * 兴趣项来源类型,自定义or衍生于系统预置(3为自定义2为来源于预置)
     */
    private Integer interestItemType;

    /**
     * 关联表属性名
     */
    private String tableAttribute;

    /**
     * 兴趣项的值,字符串集合
     */
    private List<String> values;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {

        this.values = values;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

    public Integer getInterestItemType() {
        return interestItemType;
    }

    public void setInterestItemType(Integer interestItemType) {
        this.interestItemType = interestItemType;
    }

    public String getTableAttribute() {
        return tableAttribute;
    }

    public void setTableAttribute(String tableAttribute) {
        this.tableAttribute = tableAttribute;
    }

    @Override
    public String toString() {
        return "MemberSaveInterestDto{" +
                "id='" + id + '\'' +
                ", interestName='" + interestName + '\'' +
                ", interestItemType=" + interestItemType +
                ", values=" + values +
                '}';
    }
}
