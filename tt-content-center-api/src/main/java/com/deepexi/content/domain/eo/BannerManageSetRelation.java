package com.deepexi.content.domain.eo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.common.domain.eo.SuperEntity;

import java.io.Serializable;

/**
 * @author
 */
@TableName("coc_banner_manage_set_relation")
public class BannerManageSetRelation extends SuperEntity implements Serializable {
    /**
     * 主键id
     */
    private String id;

    /**
     * banner管理id
     */
    private String manageId;

    /**
     * banner设置id
     */
    private String setId;

    @Override
    public String getId() {
        return id;
    }

    @Override
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
