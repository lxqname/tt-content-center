package com.deepexi.content.domain.dto;

import com.deepexi.content.domain.eo.PopupManage;
import java.util.Collection;
import java.util.List;
import java.util.Date;
import java.io.Serializable;

/**
 * @desc popup_manage
 * @author 
 */
public class PopupManageDto implements Serializable {
    private String id;

    private String name;

    private String details;

    private Integer  status;

    private String imgUrl;

    private Boolean memberType;

    private String memberLevelId;

    private String memberGroupId;

    private Boolean popupType;

    private String pageId;

    private String pageName;

    private String belongApplication;

    private Integer  triggerEvent;

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

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setDetails(String details){
        this.details = details;
    }

    public String getDetails(){
        return this.details;
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

    public void setMemberType(Boolean memberType){
        this.memberType = memberType;
    }

    public Boolean getMemberType(){
        return this.memberType;
    }

    public void setMemberLevelId(String memberLevelId){
        this.memberLevelId = memberLevelId;
    }

    public String getMemberLevelId(){
        return this.memberLevelId;
    }

    public void setMemberGroupId(String memberGroupId){
        this.memberGroupId = memberGroupId;
    }

    public String getMemberGroupId(){
        return this.memberGroupId;
    }

    public void setPopupType(Boolean popupType){
        this.popupType = popupType;
    }

    public Boolean getPopupType(){
        return this.popupType;
    }

    public void setPageId(String pageId){
        this.pageId = pageId;
    }

    public String getPageId(){
        return this.pageId;
    }

    public void setPageName(String pageName){
        this.pageName = pageName;
    }

    public String getPageName(){
        return this.pageName;
    }

    public void setBelongApplication(String belongApplication){
        this.belongApplication = belongApplication;
    }

    public String getBelongApplication(){
        return this.belongApplication;
    }

    public void setTriggerEvent(Integer  triggerEvent){
        this.triggerEvent = triggerEvent;
    }

    public Integer  getTriggerEvent(){
        return this.triggerEvent;
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

