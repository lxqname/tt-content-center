package com.deepexi.content.domain.dto;

import com.deepexi.common.domain.eo.SuperEntity;

import java.io.Serializable;

/**
 * @desc banner_manage_set_relation
 * @author hongchungen
 */
public class BannerManageSetRelationDto extends SuperEntity implements Serializable {

    private String manageId;

    private String setId;

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
