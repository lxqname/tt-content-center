package com.deepexi.content.domain.dto;

import com.deepexi.content.domain.eo.Interest;
import java.util.Collection;
import java.util.List;
import java.util.Date;
import java.io.Serializable;

/**
 * @desc interest
 * @author 
 */
public class InterestDto implements Serializable {
    /**
     * id由数据库生成
     */
    private String id;

    /**
     * 对应会员项名称
     */
    private String interestName;

    /**
     * 对应会员项的字段类型
     */
    private String interestType;

    /**
     * 对应会员项的值
     */
    private String interestValue;

    /**
     * 对应会员项的状态
     */
    private Integer  status;

    /**
     * 查询数据库自增得到
     */
    private Integer  level;

    /**
     * 引导语
     */
    private String guideName;

    private String tenantCode;

    private Date createdAt;

    private String createdBy;

    private Date updatedAt;

    private String updatedBy;

    private Integer  version;

    private Integer  dr;

    /**
     * 会员信息项的id
     */
    private String memberInfoId;

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setInterestName(String interestName){
        this.interestName = interestName;
    }

    public String getInterestName(){
        return this.interestName;
    }

    public void setInterestType(String interestType){
        this.interestType = interestType;
    }

    public String getInterestType(){
        return this.interestType;
    }

    public void setInterestValue(String interestValue){
        this.interestValue = interestValue;
    }

    public String getInterestValue(){
        return this.interestValue;
    }

    public void setStatus(Integer  status){
        this.status = status;
    }

    public Integer  getStatus(){
        return this.status;
    }

    public void setLevel(Integer  level){
        this.level = level;
    }

    public Integer  getLevel(){
        return this.level;
    }

    public void setGuideName(String guideName){
        this.guideName = guideName;
    }

    public String getGuideName(){
        return this.guideName;
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

    public String getMemberInfoId() {
        return memberInfoId;
    }

    public void setMemberInfoId(String memberInfoId) {
        this.memberInfoId = memberInfoId;
    }
}

