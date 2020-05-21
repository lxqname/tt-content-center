package com.deepexi.content.domain.eo;
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
//@ApiModel(description = "coc_popup_member_level_relation")
@TableName("coc_popup_member_level_relation")
public class PopupMemberLevelRelation extends SuperEntity implements Serializable{
    //@ApiModelProperty(value = "弹窗ID")
    private String popupId;
    //@ApiModelProperty(value = "会员等级ID")
    private String memberLevelId;

    public String getPopupId() {
        return popupId;
    }

    public void setPopupId(String popupId) {
        this.popupId = popupId;
    }

    public String getMemberLevelId() {
        return memberLevelId;
    }

    public void setMemberLevelId(String memberLevelId) {
        this.memberLevelId = memberLevelId;
    }

    @Override
    public String toString() {
        return "PopupMemberLevelRelation{" +
                "popupId='" + popupId + '\'' +
                ", memberLevelId='" + memberLevelId + '\'' +
                '}';
    }
}
