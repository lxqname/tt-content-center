package com.deepexi.content.domain.eo;




import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.common.domain.eo.SuperEntity;

import java.util.*;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;


/**
 * @desc member_info_item
 * @author 
 */
//@ApiModel(description = "会员信息项")
@TableName("coc_member_info_item")
public class MemberInfoItem extends SuperEntity implements Serializable{

    //@ApiModelProperty(value = "会员信息项名称")
    private String name;
    //@ApiModelProperty(value = "关联表属性名")
    private String tableAttribute;
    //@ApiModelProperty(value = "字段类型：1=选项-多选,2=选项-单选,3=文本,4=日期,5=省市区,6=数值")
    private Integer  fieldType;
    //@ApiModelProperty(value = "会员信息项类型：1=默认-不可操作,2=默认-可操作,3=自定义")
    private Integer  itemType;
    //@ApiModelProperty(value = "是否为兴趣项; 0=否 1=是")
    private Integer  isInterest;
    //@ApiModelProperty(value = "是否同步为原生标签; 0=否 1=是")
    private Integer  isLabel;
    //@ApiModelProperty(value = "账号状态：0=禁用，1=启用")
    private Integer  status;
    //@ApiModelProperty(value = "序号,排序用")
    private Integer  sequence;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
    public void setTableAttribute(String tableAttribute){
        this.tableAttribute = tableAttribute;
    }

    public String getTableAttribute(){
        return this.tableAttribute;
    }
    public void setFieldType(Integer  fieldType){
        this.fieldType = fieldType;
    }

    public Integer  getFieldType(){
        return this.fieldType;
    }
    public void setItemType(Integer  itemType){
        this.itemType = itemType;
    }

    public Integer  getItemType(){
        return this.itemType;
    }
    public void setIsInterest(Integer  isInterest){
        this.isInterest = isInterest;
    }

    public Integer  getIsInterest(){
        return this.isInterest;
    }
    public void setIsLabel(Integer  isLabel){
        this.isLabel = isLabel;
    }

    public Integer  getIsLabel(){
        return this.isLabel;
    }
    public void setStatus(Integer  status){
        this.status = status;
    }

    public Integer  getStatus(){
        return this.status;
    }
    public void setSequence(Integer  sequence){
        this.sequence = sequence;
    }

    public Integer  getSequence(){
        return this.sequence;
    }


}

