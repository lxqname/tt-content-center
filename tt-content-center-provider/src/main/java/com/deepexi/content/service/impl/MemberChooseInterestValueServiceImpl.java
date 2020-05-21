package com.deepexi.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.deepexi.content.domain.dto.MemberChooseInterestValueDto;
import com.deepexi.content.domain.eo.MemberChooseInterestValue;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.MemberChooseInterestValueMapper;
import com.deepexi.content.service.MemberChooseInterestValueService;
import com.deepexi.util.BeanPowerHelper;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
@Service(version = "${demo.service.version}")
public class MemberChooseInterestValueServiceImpl implements MemberChooseInterestValueService {

    private static final int ZERO=0;

    private static final Logger logger = LoggerFactory.getLogger(MemberChooseInterestValueServiceImpl.class);

    @Autowired
    private MemberChooseInterestValueMapper memberChooseInterestValueMapper;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Override
    public PageBean findPage(MemberChooseInterestValueDto eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<MemberChooseInterestValue> list = memberChooseInterestValueMapper.findList(eo);
        return new PageBean<>(list);
    }

    @Override
    public List<MemberChooseInterestValue> findAll(MemberChooseInterestValueDto eo) {
        List<MemberChooseInterestValue> list = memberChooseInterestValueMapper.findList(eo);
        return list;
    }
    @Override
    public MemberChooseInterestValue detail(String pk) {
        return memberChooseInterestValueMapper.selectById(pk);
    }

    @Override
    public Boolean create(MemberChooseInterestValueDto eo) {
        int result = memberChooseInterestValueMapper.insert(BeanPowerHelper.mapPartOverrider(eo, MemberChooseInterestValue.class));
        return result > 0;
    }


    /**
     * @Description: 增加会员所选兴趣项值到表中(仅含自定义部分)
     * @Param: [eo]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/10/9 16:46
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean create(MemberChooseInterestValue eo) {
        int i = memberChooseInterestValueMapper.insert(eo);
        return i>ZERO;
    }

    @Override
    public Boolean update(String pk, MemberChooseInterestValueDto eo) {
        eo.setId(pk);
        int result = memberChooseInterestValueMapper.updateById(BeanPowerHelper.mapPartOverrider(eo, MemberChooseInterestValue.class));
        return result > 0;
    }

    @Override
    public Boolean delete(String...pk) {
        int result = memberChooseInterestValueMapper.deleteByIds(pk);
        return result > 0;
    }


    /**
     * @Description: 根据会员所选兴趣值的id查询兴趣值
     * @Param: [valueId]
     * @returns: java.lang.String
     * @Author: wujie
     * @Date: 2019/10/9 19:14
     */
    @Override
    public String selectInterestValue(String valueId) {
        MemberChooseInterestValue memberChooseInterestValue = memberChooseInterestValueMapper.selectById(valueId);
        return memberChooseInterestValue.getValue();
    }

}