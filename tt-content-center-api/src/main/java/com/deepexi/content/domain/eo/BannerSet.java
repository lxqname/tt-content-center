package com.deepexi.content.domain.eo;




import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.common.domain.eo.SuperEntity;

import java.util.*;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;


/**
 * @desc banner_set
 * @author 
 */
//@ApiModel(description = "banner设置")
@TableName("coc_banner_set")
public class BannerSet extends SuperEntity implements Serializable{

    //@ApiModelProperty(value = "banner名称")
    private String name;
    /** banner简称 */
    private String abbreviate;
    //@ApiModelProperty(value = "banner图片")
    private String imgUrl;
    /** 缩略图*/
    private String thumbnail;
    //@ApiModelProperty(value = "上架开始时间")
    private Date startTime;
    //@ApiModelProperty(value = "上架结束时间")
    private Date endTime;
    //@ApiModelProperty(value = "状态，0=启用，1=禁用")
    private Integer  status;
    //@ApiModelProperty(value = "权重，越大排序越前")
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

