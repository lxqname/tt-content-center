package com.deepexi.content.domain.eo;






import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.common.domain.eo.SuperEntity;

import java.util.*;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;


/**
 * @desc hotwordcount_hotword_relation
 * @author 
 */
//@ApiModel(description = "coc_hotwordcount_hotword_relation")
@TableName("coc_hotwordcount_hotword_relation")
public class HotwordcountHotwordRelation extends SuperEntity implements Serializable{

    //@ApiModelProperty(value = "热词集id")
    private String hotWordCountId;
    //@ApiModelProperty(value = "热词id")
    private String hotWordId;

    public void setHotWordCountId(String hotWordCountId){
        this.hotWordCountId = hotWordCountId;
    }

    public String getHotWordCountId(){
        return this.hotWordCountId;
    }
    public void setHotWordId(String hotWordId){
        this.hotWordId = hotWordId;
    }

    public String getHotWordId(){
        return this.hotWordId;
    }


}

