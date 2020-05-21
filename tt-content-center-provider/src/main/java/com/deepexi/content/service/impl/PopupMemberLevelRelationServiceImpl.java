package com.deepexi.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.deepexi.content.domain.eo.PopupMemberLevelRelation;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.PopupMemberLevelRelationMapper;
import com.deepexi.content.service.PopupMemberLevelRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Service(version = "${demo.service.version}")
public class PopupMemberLevelRelationServiceImpl implements PopupMemberLevelRelationService {

    @Autowired
    private PopupMemberLevelRelationMapper popupMemberLevelRelationMapper;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Override
    public List<PopupMemberLevelRelation> getByPopupId(String popupId) {
        return popupMemberLevelRelationMapper.getByPopupId(popupId, appRuntimeEnv.getTenantId());
    }

    @Override
    public int deleteByPopupId(String popupId) {
        return popupMemberLevelRelationMapper.deleteByPopupId(popupId);
    }

    @Override
    public int create(PopupMemberLevelRelation eo) {
        return popupMemberLevelRelationMapper.insert(eo);
    }
}
