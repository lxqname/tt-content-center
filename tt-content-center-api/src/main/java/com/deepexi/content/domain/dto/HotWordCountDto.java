package com.deepexi.content.domain.dto;

import com.deepexi.content.domain.eo.HotWordCount;
import java.util.Collection;
import java.util.List;
import java.util.Date;
import java.io.Serializable;

/**
 * @desc hot_word_count
 * @author 
 */
public class HotWordCountDto implements Serializable {
    private String id;

    private Integer  onShow;

    private Integer  searchNum;

    private String hotWords;

    private Date showDate;

    private String tenantCode;

    private Date createdAt;

    private String createdBy;

    private Date updatedAt;

    private String updatedBy;

    private Integer  version;

    private Integer  dr;


    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

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

    public void setTenantCode(String tenantCode){
        this.tenantCode = tenantCode;
    }

    public String getTenantCode(){
        return this.tenantCode;
    }

    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt;
    }

    public Date getCreatedAt(){
        return this.createdAt;
    }

    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }

    public String getCreatedBy(){
        return this.createdBy;
    }

    public void setUpdatedAt(Date updatedAt){
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt(){
        return this.updatedAt;
    }

    public void setUpdatedBy(String updatedBy){
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy(){
        return this.updatedBy;
    }

    public void setVersion(Integer  version){
        this.version = version;
    }

    public Integer  getVersion(){
        return this.version;
    }

    public void setDr(Integer  dr){
        this.dr = dr;
    }

    public Integer  getDr(){
        return this.dr;
    }
}

