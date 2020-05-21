package com.deepexi.content.domain.vo;

import com.deepexi.content.domain.dto.AdvertContentSuperDto;
import com.deepexi.content.enums.AdvertTypeEnum;

import java.util.Date;

/**
 * @author hongchungen
 *
 */
public class TopicPageDetailVo extends AdvertContentSuperDto {

    private String id;
    /** 话题名字 */
    private String name;
    /** 话题状态 */
    private Integer  status;
    /** 上架时间 */
    private Date startTime;
    /** 下架时间 */
    private Date endTime;
    /** 话题状态 */
    private Integer state;
    /** 关联类型 */
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

    public AdvertTypeEnum getAdvertTypeEnum() {
        return advertTypeEnum;
    }

    public void setAdvertTypeEnum(AdvertTypeEnum advertTypeEnum) {
        this.advertTypeEnum = advertTypeEnum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
