package com.deepexi.content.service;

import com.deepexi.common.enums.StatusEnum;
import com.deepexi.content.domain.dto.TopicPageCreateDto;
import com.deepexi.content.domain.dto.TopicPageStateDto;
import com.deepexi.content.domain.dto.TopicPageUpdateDto;
import com.deepexi.content.domain.dto.TopicQueryDto;
import com.deepexi.content.domain.eo.TopicPage;
import com.deepexi.content.domain.vo.BannerFrontPageVo;
import com.deepexi.content.domain.vo.TopicPageDetailVo;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * @author hongchunegn
 * @date 2019/10/20 20:42
 */
public interface TopicPageService {

    /**
     * 分页
     * @param eo
     * @param page
     * @param size
     * @return
     */
    PageBean<TopicPageStateDto> findPage(TopicQueryDto eo, Integer page, Integer size);

    /**
     * 创建话题页
     * @param dto
     * @return
     */
    Boolean topicPageCreate(TopicPageCreateDto dto);

    /**
     * 获取话题通过name
     * @param name
     * @return
     */
    TopicPage getByName(String name);

    /**
     * 话题更新
     * @param pk
     * @param eo
     * @return
     */
    Boolean update(String pk, TopicPageUpdateDto eo);


    /**
     * 删除话题
     * @param id
     * @return
     */
    Boolean delete (String id);

    /**
     * 更改状态
     * @param id
     * @param statusEnum
     * @return
     */
    Boolean updateStatus(String id, StatusEnum statusEnum);

    /**
     * 获得专题页信息 通过ID
     * @param id
     * @return
     */
    TopicPageDetailVo details(String id);

}
