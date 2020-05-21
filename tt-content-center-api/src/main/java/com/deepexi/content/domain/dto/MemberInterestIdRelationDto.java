package com.deepexi.content.domain.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc member_interest_id_relation
 * @author 
 */
public class MemberInterestIdRelationDto implements Serializable {
    private String id;

    private String memberId;

    private String interestId;

    private Integer  status;

    private String tenantCode;

    private Date createdAt;

    private String createdBy;

    private Date updatedAt;

    private String updatedBy;

    private Integer  version;

    private Integer  dr;


    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setMemberId(String memberId){
        this.memberId = memberId;
    }

    public String getMemberId(){
        return this.memberId;
    }

    public void setInterestId(String interestId){
        this.interestId = interestId;
    }

    public String getInterestId(){
        return this.interestId;
    }

    public void setStatus(Integer  status){
        this.status = status;
    }

    public Integer  getStatus(){
        return this.status;
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

    public void setDr(Integer  dr){
        this.dr = dr;
    }

    public Integer  getDr(){
        return this.dr;
    }
}

