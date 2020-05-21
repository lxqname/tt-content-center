package com.deepexi.content.domain.eo;


import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.common.domain.eo.SuperEntity;


import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;


/**
 * @desc member_choose_interest_value
 * @author 
 */
//@ApiModel(description = "coc_member_choose_interest_value")
@TableName("coc_member_choose_interest_value")
public class MemberChooseInterestValue extends SuperEntity implements Serializable{

    //@ApiModelProperty(value = "会员所填写的兴趣项的值")
    private String value;

    public void setValue(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }


}

