package com.deepexi.content.domain.dto;

import javax.ws.rs.QueryParam;

/**
 * @author hongchungen
 * @date 2019/10/21 16:54
 */
public class TopicQueryDto {
    /**
     * 话题名称
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
