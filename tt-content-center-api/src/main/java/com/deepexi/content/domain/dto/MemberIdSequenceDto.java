package com.deepexi.content.domain.dto;

import java.io.Serializable;

/**
 * @author ：wujie
 * @date ：Created in 2019/9/16 17:29
 * @description：会员信息项的id和序号的Dto
 * @version: 1.0.0
 */
public class MemberIdSequenceDto implements Serializable {

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
    private Integer resourceSequence;

    /**
     * 当前行对应的上一行或者下一行的序号
     */
    private Integer destinationSequence;

    public MemberIdSequenceDto() {
    }

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

    public Integer getResourceSequence() {
        return resourceSequence;
    }

    public void setResourceSequence(Integer resourceSequence) {
        this.resourceSequence = resourceSequence;
    }

    public Integer getDestinationSequence() {
        return destinationSequence;
    }

    public void setDestinationSequence(Integer destinationSequence) {
        this.destinationSequence = destinationSequence;
    }

    @Override
    public String toString() {
        return "MemberSequenceDto{" +
                "resourceId='" + resourceId + '\'' +
                ", destinationId='" + destinationId + '\'' +
                ", resourceSequence=" + resourceSequence +
                ", destinationSequence=" + destinationSequence +
                '}';
    }
}
