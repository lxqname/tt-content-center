package com.deepexi.content.domain.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author hongchungen
 * @date 2019/09/25 23:56
 */
public class MovePageDto implements Serializable {
    /**
     * 当前ID
     */
    @NotNull(message = "ID不能为空")
    private String id;
    /**
     * 位置ID
     */
    @NotNull(message = "位置ID不能为空")
    private String locationId;
    /**
     * 相邻类型（1-原id后一个位置、2-原id前一个位置）
     */
    @NotNull(message = "类型不能为空")
    private Integer type;

    @Override
    public String toString() {
        return "McMoveDTO{" +
                "id='" + id + '\'' +
                ", locationId='" + locationId + '\'' +
                ", type=" + type +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
