package com.deepexi.content.domain.eo;




import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.common.domain.eo.SuperEntity;

import java.util.*;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;


/**
 * @desc div_url
 * @author 
 */
//@ApiModel(description = "coc_div_url")
@TableName("coc_div_url")
public class DivUrl extends SuperEntity implements Serializable{
    //@ApiModelProperty(value = "自定义url")
    private String divUrl;

    public void setDivUrl(String divUrl){
        this.divUrl = divUrl;
    }

    public String getDivUrl(){
        return this.divUrl;
    }


}

