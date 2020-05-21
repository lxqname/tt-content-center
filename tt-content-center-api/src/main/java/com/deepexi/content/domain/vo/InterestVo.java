package com.deepexi.content.domain.vo;

/**
 * @author ：wujie
 * @date ：Created in 2019/9/18 14:14
 * @description：兴趣引导项的Vo
 * @version:1.0.0
 */
public class InterestVo {

    /**
     * id主键
     */
    private String id;

    /**
     * 兴趣项的名称
     */
    private String interestName;

    /**
     * 兴趣项的类型
     */
    private String interestType;

    /**
     * 兴趣项的值
     */
    private String interestValue;

    /**
     * 排序序号(后端用)
     */
    private Integer level;

    /**
     * 排序序号(前端用)
     */
    private Integer orderNum;

    /**
     * 引导语,若引导语存在，前端页面的“兴趣项名称”一栏应显示引导语
     * 若引导语为""或为null,则前端页面“兴趣项名称”一栏显示兴趣项
     * @return
     */
    private String guideName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public String getInterestType() {
        return interestType;
    }

    public void setInterestType(String interestType) {
        this.interestType = interestType;
    }

    public String getInterestValue() {
        return interestValue;
    }

    public void setInterestValue(String interestValue) {
        this.interestValue = interestValue;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
