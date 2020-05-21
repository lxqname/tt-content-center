package com.deepexi.content.domain.dto;

import com.deepexi.content.domain.eo.HotWord;
import com.deepexi.product.domain.dto.ItemTagDto;

import java.util.Collection;
import java.util.List;
import java.util.Date;
import java.io.Serializable;

/**
 * @desc hot_word
 * @author
 */
public class HotWordDto implements Serializable {
    private String id;

    private String name;

    private Integer  searchNum;

    /**
     * 热词来源,由前端传来 "自定义"或者"YYYY-MM-DD top10-X"格式
     */
    private String source;

    private Integer  tagRed;

    private String tenantCode;

    private Date createdAt;

    private String createdBy;

    private Date updatedAt;

    private String updatedBy;

    private Integer  version;

    private Integer  dr;

    /**
     * 商品标签的id和商品名
     */
    private List<GoodsNameAndIdDto> goodsNameAndId;


    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

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

    public List<GoodsNameAndIdDto> getGoodsNameAndId() {
        return goodsNameAndId;
    }

    public void setGoodsNameAndId(List<GoodsNameAndIdDto> goodsNameAndId) {
        this.goodsNameAndId = goodsNameAndId;
    }
}

