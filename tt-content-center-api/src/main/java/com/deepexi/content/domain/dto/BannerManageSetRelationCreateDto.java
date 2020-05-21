package com.deepexi.content.domain.dto;

import javax.validation.constraints.NotNull;

/**
 * @author hongchungen
 */
public class BannerManageSetRelationCreateDto extends AdvertContentSuperDto {
    /**
     * 主键id
     */
    private String id;

    /**
     * banner管理id
     */
    @NotNull(message = "banner管理id不允许为空")
    private String manageId;

    /**
     * banner设置id
     */
    @NotNull(message = "banner设置id不允许为空")
    private String setId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManageId() {
        return manageId;
    }

    public void setManageId(String manageId) {
        this.manageId = manageId;
    }

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }
}
