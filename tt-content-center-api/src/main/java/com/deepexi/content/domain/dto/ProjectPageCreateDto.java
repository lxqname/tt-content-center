package com.deepexi.content.domain.dto;

import com.deepexi.content.enums.AdvertTypeEnum;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author hongchungen
 */
public class ProjectPageCreateDto extends AdvertContentSuperDto implements Serializable {
    private String id;
    /**
     * 专题页名称
     */
    @NotNull(message = "专题页名称不允许为空")
    private String name;
    /**
     * 状态0：启用，1：禁用
     */
    @NotNull(message = "专题页状态不允许为空")
    private Integer status;
    /**
     * 广告图
     */
    private String imgUrl;

    /**
     * 父级id（默认0）
     */
    private String pId;

    /**
     * 权重（创建时增长）
     */
    private Integer weight;

    /**
     * 关联类型
     */
    @NotNull(message = "关联类型不能为空")
    private AdvertTypeEnum typeEnum;

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

    public AdvertTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(AdvertTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

}
