package com.deepexi.content.domain.dto;

import javax.ws.rs.QueryParam;
import java.io.Serializable;

/**
 * @author ：wujie
 * @date ：Created in 2019/9/26 10:23
 * @description：热词统计条件查询的日期区间
 * @version: 1.0.0
 */
public class DateConditionDto implements Serializable {

    /**
     * 日期开始区间
     */
    @QueryParam("startTime")
    private long startTime;

    /**
     * 日期结束区间
     */
    @QueryParam("endTime")
    private long endTime;


    /**
     * 开始时间(字符串格式)
     */
    private String start;

    /**
     * 结束时间(字符串格式)
     */
    private String end;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
