package com.deepexi.content.domain.eo;




import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.common.domain.eo.SuperEntity;

import java.util.*;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;


/**
 * @desc hot_word_count
 * @author 
 */
//@ApiModel(description = "coc_hot_word_count")
@TableName("coc_hot_word_count")
public class HotWordCount extends SuperEntity implements Serializable{

    //@ApiModelProperty(value = "0=未展示 1=展示中")
    private Integer  onShow;
    //@ApiModelProperty(value = "搜索次数")
    private Integer  searchNum;
    //@ApiModelProperty(value = "使用json,热词集(热词1;热词2...)")
    private String hotWords;
    //@ApiModelProperty(value = "展示日期")
    private Date showDate;

    public void setOnShow(Integer  onShow){
        this.onShow = onShow;
    }

    public Integer  getOnShow(){
        return this.onShow;
    }
    public void setSearchNum(Integer  searchNum){
        this.searchNum = searchNum;
    }

    public Integer  getSearchNum(){
        return this.searchNum;
    }
    public void setHotWords(String hotWords){
        this.hotWords = hotWords;
    }

    public String getHotWords(){
        return this.hotWords;
    }
    public void setShowDate(Date showDate){
        this.showDate = showDate;
    }

    public Date getShowDate(){
        return this.showDate;
    }


}

