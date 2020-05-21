package com.deepexi.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.deepexi.content.domain.dto.MemberChooseInterestIdRelationDto;
import com.deepexi.content.domain.eo.MemberChooseInterestIdRelation;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.MemberChooseInterestIdRelationMapper;
import com.deepexi.content.service.MemberChooseInterestIdRelationService;
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
public class MemberChooseInterestIdRelationServiceImpl implements MemberChooseInterestIdRelationService {

    private static final int ZERO=0;

    private static final Logger logger = LoggerFactory.getLogger(MemberChooseInterestIdRelationServiceImpl.class);

    @Autowired
    private MemberChooseInterestIdRelationMapper memberChooseInterestIdRelationMapper;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Override
    public PageBean findPage(MemberChooseInterestIdRelationDto eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<MemberChooseInterestIdRelation> list = memberChooseInterestIdRelationMapper.findList(eo);
        return new PageBean<>(list);
    }

    @Override
    public List<MemberChooseInterestIdRelation> findAll(MemberChooseInterestIdRelationDto eo) {
        List<MemberChooseInterestIdRelation> list = memberChooseInterestIdRelationMapper.findList(eo);
        return list;
    }
    @Override
    public MemberChooseInterestIdRelation detail(String pk) {
        return memberChooseInterestIdRelationMapper.selectById(pk);
    }

    @Override
    public Boolean create(MemberChooseInterestIdRelationDto eo) {
        int result = memberChooseInterestIdRelationMapper.insert(BeanPowerHelper.mapPartOverrider(eo, MemberChooseInterestIdRelation.class));
        return result > 0;
    }


    /**
     * @Description: 增加会员所选择的兴趣项值的id和兴趣项的id到表中
     * @Param: [eo]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/10/9 16:57
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean create(MemberChooseInterestIdRelation eo) {
        int i = memberChooseInterestIdRelationMapper.insert(eo);
        return i>ZERO;
    }

    @Override
    public Boolean update(String pk, MemberChooseInterestIdRelationDto eo) {
        eo.setId(pk);
        int result = memberChooseInterestIdRelationMapper.updateById(BeanPowerHelper.mapPartOverrider(eo, MemberChooseInterestIdRelation.class));
        return result > 0;
    }

    @Override
    public Boolean delete(String...pk) {
        int result = memberChooseInterestIdRelationMapper.deleteByIds(pk);
        return result > 0;
    }


    /**
     * @Description: 关联表根据兴趣id查询会员所选兴趣的值的id
     * @Param: interestId 兴趣项id memberId 会员id
     * @returns: java.util.List<java.lang.String>
     * @Author: wujie
     * @Date: 2019/10/9 19:03
     */
    @Override
    public List<String> selectMemberChooseValueId(String interestId,String memberId) {
        List<String> valueIds = memberChooseInterestIdRelationMapper.selectMemberChooseValueId(interestId,memberId);
        return valueIds;
    }

}