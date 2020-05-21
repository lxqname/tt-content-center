package com.deepexi.content.domain.eo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.common.domain.eo.SuperEntity;

import java.util.*;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;


/**
 * @desc popup_manage
 * @author 
 */
//@ApiModel(description = "coc_popup_manage")
@TableName("coc_popup_manage")
public class PopupManage extends SuperEntity implements Serializable{

    //@ApiModelProperty(value = "弹窗名称")
    private String name;
    //@ApiModelProperty(value = "弹窗文案")
    private String details;
    //@ApiModelProperty(value = "状态 0:启动 1:禁用")
    private Integer  status;
    //@ApiModelProperty(value = "弹窗海报URL")
    private String imgUrl;
    //@ApiModelProperty(value = "推荐人群类型   0：全部 1：已注册 2：未注册 ")
    private Integer memberType;
    //@ApiModelProperty(value = "会员分组ID")
    private String memberGroupId;
    //@ApiModelProperty(value = "弹窗类型 1：常规活动弹窗 2：新会员弹窗")
    private Integer popupType;
    //@ApiModelProperty(value = "弹窗页面ID")
    private String pageId;
    //@ApiModelProperty(value = "弹窗页面名称")
    private String pageName;
    //@ApiModelProperty(value = "所属应用")
    private String belongApplication;
    //@ApiModelProperty(value = "触发事件 1：当日首次登录 2：当日首次进入")
    private Integer  triggerEvent;

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

    public Integer getMemberType() {
        return memberType;
    }

    public void setMemberType(Integer memberType) {
        this.memberType = memberType;
    }

    public Integer getPopupType() {
        return popupType;
    }

    public void setPopupType(Integer popupType) {
        this.popupType = popupType;
    }

    public void setMemberGroupId(String memberGroupId){
        this.memberGroupId = memberGroupId;
    }

    public String getMemberGroupId(){
        return this.memberGroupId;
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


}

