package com.deepexi.content.service;

import com.deepexi.common.enums.StatusEnum;
import com.deepexi.content.domain.dto.BannerQueryDto;
import com.deepexi.content.domain.dto.BannerSetCreateDto;
import com.deepexi.content.domain.dto.BannerSetUpdateDto;
import com.deepexi.content.domain.eo.BannerSet;
import com.deepexi.content.domain.vo.BannerFrontPageVo;
import com.deepexi.content.domain.vo.BannerSetDetailVo;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * @author hongchungen
 */
public interface BannerSetService {

    /**
     * 分页
     * @param eo
     * @param page
     * @param size
     * @param id
     * @return
     */
    PageBean<BannerSetDetailVo> findPage(BannerQueryDto eo, Integer page, Integer size, String id);

    /**
     * 查询所有符合条件的
     * @param tenantId
     * @param pks
     * @return
     */
    List<BannerSet> findBySetIds(String tenantId, List<String> pks);


    /**
     * 详情
     * @param pk
     * @return
     */
    BannerSet detail(String pk);

    /**
     * 更新
     * @param pk
     * @param eo
     * @return
     */
    Boolean update(String pk, BannerSetUpdateDto eo);

    /**
     * 创建
     * @param eo
     * @return
     */
    Boolean create(BannerSetCreateDto eo);

    /**
     * 删除
     * @param pk
     * @return
     */
    Boolean delete(String pk);

    /**
     * 根据banner名称获取banner
     * @param name
     * @return
     */
    BannerSet getByName(String name);

    /**
     * 获取最大权重
     * @return
     */
    BannerSet getMaxWeight();

    /**
     * 获取banner设置详情
     * @param pk
     * @return
     */
    BannerSetDetailVo getBannerSetDetail(String pk);

    /**
     * 上升
     * @param id
     * @return
     */
    Boolean upBanner(String id);

    /**
     * 下降
     * @param id
     * @return
     */
    Boolean downBanner(String id);

    /**
     * 更改状态
     * @param id
     * @param statusEnum
     * @return
     */
    Boolean updateStatus(String id, StatusEnum statusEnum);

    /**
     * H5前端会员
     * @return
     */
    List<BannerFrontPageVo> frontPage();
}