package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.content.domain.eo.PopupMemberLevelRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: LuFeng
 */
@Mapper
public interface PopupMemberLevelRelationMapper extends BaseMapper<PopupMemberLevelRelation> {
    /**
     * 获取弹窗和会员等级ID关系
     * @param popupId
     * @param tenantId
     * @return
     */
    List<PopupMemberLevelRelation> getByPopupId(@Param("popupId") String popupId, @Param("tenantId")  String tenantId);

    /**
     * 根据弹窗ID删除关系
     * @param popupId
     * @return
     */
    int deleteByPopupId(@Param("popupId") String popupId);
}
