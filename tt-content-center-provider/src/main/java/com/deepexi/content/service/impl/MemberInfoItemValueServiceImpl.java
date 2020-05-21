package com.deepexi.content.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.deepexi.content.domain.dto.MemberInfoItemValueRelationDto;
import com.deepexi.content.domain.eo.MemberInfoItemValueRelation;
import com.deepexi.content.service.MemberInfoItemValueRelationService;
import com.deepexi.content.service.MemberInfoItemValueService;
import com.deepexi.content.domain.eo.MemberInfoItemValue;
import com.deepexi.content.domain.dto.MemberInfoItemValueDto;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.MemberInfoItemValueMapper;
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
public class MemberInfoItemValueServiceImpl implements MemberInfoItemValueService {

    private static final int ZERO=0;

    private static final String REGEX="-";

    private static final String EMPTY="";

    private static final Logger logger = LoggerFactory.getLogger(MemberInfoItemValueServiceImpl.class);

    @Autowired
    private MemberInfoItemValueMapper memberInfoItemValueMapper;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private MemberInfoItemValueRelationService memberInfoItemValueRelationService;

    @Reference(version = "${content.service.version}", check = false)
    private AccountService accountService;

    @Override
    public PageBean findPage(MemberInfoItemValueDto eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<MemberInfoItemValue> list = memberInfoItemValueMapper.findList(eo);
        return new PageBean<>(list);
    }

    @Override
    public List<MemberInfoItemValue> findAll(MemberInfoItemValueDto eo) {
        List<MemberInfoItemValue> list = memberInfoItemValueMapper.findList(eo);
        return list;
    }
    @Override
    public MemberInfoItemValue detail(String pk) {
        return memberInfoItemValueMapper.selectById(pk);
    }

    @Override
    public Boolean create(MemberInfoItemValueDto eo) {
        int result = memberInfoItemValueMapper.insert(BeanPowerHelper.mapPartOverrider(eo,MemberInfoItemValue.class));
        return result > 0;
    }

    @Override
    public Boolean update(String pk,MemberInfoItemValueDto eo) {
        eo.setId(pk);
        int result = memberInfoItemValueMapper.updateById(BeanPowerHelper.mapPartOverrider(eo,MemberInfoItemValue.class));
        return result > 0;
    }

    /**
     * @Description:根据valueIds批量删除
     * @Param: [valueIds]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/18 20:51
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(List<String> valueIds) {
        int result = memberInfoItemValueMapper.deleteByIds(valueIds);
        return result > ZERO;
    }

    /**
     * @Description:增加会员信息项的值
     * @Param: [values]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/17 16:20
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertMemberInfoItemValue(List<String> values,String memberInfoItemId) {
        Boolean aBoolean;
        //创建会员信息项值dto对象的集合
        List<MemberInfoItemValueDto> memberInfoItemValueDtos = new ArrayList<>();
        for (String str:values) {
            MemberInfoItemValueDto memberInfoItemValueDto = new MemberInfoItemValueDto();
            memberInfoItemValueDto.setItemValue(str);
            //添加会员信息项的值到会员信息项值的表中
            MemberInfoItemValue memberInfoItemValue = BeanPowerHelper.mapPartOverrider(memberInfoItemValueDto, MemberInfoItemValue.class);
            aBoolean=memberInfoItemValueMapper.insert(memberInfoItemValue)>ZERO;
            memberInfoItemValueDto.setId(memberInfoItemValue.getId());
            memberInfoItemValueDtos.add(memberInfoItemValueDto);
        }
        //增加会员信息项id和会员信息项值的id到关系表中
        aBoolean = memberInfoItemValueRelationService.insertItemIdAndItemValueId(memberInfoItemValueDtos,memberInfoItemId);

        return aBoolean;
    }


    /**
     * @Description: 根据主键id集合查询会员信息项值
     * @Param: [valueIds]
     * @returns: java.util.List<java.lang.String>
     * @Author: wujie
     * @Date: 2019/9/26 16:58
     */
    @Override
    public List<String> selectValuesByIds(List<String> valueIds) {
        List<String> values = memberInfoItemValueMapper.findById(valueIds);
        return values;
    }

}