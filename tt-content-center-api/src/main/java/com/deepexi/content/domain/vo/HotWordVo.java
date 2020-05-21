package com.deepexi.content.domain.vo;

import com.deepexi.content.domain.dto.GoodsNameAndIdDto;
import com.deepexi.product.domain.dto.ItemTagDto;

import java.util.List;

/**
 * @author ：wujie
 * @date ：Created in 2019/9/25 17:34
 * @description：热词回显的Vo
 * @version: 1.0.0
 */
public class HotWordVo {
    /**
     * 热词的主键id
     */
    private String id;

    /**
     * 热词名称
     */
    private String name;

    /**
     * 热词来源
     */
    private String source;

    /**
     * 是否标红 0未标红 1标红
     */
    private Integer tagRed;

    /**
     * 热词点击量
     */
    private Integer searchNum;

    /**
     * 商品标签的id和名称对象
     */
    private List<GoodsNameAndIdDto> goodsNameAndId;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTagRed() {
        return tagRed;
    }

    public void setTagRed(Integer tagRed) {
        this.tagRed = tagRed;
    }

    public List<GoodsNameAndIdDto> getGoodsNameAndId() {
        return goodsNameAndId;
    }

    public void setGoodsNameAndId(List<GoodsNameAndIdDto> goodsNameAndId) {
        this.goodsNameAndId = goodsNameAndId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getSearchNum() {
        return searchNum;
    }

    public void setSearchNum(Integer searchNum) {
        this.searchNum = searchNum;
    }

}
