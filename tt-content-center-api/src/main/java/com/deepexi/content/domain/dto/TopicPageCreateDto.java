package com.deepexi.content.domain.dto;

import com.deepexi.content.enums.AdvertTypeEnum;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author hongchungen
 * @date 2019/10/20 20:29
 */
public class TopicPageCreateDto extends AdvertContentSuperDto implements Serializable {
    /** 主键id */
    private String id;
    /** 话题名字 */
    @NotNull(message = "话题名字不能为空")
    private String name;
    /** 话题状态 */
    private Integer  status;
    /** 上架时间 */
    @NotNull(message = "开始时间不能为空")
    private Date startTime;
    /** 下架时间 */
    @NotNull(message = "话题名字不能为空")
    private Date endTime;
    /** 关联类型 */
    private AdvertTypeEnum typeEnum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AdvertTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(AdvertTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}
