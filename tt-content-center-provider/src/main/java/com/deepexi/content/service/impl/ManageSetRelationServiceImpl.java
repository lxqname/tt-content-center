package com.deepexi.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.deepexi.content.service.ManageSetRelationService;
import com.deepexi.content.domain.eo.ManageSetRelation;
import com.deepexi.content.domain.dto.ManageSetRelationDto;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.ManageSetRelationMapper;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.deepexi.util.BeanPowerHelper;
import java.util.List;


@Component
@Service(version = "${demo.service.version}")
public class ManageSetRelationServiceImpl implements ManageSetRelationService {

    private static final Logger logger = LoggerFactory.getLogger(ManageSetRelationServiceImpl.class);

    @Autowired
    private ManageSetRelationMapper manageSetRelationMapper;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Override
    public PageBean findPage(ManageSetRelationDto eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<ManageSetRelation> list = manageSetRelationMapper.findList(eo);
        return new PageBean<>(list);
    }

    @Override
    public List<ManageSetRelation> findAll(ManageSetRelationDto eo) {
        List<ManageSetRelation> list = manageSetRelationMapper.findList(eo);
        return list;
    }
    @Override
    public ManageSetRelation detail(String pk) {
        return manageSetRelationMapper.selectById(pk);
    }

    @Override
    public Boolean create(ManageSetRelationDto eo) {
        int result = manageSetRelationMapper.insert(BeanPowerHelper.mapPartOverrider(eo,ManageSetRelation.class));
        return result > 0;
    }

    @Override
    public Boolean update(String pk,ManageSetRelationDto eo) {
        eo.setId(pk);
        int result = manageSetRelationMapper.updateById(BeanPowerHelper.mapPartOverrider(eo,ManageSetRelation.class));
        return result > 0;
    }

    @Override
    public Boolean delete(String...pk) {
        int result = manageSetRelationMapper.deleteByIds(pk);
        return result > 0;
    }

}