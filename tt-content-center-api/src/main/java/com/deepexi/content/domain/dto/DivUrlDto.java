package com.deepexi.content.domain.dto;

import com.deepexi.common.domain.eo.SuperEntity;

import java.io.Serializable;

/**
 * @desc div_url
 * @author 
 */
public class DivUrlDto extends SuperEntity implements Serializable {

    private String divUrl;

    public void setDivUrl(String divUrl){
        this.divUrl = divUrl;
    }

    public String getDivUrl(){
        return this.divUrl;
    }


}

