package com.deepexi.content.service;

import com.deepexi.content.domain.dto.DateConditionDto;
import com.deepexi.content.domain.vo.HotWordCountVo;
import com.deepexi.content.domain.vo.HotWordVo;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.content.domain.eo.HotWordCount;
import com.deepexi.content.domain.dto.HotWordCountDto;
import java.util.*;

public interface HotWordCountService {

    /**
     * 分页查询
     * @param dto dto传输对象
     * @param page 页数
     * @param size 每页数据数
     * @return 返回值
     */
    PageBean<HotWordCountVo> findPage(DateConditionDto dto, Integer page, Integer size);

    /**
     * 查询所有数据
     * @param eo dto传输对象
     * @return 返回值
     */
    List<HotWordCount> findAll(HotWordCountDto eo);

    /**
     * 查询数据明细
     * @param pk id
     * @return 返回值
     */
    List<HotWordVo> detail(String pk);

    /**
     * 更新数据
     * @param pk id
     * @param eo dto传输对象
     * @return 返回值
     */
    Boolean update(String pk, HotWordCountDto eo);

    /**
     * 更新热词
     * @param id id
     * @param hotWordValuesStr 热词值字符串形式
     * @return 返回值
     */
    Boolean updateHotWords(String id,String hotWordValuesStr);

    /**
     * 定时任务
     * @return 返回值
     */
    Boolean task();

    /**
     * 删除数据
     * @param pk id数组
     * @return 返回值
     */
    Boolean delete(String... pk);

    /**
     * 查询数据
     * @param date 展示日期
     * @return 返回值
     */
    String selectIdByShowDate(String date);
}