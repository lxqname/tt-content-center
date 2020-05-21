package com.deepexi.content.domain.dto;

import com.deepexi.content.domain.eo.AdvertContentRelation;
import java.util.Collection;
import java.util.List;
import java.util.Date;
import java.io.Serializable;

/**
 * @desc advert_content_relation
 * @author 
 */
public class AdvertContentRelationDto implements Serializable {
    private String id;

    private Integer  setType;

    private String setTypeId;

    private Integer  advertType;

    private String advertId;

    private Integer  weight;

    private String tenantCode;

    private Date createdAt;

    private String createdBy;

    private Date updatedAt;

    private String updatedBy;

    private Integer  version;

    private Boolean dr;


    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

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

    public void setTenantCode(String tenantCode){
        this.tenantCode = tenantCode;
    }

    public String getTenantCode(){
        return this.tenantCode;
    }

    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt;
    }

    public Date getCreatedAt(){
        return this.createdAt;
    }

    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }

    public String getCreatedBy(){
        return this.createdBy;
    }

    public void setUpdatedAt(Date updatedAt){
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt(){
        return this.updatedAt;
    }

    public void setUpdatedBy(String updatedBy){
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy(){
        return this.updatedBy;
    }

    public void setVersion(Integer  version){
        this.version = version;
    }

    public Integer  getVersion(){
        return this.version;
    }

    public void setDr(Boolean dr){
        this.dr = dr;
    }

    public Boolean getDr(){
        return this.dr;
    }
}

