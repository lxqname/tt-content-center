package com.deepexi.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.deepexi.content.domain.dto.MemberInterestIdRelationDto;
import com.deepexi.content.domain.eo.MemberInterestIdRelation;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.MemberInterestIdRelationMapper;
import com.deepexi.content.service.MemberInterestIdRelationService;
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
public class MemberInterestIdRelationServiceImpl implements MemberInterestIdRelationService {

    private static final int ONE=1;

    private static final int ZERO=0;

    private static final Logger logger = LoggerFactory.getLogger(MemberInterestIdRelationServiceImpl.class);

    @Autowired
    private MemberInterestIdRelationMapper memberInterestIdRelationMapper;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Override
    public PageBean findPage(MemberInterestIdRelationDto eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<MemberInterestIdRelation> list = memberInterestIdRelationMapper.findList(eo);
        return new PageBean<>(list);
    }

    @Override
    public List<MemberInterestIdRelation> findAll(MemberInterestIdRelationDto eo) {
        List<MemberInterestIdRelation> list = memberInterestIdRelationMapper.findList(eo);
        return list;
    }
    @Override
    public MemberInterestIdRelation detail(String pk) {
        return memberInterestIdRelationMapper.selectById(pk);
    }

    @Override
    public Boolean create(MemberInterestIdRelationDto eo) {
        int result = memberInterestIdRelationMapper.insert(BeanPowerHelper.mapPartOverrider(eo, MemberInterestIdRelation.class));
        return result > 0;
    }


    /**
     * @Description: 增加会员id和兴趣项id到id关联表
     * @Param: [eo]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/10/8 15:39
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean create(MemberInterestIdRelation eo) {
        int i = memberInterestIdRelationMapper.insert(eo);
        return i>ONE;
    }

    @Override
    public Boolean update(String pk, MemberInterestIdRelationDto eo) {
        eo.setId(pk);
        int result = memberInterestIdRelationMapper.updateById(BeanPowerHelper.mapPartOverrider(eo, MemberInterestIdRelation.class));
        return result > 0;
    }

    @Override
    public Boolean delete(String...pk) {
        int result = memberInterestIdRelationMapper.deleteByIds(pk);
        return result > 0;
    }


    /**
     * @Description: 更新status状态
     * @Param: [memberInterestIdRelationDto]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/10/9 17:17
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateByInterestId(MemberInterestIdRelationDto memberInterestIdRelationDto) {
        int i = memberInterestIdRelationMapper.updateByInterestId(memberInterestIdRelationDto);
        return i>ZERO;
    }
    /**
     * @Description: 根据会员id查询兴趣id
     * @Param: [memberId]
     * @returns: java.util.List<java.lang.String>
     * @Author: wujie
     * @Date: 2019/10/9 18:48
     */
    @Override
    public List<String> selectInterestIdByMemberId(String memberId) {
        List<String> interestIds = memberInterestIdRelationMapper.selectInterestIdByMemberId(memberId);
        return interestIds;
    }

}