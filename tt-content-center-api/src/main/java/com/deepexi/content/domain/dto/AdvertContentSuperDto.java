package com.deepexi.content.domain.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author admin
 */
public class AdvertContentSuperDto implements Serializable {

    /**
     * 内容json格式
     */
    private List<AdvertContentJsonDto> jsonDtoList;

    /**
     * 商品内容json格式
     */
    private List<AdvertContentJsonDto> productJsonDtoList;

    /**
     * 活动内容json格式
     */
    private List<AdvertContentJsonDto> activityJsonDtoList;


    public List<AdvertContentJsonDto> getJsonDtoList() {
        return jsonDtoList;
    }

    public void setJsonDtoList(List<AdvertContentJsonDto> jsonDtoList) {
        this.jsonDtoList = jsonDtoList;
    }

    public List<AdvertContentJsonDto> getProductJsonDtoList() {
        return productJsonDtoList;
    }

    public void setProductJsonDtoList(List<AdvertContentJsonDto> productJsonDtoList) {
        this.productJsonDtoList = productJsonDtoList;
    }

    public List<AdvertContentJsonDto> getActivityJsonDtoList() {
        return activityJsonDtoList;
    }

    public void setActivityJsonDtoList(List<AdvertContentJsonDto> activityJsonDtoList) {
        this.activityJsonDtoList = activityJsonDtoList;
    }
}
