package com.deepexi.content.domain.vo;

import com.deepexi.content.domain.dto.AdvertContentSuperDto;
import com.deepexi.content.enums.AdvertTypeEnum;

/**
 * @author hongchungen
 * @Package com.deepexi.content.domain.vo
 * @Description: 专题页详情vo
 *
 */
public class ProjectPageDetailVo extends AdvertContentSuperDto {
    private String id;
    /**
     * 专题页名称
     */
    private String name;
    /**
     * 状态0：启用，1：禁用
     */
    private Integer status;
    /**
     * 广告图
     */
    private String imgUrl;

    /**
     * 父级id
     */
    private String pId;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 关联类型
     */
    private AdvertTypeEnum advertTypeEnum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public AdvertTypeEnum getAdvertTypeEnum() {
        return advertTypeEnum;
    }

    public void setAdvertTypeEnum(AdvertTypeEnum advertTypeEnum) {
        this.advertTypeEnum = advertTypeEnum;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "ProjectPageDetailVo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", imgUrl='" + imgUrl + '\'' +
                ", pId='" + pId + '\'' +
                ", weight=" + weight +
                ", advertTypeEnum=" + advertTypeEnum +
                '}';
    }
}
