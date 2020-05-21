package com.deepexi.content.domain.eo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.common.domain.eo.SuperEntity;

import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

/**
 * @desc advert_content_relation
 * @author 
 */
//@ApiModel(description = "关联广告内容表的关系表")
@TableName("coc_advert_content_relation")
public class AdvertContentRelation extends SuperEntity implements Serializable{

    //@ApiModelProperty(value = "设置类型 1:BannerImg设置 2:专题页设置 3:楼层页设置  4:弹窗设置")
    private Integer  setType;
    //@ApiModelProperty(value = "设置类型ID")
    private String setTypeId;
    //@ApiModelProperty(value = "关联类型 1:商品 2:活动 3:自定义url  4:无")
    private Integer  advertType;
    //@ApiModelProperty(value = "广告内容ID")
    private String advertId;
    //@ApiModelProperty(value = "权重，越大排序越前")
    private Integer  weight;

    public void setSetType(Integer  setType){
        this.setType = setType;
    }

    public Integer  getSetType(){
        return this.setType;
    }
    public void setSetTypeId(String setTypeId){
        this.setTypeId = setTypeId;
    }

    public String getSetTypeId(){
        return this.setTypeId;
    }
    public void setAdvertType(Integer  advertType){
        this.advertType = advertType;
    }

    public Integer  getAdvertType(){
        return this.advertType;
    }
    public void setAdvertId(String advertId){
        this.advertId = advertId;
    }

    public String getAdvertId(){
        return this.advertId;
    }
    public void setWeight(Integer  weight){
        this.weight = weight;
    }

    public Integer  getWeight(){
        return this.weight;
    }



}

