package com.deepexi.content.domain.eo;




import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.common.domain.eo.SuperEntity;

import java.util.*;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;


/**
 * @desc member_info_item_value_relation
 * @author 
 */
//@ApiModel(description = "usc_member_info_item_value_relation")
@TableName("coc_member_info_item_value_relation")
public class MemberInfoItemValueRelation extends SuperEntity implements Serializable{

    //@ApiModelProperty(value = "会员信息项的id")
    private String itemId;
    //@ApiModelProperty(value = "会员信息项的值的id")
    private String itemValueId;

    public void setItemId(String itemId){
        this.itemId = itemId;
    }

    public String getItemId(){
        return this.itemId;
    }
    public void setItemValueId(String itemValueId){
        this.itemValueId = itemValueId;
    }

    public String getItemValueId(){
        return this.itemValueId;
    }


}

