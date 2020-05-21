package com.deepexi.content.domain.dto;

import com.deepexi.content.domain.eo.ManageSetRelation;
import java.util.Collection;
import java.util.List;
import java.util.Date;
import java.io.Serializable;

/**
 * @desc manage_set_relation
 * @author 
 */
public class ManageSetRelationDto implements Serializable {
    private String id;

    private String manageId;

    private String setId;

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

    public void setManageId(String manageId){
        this.manageId = manageId;
    }

    public String getManageId(){
        return this.manageId;
    }

    public void setSetId(String setId){
        this.setId = setId;
    }

    public String getSetId(){
        return this.setId;
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

