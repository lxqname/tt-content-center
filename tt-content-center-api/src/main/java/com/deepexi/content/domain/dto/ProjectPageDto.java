package com.deepexi.content.domain.dto;

import com.deepexi.common.domain.eo.SuperEntity;

import java.io.Serializable;

/**
 * @desc project_page
 * @author admin
 */
public class ProjectPageDto extends SuperEntity implements Serializable {

    private String name;

    private Integer  status;

    private String imgUrl;

    private String pId;

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

