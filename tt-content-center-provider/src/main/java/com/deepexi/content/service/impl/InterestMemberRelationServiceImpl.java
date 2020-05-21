package com.deepexi.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.deepexi.content.service.InterestMemberRelationService;
import com.deepexi.content.domain.eo.InterestMemberRelation;
import com.deepexi.content.domain.dto.InterestMemberRelationDto;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.InterestMemberRelationMapper;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.deepexi.util.BeanPowerHelper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
@Service(version = "${demo.service.version}")
public class InterestMemberRelationServiceImpl implements InterestMemberRelationService {

    private static final int ZERO=0;

    private static final Logger logger = LoggerFactory.getLogger(InterestMemberRelationServiceImpl.class);

    @Autowired
    private InterestMemberRelationMapper interestMemberRelationMapper;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Override
    public PageBean findPage(InterestMemberRelationDto eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<InterestMemberRelation> list = interestMemberRelationMapper.findList(eo);
        return new PageBean<>(list);
    }

    @Override
    public List<InterestMemberRelation> findAll(InterestMemberRelationDto eo) {
        List<InterestMemberRelation> list = interestMemberRelationMapper.findList(eo);
        return list;
    }
    @Override
    public InterestMemberRelation detail(String pk) {
        return interestMemberRelationMapper.selectById(pk);
    }

    @Override
    public Boolean update(String pk,InterestMemberRelationDto eo) {
        eo.setId(pk);
        int result = interestMemberRelationMapper.updateById(BeanPowerHelper.mapPartOverrider(eo,InterestMemberRelation.class));
        return result > 0;
    }

    @Override
    public Boolean delete(String...pk) {
        int result = interestMemberRelationMapper.deleteByIds(pk);
        return result > 0;
    }


    /**
     * @Description:增加两个关联id到关联表中
     * @Param: [interestId, memberInfoId]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/18 11:41
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean create(String interestId,String memberInfoId) {
        //封装这两个id到InterestMemberRelation中
        InterestMemberRelation interestMemberRelation = new InterestMemberRelation();
        interestMemberRelation.setInterestId(interestId);
        interestMemberRelation.setMemberInformationId(memberInfoId);
        int result = interestMemberRelationMapper.insert(interestMemberRelation);
        return result > ZERO;
    }

    /**
     * @Description:根据interestId删除关系表信数据
     * @Param: [interestId]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/18 17:16
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteByInterestId(String interestId) {
        int i = interestMemberRelationMapper.deleteByInterestId(interestId);

        return i>ZERO;
    }

    /**
     * @Description: 根据兴趣项id查询会员信息项id
     * @Param: [interestId]
     * @returns: java.lang.String
     * @Author: wujie
     * @Date: 2019/9/18 17:22
     */
    @Override
    public String selectMemberInfoIdByInterestId(String interestId) {
        String memberInfoId = interestMemberRelationMapper.selectMemberInfoIdByInterestId(interestId);
        return memberInfoId;
    }

    /**
     * @Description:到兴趣项-会员信息项关联表中查询对应的兴趣项id
     * @Param: [memberInfoId]
     * @returns: java.lang.String
     * @Author: wujie
     * @Date: 2019/9/18 20:01
     */
    @Override
    public String selectInterestIdByMemberInfoId(String memberInfoId) {
        String interestId = interestMemberRelationMapper.selectInterestIdByMemberInfoId(memberInfoId);
        return interestId;
    }

}