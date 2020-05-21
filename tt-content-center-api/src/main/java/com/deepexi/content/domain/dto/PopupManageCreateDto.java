package com.deepexi.content.domain.dto;

import com.deepexi.content.enums.AdvertTypeEnum;
import com.deepexi.content.enums.BelongApplicationTypeEnum;
import com.deepexi.content.enums.ContentSetTypeEnum;
import com.deepexi.content.enums.TriggerEventTypeEnum;

import javax.validation.constraints.NotNull;
import java.util.List;
/**
 * @Author:LuFeng
 */
public class PopupManageCreateDto extends AdvertContentSuperDto {

    /**
     * 弹窗名称
     */
    @NotNull(message = "弹窗名称不允许为空")
    private String name;

    /**
     * 弹窗文案
     */
    private String details;

    /**
     * 弹窗海报URL
     */
    @NotNull(message = "弹窗海报不允许为空")
    private String imgUrl;

    /**
     * 关联类型
     */
    @NotNull(message = "关联类型不能为空")
    private AdvertTypeEnum advertTypeEnum;

    /**
     * 推送人群类型：0:全部 1：已注册 2：未注册
     */
    @NotNull(message = "推送人群类型不能为空")
    private Integer memberType;

    /**
     * 会员分组ID
     */
    private String memberGroupId;

    /**
     * 会员等级列表
     */
    private List<String> levelIds;

    /**
     * 弹窗类型 1：常规活动弹窗 2：新会员弹窗
     */
    @NotNull(message = "所属类型不能为空")
    private Integer popupType;

    /**
     * 所属应用
     */
    @NotNull(message = "所属应用不允许为空")
    private BelongApplicationTypeEnum applicationTypeEnum;

    /**
     * 弹窗页面ID
     */
    private String pageId;

    /**
     * 弹窗页面名称
     */
    @NotNull(message = "弹窗页面名称不允许为空")
    private String pageName;

    /**
     * 触发事件
     */
    @NotNull(message = "触发事件不允许为空")
    private TriggerEventTypeEnum triggerEventTypeEnum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public AdvertTypeEnum getAdvertTypeEnum() {
        return advertTypeEnum;
    }

    public void setAdvertTypeEnum(AdvertTypeEnum advertTypeEnum) {
        this.advertTypeEnum = advertTypeEnum;
    }

    public Integer getMemberType() {
        return memberType;
    }

    public void setMemberType(Integer memberType) {
        this.memberType = memberType;
    }

    public String getMemberGroupId() {
        return memberGroupId;
    }

    public void setMemberGroupId(String memberGroupId) {
        this.memberGroupId = memberGroupId;
    }

    public List<String> getLevelIds() {
        return levelIds;
    }

    public void setLevelIds(List<String> levelIds) {
        this.levelIds = levelIds;
    }

    public Integer getPopupType() {
        return popupType;
    }

    public void setPopupType(Integer popupType) {
        this.popupType = popupType;
    }

    public BelongApplicationTypeEnum getApplicationTypeEnum() {
        return applicationTypeEnum;
    }

    public void setApplicationTypeEnum(BelongApplicationTypeEnum applicationTypeEnum) {
        this.applicationTypeEnum = applicationTypeEnum;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public TriggerEventTypeEnum getTriggerEventTypeEnum() {
        return triggerEventTypeEnum;
    }

    public void setTriggerEventTypeEnum(TriggerEventTypeEnum triggerEventTypeEnum) {
        this.triggerEventTypeEnum = triggerEventTypeEnum;
    }

    @Override
    public String toString() {
        return "PopupManageCreateDto{" +
                "name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", advertTypeEnum=" + advertTypeEnum +
                ", memberType=" + memberType +
                ", memberGroupId='" + memberGroupId + '\'' +
                ", levelIds=" + levelIds +
                ", popupType=" + popupType +
                ", applicationTypeEnum=" + applicationTypeEnum +
                ", pageId='" + pageId + '\'' +
                ", pageName='" + pageName + '\'' +
                ", triggerEventTypeEnum=" + triggerEventTypeEnum +
                '}';
    }
}
