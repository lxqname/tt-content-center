package com.deepexi.content.domain.dto;

import com.deepexi.content.enums.AdvertTypeEnum;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author hongchungen
 * @date 2019/09/23 11:58
 */
public class BannerSetCreateDto extends AdvertContentSuperDto implements Serializable {

    /**
     * 主键id
     */
    private String id;

    /**
     * banner名称
     */
    @NotNull(message = "banner名称不允许为空")
    private String name;

    /** banner简称 */
    @NotNull(message = "banner简称不允许为空")
    private String abbreviate;

    /** banner缩略图 */
    @NotNull(message = "banner缩略图不允许为空")
    private String thumbnail;

    /**
     * banner图片
     */
    @NotNull(message = "banner图片不允许为空")
    private String imgUrl;

    /**
     * 上架时间
     */
    @NotNull(message = "上架时间不允许为空")
    private Date startTime;

    /**
     * 下架时间
     */
    @NotNull(message = "下架时间不允许为空")
    private Date endTime;

    /**
     * 状态，默认启用，0启用，1禁用
     */
    private Integer  status;

    /**
     * 权重
     */
    private Integer  weight;

    /**
     * 关联类型
     */
    private AdvertTypeEnum typeEnum;

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

    public AdvertTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(AdvertTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
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
