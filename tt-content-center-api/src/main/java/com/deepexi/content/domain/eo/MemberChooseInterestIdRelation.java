package com.deepexi.content.domain.eo;


import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.common.domain.eo.SuperEntity;


import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;


/**
 * @desc member_choose_interest_id_relation
 * @author 
 */
//@ApiModel(description = "coc_member_choose_interest_id_relation")
@TableName("coc_member_choose_interest_id_relation")
public class MemberChooseInterestIdRelation extends SuperEntity implements Serializable{

    //@ApiModelProperty(value = "会员所选兴趣项值的id")
    private String memberChooseValueId;
    //@ApiModelProperty(value = "会员所选兴趣项id")
    private String memberChooseId;
    //@ApiModelProperty(value = "会员id")
    private String memberId;

    public void setMemberChooseValueId(String memberChooseValueId){
        this.memberChooseValueId = memberChooseValueId;
    }

    public String getMemberChooseValueId(){
        return this.memberChooseValueId;
    }
    public void setMemberChooseId(String memberChooseId){
        this.memberChooseId = memberChooseId;
    }

    public String getMemberChooseId(){
        return this.memberChooseId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}

