package com.deepexi.content.domain.vo;

import java.util.Date;

/**
 * @author hongchungen
 * @date 2019/09/27 16:08
 */
public class BannerSetVo {
    /**
     * banner名称
     */
    private String name;

    /** banner简称 */
    private String abbreviate;

    /** banner图片 */
    private String imgUrl;

    /** banner缩略图 */
    private String thumbnail;

    /**
     * 上架时间
     */
    private Date startTime;

    /**
     * 下架时间
     */
    private Date endTime;

    /**
     * 状态，0启用，1禁用
     */
    private Integer  status;

    /**
     * 权重。越大排序靠前
     */
    private Integer  weight;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getAbbreviate() {
        return abbreviate;
    }

    public void setAbbreviate(String abbreviate) {
        this.abbreviate = abbreviate;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
