package com.deepexi.content.domain.eo;


import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.common.domain.eo.SuperEntity;

import java.util.*;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;



/**
 * @desc hotword_goodslables_relation
 * @author 
 */
//@ApiModel(description = "coc_hotword_goodslables_relation")
@TableName("coc_hotword_goodslables_relation")
public class HotwordGoodslablesRelation extends SuperEntity implements Serializable{

    //@ApiModelProperty(value = "热词的id")
    private String hotWordId;
    //@ApiModelProperty(value = "商品标签的id")
    private String goodsLabelsId;

    public void setHotWordId(String hotWordId){
        this.hotWordId = hotWordId;
    }

    public String getHotWordId(){
        return this.hotWordId;
    }
    public void setGoodsLabelsId(String goodsLabelsId){
        this.goodsLabelsId = goodsLabelsId;
    }

    public String getGoodsLabelsId(){
        return this.goodsLabelsId;
    }

}

