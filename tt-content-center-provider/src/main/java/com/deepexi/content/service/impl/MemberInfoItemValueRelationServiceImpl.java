package com.deepexi.content.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.deepexi.content.domain.dto.MemberInfoItemValueDto;
import com.deepexi.content.domain.eo.MemberInfoItemValue;
import com.deepexi.content.service.MemberInfoItemValueRelationService;
import com.deepexi.content.domain.eo.MemberInfoItemValueRelation;
import com.deepexi.content.domain.dto.MemberInfoItemValueRelationDto;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.MemberInfoItemValueRelationMapper;
import com.deepexi.user.service.AccountService;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.deepexi.util.BeanPowerHelper;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Component
@Service(version = "${demo.service.version}")
public class MemberInfoItemValueRelationServiceImpl implements MemberInfoItemValueRelationService {

    private static final int ZERO=0;

    private static final String REGEX="-";

    private static final String EMPTY="";

    private static final Logger logger = LoggerFactory.getLogger(MemberInfoItemValueRelationServiceImpl.class);

    @Autowired
    private MemberInfoItemValueRelationMapper memberInfoItemValueRelationMapper;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Reference(version = "${content.service.version}", check = false)
    private AccountService accountService;

    @Override
    public PageBean findPage(MemberInfoItemValueRelationDto eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<MemberInfoItemValueRelation> list = memberInfoItemValueRelationMapper.findList(eo);
        return new PageBean<>(list);
    }

    @Override
    public List<MemberInfoItemValueRelation> findAll(MemberInfoItemValueRelationDto eo) {
        List<MemberInfoItemValueRelation> list = memberInfoItemValueRelationMapper.findList(eo);
        return list;
    }
    @Override
    public MemberInfoItemValueRelation detail(String pk) {
        return memberInfoItemValueRelationMapper.selectById(pk);
    }

    @Override
    public Boolean create(MemberInfoItemValueRelationDto eo) {
        int result = memberInfoItemValueRelationMapper.insert(BeanPowerHelper.mapPartOverrider(eo,MemberInfoItemValueRelation.class));
        return result > 0;
    }

    @Override
    public Boolean update(String pk,MemberInfoItemValueRelationDto eo) {
        eo.setId(pk);
        int result = memberInfoItemValueRelationMapper.updateById(BeanPowerHelper.mapPartOverrider(eo,MemberInfoItemValueRelation.class));
        return result > 0;
    }

    @Override
    public Boolean delete(String...pk) {
        int result = memberInfoItemValueRelationMapper.deleteByIds(pk);
        return result > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteById(String itemId) {
        int i = memberInfoItemValueRelationMapper.deleteByItemId(itemId);
        return i>0;
    }

    /**
     * @Description: 根据itemId到关联表查询会员信息项值的id集合
     * @Param: [itemId]
     * @returns: java.util.List<java.lang.String>
     * @Author: wujie
     * @Date: 2019/9/18 20:43
     */
    @Override
    public List<String> selectByItemId(String itemId) {
        List<String> valueIds = memberInfoItemValueRelationMapper.findById(itemId);
        return valueIds;
    }

    /**
     * @Description:增加会员信息项id和会员信息项值的id到关联表
     * @Param: [memberInfoItemValueDtos]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/17 16:42
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertItemIdAndItemValueId(List<MemberInfoItemValueDto> memberInfoItemValueDtos,String memberInfoItemId) {
        Boolean aBoolean=true;
        //遍历会员信息项dto对象集合memberInfoItemValueDtos
        for (MemberInfoItemValueDto memberInfoItemValueDto:memberInfoItemValueDtos) {
            MemberInfoItemValueRelationDto memberInfoItemValueRelationDto = new MemberInfoItemValueRelationDto();
            memberInfoItemValueRelationDto.setItemId(memberInfoItemId);
            memberInfoItemValueRelationDto.setItemValueId(memberInfoItemValueDto.getId());
            aBoolean=memberInfoItemValueRelationMapper.insert(BeanPowerHelper.mapPartOverrider(memberInfoItemValueRelationDto,MemberInfoItemValueRelation.class))>ZERO;
        }

        return aBoolean;
    }

}