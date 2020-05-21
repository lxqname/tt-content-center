package com.deepexi.content.domain.dto;

import com.deepexi.common.domain.eo.SuperEntity;

import javax.ws.rs.QueryParam;

/**
 * @author hongchungen
 * @date 2019/09/28 20:58
 */
public class BannerQueryDto extends SuperEntity {
    /**
     * banner名称
     */
    @QueryParam("name")
    private String name;
    /**
     * 开始时间
     */
    @QueryParam("startTime")
    private Long startTime;
    /**
     * 结束时间
     */
    @QueryParam("endTime")
    private Long endTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
