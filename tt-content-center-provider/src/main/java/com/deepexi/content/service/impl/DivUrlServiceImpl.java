package com.deepexi.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.deepexi.content.service.DivUrlService;
import com.deepexi.content.domain.eo.DivUrl;
import com.deepexi.content.domain.dto.DivUrlDto;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.DivUrlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.deepexi.util.BeanPowerHelper;

import java.util.List;

/**
 * @author admin
 */

@Component
@Service(version = "${demo.service.version}")
public class DivUrlServiceImpl implements DivUrlService {

    private static final Logger logger = LoggerFactory.getLogger(DivUrlServiceImpl.class);

    @Autowired
    private DivUrlMapper divUrlMapper;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;



    @Override
    public DivUrl detail(String pk) {
        return divUrlMapper.selectById(pk);
    }

    @Override
    public Boolean create(DivUrl eo) {
        int result = divUrlMapper.insert(eo);
        return result > 0;
    }

    @Override
    public Boolean update(String pk,DivUrlDto eo) {
        eo.setId(pk);
        int result = divUrlMapper.updateById(BeanPowerHelper.mapPartOverrider(eo,DivUrl.class));
        return result > 0;
    }

    @Override
    public Boolean delete(String...pk) {
        int result = divUrlMapper.deleteByIds(pk);
        return result > 0;
    }

    @Override
    public DivUrl getById(String id) {
        DivUrl divUrl = divUrlMapper.selectById(id);
        return divUrl;
    }

    /**
     * 通过url获取id
     * @param url
     * @return
     */
    @Override
    public List<String> getByUrl(String url) {
        List<String> byUrl = divUrlMapper.getByUrl(url, appRuntimeEnv.getTenantId());
        return byUrl;
    }

}