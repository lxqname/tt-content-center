package com.deepexi.content.domain.eo;


import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.common.domain.eo.SuperEntity;

import java.util.*;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;


/**
 * @desc interest_member_relation
 * @author 
 */
//@ApiModel(description = "coc_interest_member_relation")
@TableName("coc_interest_member_relation")
public class InterestMemberRelation extends SuperEntity implements Serializable{

    //@ApiModelProperty(value = "兴趣项id")
    private String interestId;
    //@ApiModelProperty(value = "会员兴趣项id")
    private String memberInformationId;

    public void setInterestId(String interestId){
        this.interestId = interestId;
    }

    public String getInterestId(){
        return this.interestId;
    }
    public void setMemberInformationId(String memberInformationId){
        this.memberInformationId = memberInformationId;
    }

    public String getMemberInformationId(){
        return this.memberInformationId;
    }


}

