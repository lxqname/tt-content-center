package com.deepexi.content.domain.vo;

import com.deepexi.content.domain.dto.AdvertContentSuperDto;
import com.deepexi.content.enums.AdvertTypeEnum;
import com.deepexi.content.enums.BelongApplicationTypeEnum;
import com.deepexi.content.enums.ContentSetTypeEnum;
import com.deepexi.content.enums.TriggerEventTypeEnum;

import java.util.List;
/**
 * @Author:LuFeng
 */
public class PopupManageDetailVo extends AdvertContentSuperDto {

    private String id;

    /**
     * 弹窗名称
     */
    private String name;

    /**
     * 弹窗文案
     */
    private String details;

    /**
     * 状态0：启用，1：禁用
     */
    private Integer status;

    /**
     * 弹窗海报URL
     */
    private String imgUrl;

    /**
     * 推送人群类型：0:全部 1：已注册 2：未注册
     */
    private Integer memberType;

    /**
     * 弹窗类型 1：常规活动弹窗 2：新会员弹窗
     */
    private Integer popupType;

    /**
     * 关联类型
     */
    private AdvertTypeEnum advertTypeEnum;

    /**
     * 会员分组ID
     */
    private String memberGroupId;

    /**
     * 会员等级
     */
    private List<String> levelIds;

    /**
     * 所属应用
     */
    private BelongApplicationTypeEnum applicationTypeEnum;

    /**
     * 弹窗页面ID
     */
    private String pageId;

    /**
     * 弹窗页面名称
     */
    private String pageName;

    /**
     * 触发事件
     */
    private TriggerEventTypeEnum triggerEventTypeEnum;

    /**
     * 关联设置类型
     */
    private ContentSetTypeEnum setTypeEnum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public AdvertTypeEnum getAdvertTypeEnum() {
        return advertTypeEnum;
    }

    public void setAdvertTypeEnum(AdvertTypeEnum advertTypeEnum) {
        this.advertTypeEnum = advertTypeEnum;
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

    public ContentSetTypeEnum getSetTypeEnum() {
        return setTypeEnum;
    }

    public void setSetTypeEnum(ContentSetTypeEnum setTypeEnum) {
        this.setTypeEnum = setTypeEnum;
    }

    @Override
    public String toString() {
        return "PopupManageDetailVo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", status=" + status +
                ", imgUrl='" + imgUrl + '\'' +
                ", memberType=" + memberType +
                ", popupType=" + popupType +
                ", advertTypeEnum=" + advertTypeEnum +
                ", memberGroupId='" + memberGroupId + '\'' +
                ", levelIds=" + levelIds +
                ", applicationTypeEnum=" + applicationTypeEnum +
                ", pageId='" + pageId + '\'' +
                ", pageName='" + pageName + '\'' +
                ", triggerEventTypeEnum=" + triggerEventTypeEnum +
                ", setTypeEnum=" + setTypeEnum +
                '}';
    }
}
