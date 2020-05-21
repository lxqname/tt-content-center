package com.deepexi.content.domain.vo;

import java.util.List;

/**
 * @author ：wujie
 * @date ：Created in 2019/9/26 15:32
 * @description：兴趣项展示到H5的Vo
 * @version: 1.0.0
 */
public class InterestToH5Vo {
    /**
     * 兴趣项的主键id
     */
    private String id;

    /**
     * 兴趣项类型
     * 1=选项-多选,2=选项-单选,3=文本,4=日期,5=省市区,6=数值
     */
    private Integer interestType;

    /**
     * 会员项/信息项的类型(默认or自定义)3代表自定义其他均为系统预置
     * 用以区分兴趣项是自定义还是衍生于系统预置
     */
    private Integer interestItemType;

    /**
     * 兴趣项引导语
     */
    private String guideName;

    /**
     * 兴趣项名称
     */
    private String interestName;

    /**
     *关联表属性名
     */
    private String tableAttribute;

    /**
     * 兴趣项值
     */
    private List<String> values;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getInterestType() {
        return interestType;
    }

    public void setInterestType(Integer interestType) {
        this.interestType = interestType;
    }

    public String getTableAttribute() {
        return tableAttribute;
    }

    public void setTableAttribute(String tableAttribute) {
        this.tableAttribute = tableAttribute;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public Integer getInterestItemType() {
        return interestItemType;
    }

    public void setInterestItemType(Integer interestItemType) {
        this.interestItemType = interestItemType;
    }
}
