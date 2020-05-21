package com.deepexi.content.service;

import com.deepexi.content.domain.eo.PopupMemberLevelRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *@author: LuFeng
 */
public interface PopupMemberLevelRelationService {

    /**
     * 根据弹窗ID获取会员等级关系
     * @param popupId
     * @return
     */
    List<PopupMemberLevelRelation> getByPopupId(@Param("popupId") String popupId);

    /**
     * 删除弹窗会员等级关系，根据弹窗ID
     * @param popupId
     * @return
     */
    int deleteByPopupId(@Param("popupId") String popupId);

    /**
     * 创建弹窗会员等级关系
     * @param eo
     * @return
     */
    int create(PopupMemberLevelRelation eo);

}
