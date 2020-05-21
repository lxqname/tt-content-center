package com.deepexi.content.service;

import com.deepexi.content.domain.dto.GoodsNameAndIdDto;
import com.deepexi.content.domain.vo.HotWordVo;
import com.deepexi.product.domain.dto.ItemTagDto;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.content.domain.eo.HotWord;
import com.deepexi.content.domain.dto.HotWordDto;
import java.util.*;

public interface HotWordService {

    /**
     * 分页查询
     * @param eo dto传输对象
     * @param page 页数
     * @param size 每页数据数
     * @return 返回值
     */
    PageBean<HotWord> findPage(HotWordDto eo, Integer page, Integer size);

    /**
     * 查询所有数据
     * @param eo dto传输对象
     * @return 返回值
     */
    List<HotWord> findAll(HotWordDto eo);

    /**
     * 查询数据明细
     * @param pk id
     * @return 返回值
     */
    HotWord detail(String pk);

    /**
     * 更新数据
     * @param pk id
     * @param eo dto传输对象
     * @return 返回值
     */
    Boolean update(String pk, HotWordDto eo);

    /**
     * 新增数据
     * @param eo 热词传输对象集合
     * @param hotWordCountId 热词统计id
     * @return 返回值
     */
    Boolean create(List<HotWordDto> eo,String hotWordCountId);

    /**
     * 展示热词
     * @param hotWordCountId 热词统计id
     * @return 返回值
     */
    List<HotWordVo> displayHotWords(String hotWordCountId);

    /**
     * 查询热词
     * @param yHotWordIds 历史热词集合
     * @return 返回值
     */
    List<HotWordDto> selectHotWordByYhotWordIds(List<String> yHotWordIds);

    /**
     * 展示商品标签
     * @return 返回值
     */
    List<GoodsNameAndIdDto> showGoodsLabel();

    /**
     * 查询搜索次数
     * @param yHotWordIds 历史热词id集合
     * @return 返回值
     */
    int sumSearchNum(List<String> yHotWordIds);

    /**
     * 更新热词搜索次数
     * @param hotWordId 热词id
     * @return 返回值
     */
    Boolean updateSearchNum(String hotWordId);

    /**
     * H5展示热词
     * @return 返回值
     */
    List<HotWordVo> showHotWordToH5();

    /**
     * 还原推荐热词
     * @param hotWordCountId 统计热词id
     * @return 返回值
     */
    List<HotWordVo> reduction(String hotWordCountId);


}