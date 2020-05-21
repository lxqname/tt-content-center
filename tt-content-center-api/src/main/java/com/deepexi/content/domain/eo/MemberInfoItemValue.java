package com.deepexi.content.domain.eo;




import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.common.domain.eo.SuperEntity;

import java.util.*;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;


/**
 * @desc member_info_item_value
 * @author 
 */
//@ApiModel(description = "会员信息项值")
@TableName("coc_member_info_item_value")
public class MemberInfoItemValue extends SuperEntity implements Serializable{

    //@ApiModelProperty(value = "会员信息项值")
    private String itemValue;

    public void setItemValue(String itemValue){
        this.itemValue = itemValue;
    }

    public String getItemValue(){
        return this.itemValue;
    }


}

