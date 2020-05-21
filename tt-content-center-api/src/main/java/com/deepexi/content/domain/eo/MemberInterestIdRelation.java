package com.deepexi.content.domain.eo;


import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.common.domain.eo.SuperEntity;


import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;


/**
 * @desc member_interest_id_relation
 * @author 
 */
//@ApiModel(description = "coc_member_interest_id_relation")
@TableName("coc_member_interest_id_relation")
public class MemberInterestIdRelation extends SuperEntity implements Serializable{

    //@ApiModelProperty(value = "会员id")
    private String memberId;
    //@ApiModelProperty(value = "兴趣项id")
    private String interestId;
    //@ApiModelProperty(value = "状态值 0=隐藏 1=显示")
    private Integer  status;

    public void setMemberId(String memberId){
        this.memberId = memberId;
    }

    public String getMemberId(){
        return this.memberId;
    }
    public void setInterestId(String interestId){
        this.interestId = interestId;
    }

    public String getInterestId(){
        return this.interestId;
    }
    public void setStatus(Integer  status){
        this.status = status;
    }

    public Integer  getStatus(){
        return this.status;
    }

}

