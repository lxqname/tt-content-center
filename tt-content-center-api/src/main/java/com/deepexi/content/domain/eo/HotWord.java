package com.deepexi.content.domain.eo;




import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.common.domain.eo.SuperEntity;

import java.util.*;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;


/**
 * @desc hot_word
 * @author 
 */
//@ApiModel(description = "coc_hot_word")
@TableName("coc_hot_word")
public class HotWord extends SuperEntity implements Serializable{

    //@ApiModelProperty(value = "热词名称")
    private String name;
    //@ApiModelProperty(value = "搜索次数")
    private Integer  searchNum;
    //@ApiModelProperty(value = "热词来源")
    private String source;
    //@ApiModelProperty(value = "是否标红  0=未标红 1=标红")
    private Integer  tagRed;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
    public void setSearchNum(Integer  searchNum){
        this.searchNum = searchNum;
    }

    public Integer  getSearchNum(){
        return this.searchNum;
    }
    public void setSource(String source){
        this.source = source;
    }

    public String getSource(){
        return this.source;
    }
    public void setTagRed(Integer  tagRed){
        this.tagRed = tagRed;
    }

    public Integer  getTagRed(){
        return this.tagRed;
    }


}

