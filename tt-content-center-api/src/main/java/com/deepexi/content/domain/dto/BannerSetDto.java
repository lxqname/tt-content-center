package com.deepexi.content.domain.dto;

import com.deepexi.common.domain.eo.SuperEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc banner_set
 * @author 
 */
public class BannerSetDto extends SuperEntity implements Serializable {
    /** banner名称 */
    private String name;
    /** banner简称 */
    private String abbreviate;
    /** banner大图 */
    private String imgUrl;
    /** banner缩略图 */
    private String thumbnail;
    /** 上架时间 */
    private Date startTime;
    /** 下架时间 */
    private Date endTime;
    /** 状态，0启用，1禁用 */
    private Integer  status;
    /** 权重，数字越小权重越大，排序靠前 */
    private Integer  weight;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }

    public String getImgUrl(){
        return this.imgUrl;
    }

    public void setStartTime(Date startTime){
        this.startTime = startTime;
    }

    public Date getStartTime(){
        return this.startTime;
    }

    public void setEndTime(Date endTime){
        this.endTime = endTime;
    }

    public Date getEndTime(){
        return this.endTime;
    }

    public void setStatus(Integer  status){
        this.status = status;
    }

    public Integer  getStatus(){
        return this.status;
    }

    public void setWeight(Integer  weight){
        this.weight = weight;
    }

    public Integer  getWeight(){
        return this.weight;
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

