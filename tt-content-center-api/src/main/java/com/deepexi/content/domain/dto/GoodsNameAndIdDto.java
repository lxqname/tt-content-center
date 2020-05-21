package com.deepexi.content.domain.dto;

import java.io.Serializable;

/**
 * @author ：wujie
 * @date ：Created in 2019/9/25 15:54
 * @description：商品名称和id的dto
 * @version: 1.0.0
 */
public class GoodsNameAndIdDto implements Serializable {
    /**
     * 商品标签名称
     */
    private String name;

    /**
     * 商品标签id
     */
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
