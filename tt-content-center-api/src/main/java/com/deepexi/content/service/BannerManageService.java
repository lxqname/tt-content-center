package com.deepexi.content.service;

import com.deepexi.content.domain.dto.BannerManageSelectDto;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.content.domain.eo.BannerManage;
import com.deepexi.content.domain.dto.BannerManageDto;
import java.util.*;

/**
 * @author hongchungen
 * @date 2019/09/26 0:08
 */
public interface BannerManageService {

    /**
     * 分页
     * @param eo
     * @param page
     * @param size
     * @return
     */
    PageBean<BannerManage> findPage(BannerManageDto eo, Integer page, Integer size);

    /**
     * 查询所有
     * @param eo
     * @return
     */
    List<BannerManage> findAll(BannerManageDto eo);

    /**
     * 查询详情
     * @param pk
     * @return
     */
    BannerManage detail(String pk);

    /**
     * 更新
     * @param pk
     * @param eo
     * @return
     */
    Boolean update(String pk, BannerManageDto eo);

    /**
     * 创建
     * @param eo
     * @return
     */
    Boolean create(BannerManageDto eo);

    /**
     * 删除
     * @param pk
     * @return
     */
    Boolean delete(String... pk);

    /**
     * 获取banner管理页
     * @return
     */
    BannerManageSelectDto getBanner();
}