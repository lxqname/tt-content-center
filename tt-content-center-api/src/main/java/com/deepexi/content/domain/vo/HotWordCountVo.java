package com.deepexi.content.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * @author ：wujie
 * @date ：Created in 2019/9/26 10:32
 * @description：热词统计的Vo
 * @version: 1.0.0
 */
public class HotWordCountVo {

    /**
     * 主键id
     */
    private String id;


    //@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date showDate;


    /**
     * 展示日期
     */
    private String onShowDate;

    /**
     * 是否展示 0未展示 1展示中
     */
    private Integer onShow;

    /**
     * 热词集集合形式
     */
    private List<String> hotwordList;


    /**
     * 热词集json字符串形式
     */
    private String hotWords;

    /**
     * 热词集搜索次数
     */
    private Integer searchNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getShowDate() {
        return showDate;
    }

    public void setShowDate(Date showDate) {
        this.showDate = showDate;
    }

    public Integer getOnShow() {
        return onShow;
    }

    public void setOnShow(Integer onShow) {
        this.onShow = onShow;
    }

    public String getHotWords() {
        return hotWords;
    }

    public void setHotWords(String hotWords) {
        this.hotWords = hotWords;
    }

    public Integer getSearchNum() {
        return searchNum;
    }

    public void setSearchNum(Integer searchNum) {
        this.searchNum = searchNum;
    }

    public List<String> getHotwordList() {
        return hotwordList;
    }

    public void setHotwordList(List<String> hotwordList) {
        this.hotwordList = hotwordList;
    }

    public String getOnShowDate() {
        return onShowDate;
    }

    public void setOnShowDate(String onShowDate) {
        this.onShowDate = onShowDate;
    }
}

