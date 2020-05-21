package com.deepexi.content.domain.dto;

import java.io.Serializable;

/**
 * @author ：wujie
 * @date ：Created in 2019/9/18 17:47
 * @description：兴趣项的id和level
 * @version: 1.0.0
 */
public class InterestIdLevelDto implements Serializable {
    /**
     * 当前行的id
     */
    private String resourceId;

    /**
     * 当前行对应的上一行或者下一行的id
     */
    private String destinationId;

    /**
     * 当前行的序号
     */
    private Integer resourceLevel;

    /**
     * 当前行对应的上一行或者下一行的序号
     */
    private Integer destinationLevel;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public Integer getResourceLevel() {
        return resourceLevel;
    }

    public void setResourceLevel(Integer resourceLevel) {
        this.resourceLevel = resourceLevel;
    }

    public Integer getDestinationLevel() {
        return destinationLevel;
    }

    public void setDestinationLevel(Integer destinationLevel) {
        this.destinationLevel = destinationLevel;
    }
}
