package com.deepexi.content.domain.vo;

import com.deepexi.activity.domain.dto.ActivityDetailDto;
import com.deepexi.content.domain.dto.AdvertContentJsonAndActivityDetailDto;
import com.deepexi.content.domain.dto.AdvertContentSuperDto;
import com.deepexi.content.enums.AdvertTypeEnum;

import java.util.List;

/**
 * @author: hongchungen
 * @Package com.deepexi.content.domain.vo
 * @Description: 楼层页详情vo
 * @date:
 */
public class EnterpriseFloorPageDetailVo extends AdvertContentSuperDto {
    private String id;
    /**
     * 楼层页名称
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
     * 组织id
     */
    private String organizationId;

    /**
     * 权重
     */
    private Integer weight;
    /**
     * 关联类型
     */
    private AdvertTypeEnum advertTypeEnum;

    /**
     * 活动细节
     */
    private List<ActivityDetailDto> activityDetailDto;

    public AdvertTypeEnum getAdvertTypeEnum() {
        return advertTypeEnum;
    }

    public void setAdvertTypeEnum(AdvertTypeEnum advertTypeEnum) {
        this.advertTypeEnum = advertTypeEnum;
    }

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

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public List<ActivityDetailDto> getActivityDetailDto() {
        return activityDetailDto;
    }

    public void setActivityDetailDto(List<ActivityDetailDto> activityDetailDto) {
        this.activityDetailDto = activityDetailDto;
    }

    @Override
    public String toString() {
        return "EnterpriseFloorPageDetailVo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", imgUrl='" + imgUrl + '\'' +
                ", pId='" + pId + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", weight=" + weight +
                ", advertTypeEnum=" + advertTypeEnum +
                ", activityDetailDto=" + activityDetailDto +
                '}';
    }
}
