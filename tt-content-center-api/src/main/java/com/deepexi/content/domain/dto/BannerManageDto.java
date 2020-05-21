package com.deepexi.content.domain.dto;

import com.deepexi.common.domain.eo.SuperEntity;

import java.io.Serializable;


/**
 * @desc banner_manage
 * @author 
 */
public class BannerManageDto extends SuperEntity implements Serializable {

    private String site;

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

