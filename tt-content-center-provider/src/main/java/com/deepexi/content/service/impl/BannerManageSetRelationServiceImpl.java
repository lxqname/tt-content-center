package com.deepexi.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.deepexi.content.domain.eo.BannerManageSetRelation;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.BannerManageSetRelationMapper;
import com.deepexi.content.service.BannerManageSetRelationService;
import com.deepexi.content.service.BannerSetService;
import com.deepexi.content.utils.NumberTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hongchungen
 * @date 2019/09/24 10:49
 */
@Component
@Service(version = "${demo.service.version}")
public class BannerManageSetRelationServiceImpl implements BannerManageSetRelationService {

    public static final String MANAGE_ID = "1";
    @Autowired
    private BannerManageSetRelationMapper bannerManageSetRelationMapper;

    @Autowired
    private BannerSetService bannerSetService;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    /**
     * 创建
     * @param setId
     * @return
     */
    @Override
    public Boolean create(String setId) {
        BannerManageSetRelation eo = new BannerManageSetRelation();
        eo.setManageId(MANAGE_ID);
        eo.setSetId(setId);
        int result = bannerManageSetRelationMapper.insert(eo);
        if (result > NumberTypeUtils.ZERO){
            return true;
        }
        return false;
    }

    /**
     * 根据banner管理id获取设置id
     * @param manageId
     * @return
     */
    @Override
    public List<String> getSetIdByManageId(String manageId) {
        List<String> bannerSetIds = bannerManageSetRelationMapper.getSetIdByManageId(manageId, appRuntimeEnv.getTenantId());
        List<String> bannerSetIdList = new ArrayList<>();
        bannerSetIds.stream().forEach(relation->{
            bannerSetIdList.add(relation);
        });
        return bannerSetIdList;
    }

    @Override
    public Boolean deleteByBannerSetId(String pk) {
        Boolean result = bannerManageSetRelationMapper.deleteByBannerSetId(pk, appRuntimeEnv.getTenantId());
        return result;
    }
}
