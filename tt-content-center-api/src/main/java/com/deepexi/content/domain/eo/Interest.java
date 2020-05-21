package com.deepexi.content.domain.eo;


import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.common.domain.eo.SuperEntity;

import java.util.*;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;


/**
 * @desc interest
 * @author 
 */
//@ApiModel(description = "coc_interest")
@TableName("coc_interest")
public class Interest extends SuperEntity implements Serializable{

    //@ApiModelProperty(value = "兴趣项名称")
    private String interestName;
    //@ApiModelProperty(value = "兴趣项类型")
    private String interestType;
    //@ApiModelProperty(value = "兴趣项值")
    private String interestValue;
    //@ApiModelProperty(value = "状态(和会员信息项的同步)  0代表禁用 1代表启用 禁用状态不可显示在页面")
    private Integer  status;
    //@ApiModelProperty(value = "排序等级")
    private Integer  level;
    //@ApiModelProperty(value = "引导语")
    private String guideName;

    public void setInterestName(String interestName){
        this.interestName = interestName;
    }

    public String getInterestName(){
        return this.interestName;
    }
    public void setInterestType(String interestType){
        this.interestType = interestType;
    }

    public String getInterestType(){
        return this.interestType;
    }
    public void setInterestValue(String interestValue){
        this.interestValue = interestValue;
    }

    public String getInterestValue(){
        return this.interestValue;
    }
    public void setStatus(Integer  status){
        this.status = status;
    }

    public Integer  getStatus(){
        return this.status;
    }
    public void setLevel(Integer  level){
        this.level = level;
    }

    public Integer  getLevel(){
        return this.level;
    }
    public void setGuideName(String guideName){
        this.guideName = guideName;
    }

    public String getGuideName(){
        return this.guideName;
    }


}

