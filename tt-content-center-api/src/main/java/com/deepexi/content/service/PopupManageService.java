package com.deepexi.content.service;

import com.deepexi.common.enums.StatusEnum;
import com.deepexi.content.domain.dto.PopupManageCreateDto;
import com.deepexi.content.domain.dto.PopupManageQueryDto;
import com.deepexi.content.domain.dto.PopupManageUpdateDto;
import com.deepexi.content.domain.vo.PopupManageDetailVo;
import com.deepexi.content.domain.vo.PopupManageShowVo;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.content.domain.eo.PopupManage;
import com.deepexi.content.domain.dto.PopupManageDto;
import java.util.*;

public interface PopupManageService {

    PageBean<PopupManage> findPage(PopupManageDto eo, Integer page, Integer size);

    List<PopupManage> findAll(PopupManageDto eo);

    /**
     * 弹窗详情
     * @param pk
     * @return
     */
    PopupManageDetailVo detail(String pk);

    /**
     * 弹窗详情查询-弹出弹窗
     * @param eo
     * @return
     */
    PopupManageShowVo detailQuery(PopupManageQueryDto eo);

    /**
     * 修改弹窗
     * @param pk
     * @param eo
     * @return
     */
    Boolean update(String pk, PopupManageUpdateDto eo);

    /**
     * 创建弹窗
     * @param eo
     * @return
     */
    Boolean create(PopupManageCreateDto eo);

    /**
     * 删除弹窗
     * @param pk
     * @return
     */
    Boolean delete(String pk);

    /**
     * 获取弹窗
     * @param name
     * @return
     */
    PopupManage getByName(String name);

    /**
     * 修改弹窗状态
     * @param id
     * @param statusEnum
     * @return
     */
    Boolean updateStatus(String id, StatusEnum statusEnum);

    /**
     * 初始化弹窗 弹出记录
     */
    void initPopupContent();
}