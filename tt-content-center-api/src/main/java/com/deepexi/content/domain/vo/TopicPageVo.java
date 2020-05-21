package com.deepexi.content.domain.vo;

import java.util.Date;

/**
 * @author hongchungen
 * @date 2019/10/20 19:43
 */
public class TopicPageVo {

    private String id;
    /** 话题名字 */
    private String name;
    /** 话题状态 */
    private Integer  status;
    /** 上架时间 */
    private Date startTime;
    /** 下架时间 */
    private Date endTime;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
