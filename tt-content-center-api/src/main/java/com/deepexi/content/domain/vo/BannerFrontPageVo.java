package com.deepexi.content.domain.vo;

import com.deepexi.content.domain.dto.AdvertContentSuperDto;
import com.deepexi.content.enums.AdvertTypeEnum;

/**
 * @author hongchungen
 */
public class BannerFrontPageVo extends AdvertContentSuperDto {

    /**
     * bannerId
     */
    private String id;

    /**
     * banner大图
     */
    private String imgUrl;

    /**
     * banner小图
     */
    private String thumbnail;

    /**
     * banner简称
     */
    private String abbreviate;

    /**
     * 关联url
     */
    private String divUrl;

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDivUrl() {
        return divUrl;
    }

    public void setDivUrl(String divUrl) {
        this.divUrl = divUrl;
    }

    public String getAbbreviate() {
        return abbreviate;
    }

    public void setAbbreviate(String abbreviate) {
        this.abbreviate = abbreviate;
    }

    public AdvertTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(AdvertTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

}
