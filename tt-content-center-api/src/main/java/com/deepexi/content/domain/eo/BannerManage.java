package com.deepexi.content.domain.eo;




import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.common.domain.eo.SuperEntity;

import java.util.*;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;


/**
 * @desc banner_manage
 * @author 
 */
//@ApiModel(description = "banner管理")
@TableName("coc_banner_manage")
public class BannerManage extends SuperEntity implements Serializable{

    //@ApiModelProperty(value = "位置")
    private String site;

    //@ApiModelProperty(value = "所属应用")
    private String applicationOfAffiliation;

    public void setSite(String site){
        this.site = site;
    }

    public String getSite(){
        return this.site;
    }

    public void setApplicationOfAffiliation(String applicationOfAffiliation){
        this.applicationOfAffiliation = applicationOfAffiliation;
    }

    public String getApplicationOfAffiliation(){
        return this.applicationOfAffiliation;
    }


}

