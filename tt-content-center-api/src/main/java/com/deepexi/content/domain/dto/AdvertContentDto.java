package com.deepexi.content.domain.dto;

import com.deepexi.content.enums.ContentSetTypeEnum;

import java.io.Serializable;
import java.util.List;

/**
 * @author admin
 */
public class AdvertContentDto implements Serializable {

    /**
     * 类型 1:BannerImg设置 2:专题页设置 3:楼层页设置  4:弹窗设置 5：话题设置
     */
    private ContentSetTypeEnum setTypeEnum;

    /**
     * 类型ID
     */
    private String setTypeId;

    /**
     * 内容json格式
     */
    private List<AdvertContentJsonDto> jsonDtos;

    public ContentSetTypeEnum getSetTypeEnum() {
        return setTypeEnum;
    }

    public void setSetTypeEnum(ContentSetTypeEnum setTypeEnum) {
        this.setTypeEnum = setTypeEnum;
    }

    public String getSetTypeId() {
        return setTypeId;
    }

    public void setSetTypeId(String setTypeId) {
        this.setTypeId = setTypeId;
    }

    public List<AdvertContentJsonDto> getJsonDtos() {
        return jsonDtos;
    }

    public void setJsonDtos(List<AdvertContentJsonDto> jsonDtos) {
        this.jsonDtos = jsonDtos;
    }
}
