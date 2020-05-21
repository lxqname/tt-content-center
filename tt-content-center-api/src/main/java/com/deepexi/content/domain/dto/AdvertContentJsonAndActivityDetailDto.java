package com.deepexi.content.domain.dto;

import com.deepexi.activity.domain.dto.ActivityDetailDto;
import com.deepexi.content.enums.AdvertTypeEnum;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author admin
 */
public class AdvertContentJsonAndActivityDetailDto implements Serializable {

    /**
     * 商品名称/活动名称
     */
    private String name;

    /**
     * 关联类型 1:商品 2:活动 3:自定义url  4:无
     */
    private AdvertTypeEnum advertType;

    /**
     * 运营分类/活动类型
     */
    private String type;

    /**
     * 商品ID/活动ID
     */
    private String itemId;

    /**
     * 1:领卷活动 2:助力活动
     */
    private Integer itemType;

    /**
     * 关联商户名称
     */
    private String beMerchant;

    /**
     * 前端类目
     */
    private List<List<String>> frontCategoryLinkList;

    /**
     * 自定义URL
     */
    private String divUrl;

    /**
     * 活动开始时间
     */
    private Date activityStartTime;

    /**
     * 活动结束时间
     */
    private Date activityEndTime;

    /**
     * 活动时间
     */
    private String activityTime;

    /**
     * 权重，数字越小，权重越大，排序靠前
     */
    private Integer weight;

    /**
     * 商品，活动图片
     */
    private String imgUrl;

    /**
     * 商品/活动描述
     */
    private String description;
    /**
     * 活动细节
     */
    private ActivityDetailDto activityDetailDto;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AdvertTypeEnum getAdvertType() {
        return advertType;
    }

    public void setAdvertType(AdvertTypeEnum advertType) {
        this.advertType = advertType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public String getBeMerchant() {
        return beMerchant;
    }

    public void setBeMerchant(String beMerchant) {
        this.beMerchant = beMerchant;
    }

    public String getDivUrl() {
        return divUrl;
    }

    public void setDivUrl(String divUrl) {
        this.divUrl = divUrl;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Date getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(Date activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public Date getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Date activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public List<List<String>> getFrontCategoryLinkList() {
        return frontCategoryLinkList;
    }

    public void setFrontCategoryLinkList(List<List<String>> frontCategoryLinkList) {
        this.frontCategoryLinkList = frontCategoryLinkList;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActivityDetailDto getActivityDetailDto() {
        return activityDetailDto;
    }

    public void setActivityDetailDto(ActivityDetailDto activityDetailDto) {
        this.activityDetailDto = activityDetailDto;
    }
}
