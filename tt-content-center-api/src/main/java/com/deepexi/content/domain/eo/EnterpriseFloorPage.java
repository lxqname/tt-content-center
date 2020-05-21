package com.deepexi.content.domain.eo;




import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.common.domain.eo.SuperEntity;

import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;


/**
 * @desc floor_page
 * @author  lxq
 */
//@ApiModel(description = "coc_floor_page")
@TableName("coc_enterprise_floor_page")
public class EnterpriseFloorPage extends SuperEntity implements Serializable{

    //@ApiModelProperty(value = "楼层页名称")
    /** 楼层页名称
     * @param name
     */
    private String name;
    //@ApiModelProperty(value = "状态，0=启用，1=禁用")
    /** 楼层页状态
     * @param status
     */
    private Integer  status;
    //@ApiModelProperty(value = "楼层广告图")
    /** 楼层页广告图
     * @param imgUrl
     */
    private String imgUrl;

    /**
     * 父级id
     */
    private String pId;

    /**
     * 组织id
     */
    private String organizationId;

    /**
     * 权重
     */
    private Integer weight;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
    public void setStatus(Integer  status){
        this.status = status;
    }

    public Integer  getStatus(){
        return this.status;
    }
    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }

    public String getImgUrl(){
        return this.imgUrl;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EnterpriseFloorPage{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", imgUrl='" + imgUrl + '\'' +
                ", pId='" + pId + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", weight=" + weight +
                '}';
    }
}

