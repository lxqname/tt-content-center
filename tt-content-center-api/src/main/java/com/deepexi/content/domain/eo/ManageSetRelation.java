package com.deepexi.content.domain.eo;


import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.common.domain.eo.SuperEntity;

import java.util.*;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;


/**
 * @desc manage_set_relation
 * @author 
 */
//@ApiModel(description = "coc_manage_set_relation")
@TableName("coc_manage_set_relation")
public class ManageSetRelation extends SuperEntity implements Serializable{

    //@ApiModelProperty(value = "banner管理id")
    private String manageId;
    //@ApiModelProperty(value = "banner设置id")
    private String setId;

    public void setManageId(String manageId){
        this.manageId = manageId;
    }

    public String getManageId(){
        return this.manageId;
    }
    public void setSetId(String setId){
        this.setId = setId;
    }

    public String getSetId(){
        return this.setId;
    }


}

