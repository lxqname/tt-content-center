package com.deepexi.content.domain.eo;


import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.common.domain.eo.SuperEntity;

import java.util.*;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;


/**
 * @author honghcungen
 */
//@ApiModel(description = "专题页")
@TableName("coc_project_page")
public class ProjectPage extends SuperEntity implements Serializable {

    //@ApiModelProperty(value = "专题页名称")
    private String name;
    //@ApiModelProperty(value = "状态，0=启用，1=禁用")
    private Integer  status;
    //@ApiModelProperty(value = "专题广告图")
    private String imgUrl;

    /**
     * 父级id
     */
    private String pId;

    /**
     * 权重
     */
    private Integer weight;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
    public void setStatus(Integer  status){
        this.status = status;
    }

    public Integer  getStatus(){
        return this.status;
    }
    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }

    public String getImgUrl(){
        return this.imgUrl;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}

