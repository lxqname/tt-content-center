package com.deepexi.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.deepexi.content.domain.dto.BannerManageSelectDto;
import com.deepexi.content.domain.eo.BannerSet;
import com.deepexi.content.service.BannerManageService;
import com.deepexi.content.domain.eo.BannerManage;
import com.deepexi.content.domain.dto.BannerManageDto;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.BannerManageMapper;
import com.deepexi.content.service.BannerManageSetRelationService;
import com.deepexi.content.service.BannerSetService;
import com.deepexi.content.utils.NumberTypeUtils;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.deepexi.util.BeanPowerHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hongchungen
 */
@Component
@Service(version = "${demo.service.version}")
public class BannerManageServiceImpl implements BannerManageService {

    private static final Logger logger = LoggerFactory.getLogger(BannerManageServiceImpl.class);


    @Autowired
    private BannerManageMapper bannerManageMapper;

    @Autowired
    private BannerSetService bannerSetService;

    @Autowired
    private BannerManageSetRelationService bannerManageSetRelationService;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    private static final String DEFAULT_ID = "1";

    @Override
    public PageBean findPage(BannerManageDto eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<BannerManage> list = bannerManageMapper.findList(eo);
        return new PageBean<>(list);
    }

    @Override
    public List<BannerManage> findAll(BannerManageDto eo) {
        List<BannerManage> list = bannerManageMapper.findList(eo);
        return list;
    }
    @Override
    public BannerManage detail(String pk) {
        return bannerManageMapper.selectById(pk);
    }

    @Override
    public Boolean create(BannerManageDto eo) {
        int result = bannerManageMapper.insert(BeanPowerHelper.mapPartOverrider(eo,BannerManage.class));
        return result > 0;
    }

    @Override
    public Boolean update(String pk,BannerManageDto eo) {
        eo.setId(pk);
        int result = bannerManageMapper.updateById(BeanPowerHelper.mapPartOverrider(eo,BannerManage.class));
        return result > 0;
    }

    @Override
    public Boolean delete(String...pk) {
        int result = bannerManageMapper.deleteByIds(pk);
        return result > 0;
    }

    /**
     * 获取顶级banner页展示
     * @return
     */
    @Override
    public BannerManageSelectDto getBanner() {
        BannerManageSelectDto bannerManageSelectDto = new BannerManageSelectDto();
        //创建空集合，数据库无数据时前端显示所需内容格式
        List<BannerSet> list = new ArrayList<>();
        //获取manage信息
        BannerManage bannerManage = bannerManageMapper.selectById(DEFAULT_ID);
        //获取与manage相关的setId集合
        List<String> bannerSetId = bannerManageSetRelationService.getSetIdByManageId(DEFAULT_ID);
        if (null == bannerSetId || bannerSetId.size() == NumberTypeUtils.ZERO) {
            BeanUtils.copyProperties(bannerManage, bannerManageSelectDto);
            bannerManageSelectDto.setBannerSetList(list);
            return bannerManageSelectDto;
        }
        //根据setId获取banner详情集合
        List<BannerSet> bannerSetList = bannerSetService.findBySetIds(appRuntimeEnv.getTenantId(), bannerSetId);
        BeanUtils.copyProperties(bannerManage, bannerManageSelectDto);
        bannerManageSelectDto.setBannerSetList(bannerSetList);
        return bannerManageSelectDto;
    }

}