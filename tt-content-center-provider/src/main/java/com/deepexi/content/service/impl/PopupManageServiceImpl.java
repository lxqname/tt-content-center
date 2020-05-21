package com.deepexi.content.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.deepexi.common.enums.ResultEnum;
import com.deepexi.common.enums.StatusEnum;
import com.deepexi.content.domain.dto.*;
import com.deepexi.content.domain.eo.AdvertContentRelation;
import com.deepexi.content.domain.eo.PopupManage;
import com.deepexi.content.domain.eo.PopupMemberLevelRelation;
import com.deepexi.content.domain.vo.PopupManageDetailVo;
import com.deepexi.content.domain.vo.PopupManageShowVo;
import com.deepexi.content.enums.*;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.PopupManageMapper;
import com.deepexi.content.service.*;
import com.deepexi.member.api.MemberService;
import com.deepexi.member.domain.eo.Member;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @Author:LuFeng
 */
@Component
@Service(version = "${demo.service.version}")
public class PopupManageServiceImpl implements PopupManageService {

    private static final Logger logger = LoggerFactory.getLogger(PopupManageServiceImpl.class);

    @Autowired
    private PopupManageMapper popupManageMapper;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private AdvertContentRelationService advertContentRelationService;

    @Autowired
    private AdvertContentService advertContentService;

    @Autowired
    private CocRedisService cocRedisService;

    @Autowired
    private PopupMemberLevelRelationService popupMemberLevelRelationService;

    @Reference(version = "${demo.service.version}",check = false)
    private MemberService memberService;

    /**
     * 弹窗弹出记录
     */
    private final String REDIS_POPUP_MANAGE_COUNT = "coc:popup:manage:count";

    @Override
    public PageBean<PopupManage> findPage(PopupManageDto eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<PopupManage> list = popupManageMapper.findList(eo, appRuntimeEnv.getTenantId());
        return new PageBean<>(list);
    }

    @Override
    public List<PopupManage> findAll(PopupManageDto eo) {
        List<PopupManage> list = popupManageMapper.findList(eo, appRuntimeEnv.getTenantId());
        return list;
    }

    @Override
    public PopupManageDetailVo detail(String pk) {
        PopupManage popupManage = popupManageMapper.selectById(pk);
        if (null == popupManage){
            throw new ApplicationException(ResultEnum.getParameterError("不存在此弹窗ID:"+pk));
        }
        PopupManageDetailVo vo = getPopupManageDetailVO(popupManage);
        return vo;
    }

    private PopupManageDetailVo getPopupManageDetailVO(PopupManage popupManage) {
        PopupManageDetailVo vo = new PopupManageDetailVo();
        BeanUtils.copyProperties(popupManage, vo);
        List<AdvertContentRelation> relationList = advertContentRelationService.getBySetTypeId(popupManage.getId());
        if (CollectionUtil.isEmpty(relationList)){
            throw new ApplicationException(ResultEnum.getParameterError("查询不到相关信息"));
        }
        int advertType = relationList.get(0).getAdvertType();
        vo.setAdvertTypeEnum(AdvertTypeEnum.getEnumByValue(advertType));
        vo.setSetTypeEnum(ContentSetTypeEnum.getEnumByValue(relationList.get(0).getSetType()));
        vo.setApplicationTypeEnum(BelongApplicationTypeEnum.getEnumByValue(popupManage.getBelongApplication()));
        vo.setTriggerEventTypeEnum(TriggerEventTypeEnum.getEnumByValue(popupManage.getTriggerEvent()));

        List<String> levels = getMemberLevel(popupManage);
        vo.setLevelIds(levels);
        List<AdvertContentJsonDto> jsonDtoList = advertContentService.getAdvertContentJsonDto(AdvertTypeEnum.getEnumByValue(advertType), popupManage.getId());
        vo.setJsonDtoList(jsonDtoList);
        return vo;
    }

    private List<String> getMemberLevel(PopupManage popupManage) {
        List<String> levels = new ArrayList<>();
        List<PopupMemberLevelRelation> popupMemberLevelRelation = popupMemberLevelRelationService.getByPopupId(popupManage.getId());
        if (!CollectionUtil.isEmpty(popupMemberLevelRelation)){
            popupMemberLevelRelation.stream().forEach(relation->{
                levels.add(relation.getMemberLevelId());
            });
        }
        return levels;
    }

    /**
     * 弹出弹窗
     * @param eo
     * @return
     */
    @Override
    public PopupManageShowVo detailQuery(PopupManageQueryDto eo) {
        PopupManageShowVo vo = new PopupManageShowVo();
        List<PopupManage> popupManages = popupManageMapper.listByQuery(eo, appRuntimeEnv.getTenantId());
        if (CollectionUtil.isEmpty(popupManages)){
            throw new ApplicationException(ResultEnum.getParameterError("弹窗为空:"+eo));
        }
        PopupManage popupManage = popupItem(popupManages,eo.getAccountId());
        if (null == popupManage){
            vo.setFlag(false);
            return vo;
        }

        vo.setFlag(true);
        vo.setDetailVO(getPopupManageDetailVO(popupManage));
        return vo;
    }

    private PopupManage popupItem(List<PopupManage> popupManages, String accountId) {
        Member member = memberService.getMemberByAccountId(accountId);
        for (int i = 0; i < popupManages.size(); i++){
            PopupManage popupManage = popupManages.get(i);
            Boolean tag =  memberTypeLevelJudge(member, popupManage);
            if (!tag){
                continue;
            }
            if (allowPopup(popupManage, accountId)){
                return popupManage;
            }
        }
        return null;
    }

    /**
     * 判断会员等级是否满足弹出条件
     * @param member
     * @param popupManage
     * @return
     */
    private Boolean memberTypeLevelJudge(Member member, PopupManage popupManage) {
        if (ObjectUtil.equal(MemberTypeEnum.ALL.getValue(),popupManage.getMemberType())){
            return true;
        }else if(ObjectUtil.equal(MemberTypeEnum.REGISTER.getValue(),popupManage.getMemberType())){
            if (member.getBindMobile()){
                List<String> levels = getMemberLevel(popupManage);
                return levels.contains(member.getMemberLevelId());
            }
        }else {
            if (!member.getBindMobile()){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否弹出
     * @param popupManage
     * @return
     */
    private Boolean allowPopup(PopupManage popupManage, String accountId) {
        Boolean allowPopup = false;
        switch (TriggerEventTypeEnum.getEnumByValue(popupManage.getTriggerEvent())) {
            case TODAY_FIRST_LOGIN:
                if (StringUtils.isEmpty(appRuntimeEnv.getToken())){
                    return false;
                }

                allowPopup = this.saveRedisMemberPopupRecord(popupManage, accountId);
                break;

            case TODAY_FIRST_ENTER:
                allowPopup = this.saveRedisMemberPopupRecord(popupManage, accountId);
                break;

            default:
        }
        return allowPopup;
    }

    private Boolean saveRedisMemberPopupRecord(PopupManage popupManage, String accountId) {
        Integer seconds = null;
        if (ObjectUtil.equal(popupManage.getPopupType(), PopupTypeEnum.ROUTINE_ACTIVITY.getValue())){
            seconds = getToDayLastSecond();
        }

        Double score = cocRedisService.getRedisZsetScore(REDIS_POPUP_MANAGE_COUNT,popupManage.getId()+":"+accountId);
        if (null != score){
            return false;
        }
        cocRedisService.setRedisZset(REDIS_POPUP_MANAGE_COUNT,popupManage.getId()+":"+accountId,seconds);
        return true;
    }

    /**
     * 获取今天还剩下多少秒
     * @return
     */
    private Integer getToDayLastSecond(){
        Calendar curDate = Calendar.getInstance();
        Calendar tommorowDate = new GregorianCalendar(curDate
                .get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate
                .get(Calendar.DATE) + 1, 0, 0, 0);
        return (int)(tommorowDate.getTimeInMillis() - curDate .getTimeInMillis()) / 1000;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean create(PopupManageCreateDto eo) {
        verificationPopupManageCreate(eo);

        PopupManage popupManage=transformPopupManage(eo);

        int result = popupManageMapper.insert(popupManage);
        if (result>0){
            PopupManage popupByName = getByName(popupManage.getName());
            eo.getLevelIds().stream().forEach(level->{
                PopupMemberLevelRelation popupMemberLevelRelation=new PopupMemberLevelRelation();
                popupMemberLevelRelation.setPopupId(popupByName.getId());
                popupMemberLevelRelation.setMemberLevelId(level);
                popupMemberLevelRelationService.create(popupMemberLevelRelation);
            });
            return advertContentRelationService.createAdvertContentRelation(ContentSetTypeEnum.POPUP_MANAGE, popupManage.getId(), eo.getJsonDtoList());
        }
        return false;
    }

    private PopupManage transformPopupManage(PopupManageCreateDto eo) {
        PopupManage popupManage = new PopupManage();
        BeanUtils.copyProperties(eo, popupManage);

        popupManage.setTriggerEvent(eo.getTriggerEventTypeEnum().getValue());
        popupManage.setBelongApplication(eo.getApplicationTypeEnum().getValue());
        return popupManage;
    }

    /**
     * 创建弹窗验证
     * @param eo
     */
    private void verificationPopupManageCreate(PopupManageCreateDto eo) {
        PopupManage popupManage = this.getByName(eo.getName());
        if (null != popupManage){
            throw new ApplicationException(ResultEnum.getParameterError("弹窗名称不允许重复"));
        }
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean update(String id, PopupManageUpdateDto eo) {
        eo.setId(id);
        List<AdvertContentRelation> relationList = advertContentRelationService.getBySetTypeId(id);
        verificationPopupManageUpdate(eo);
        if (CollectionUtil.isEmpty(relationList)){
            throw new ApplicationException(ResultEnum.getParameterError("修改失败"));
        }
        PopupManage popupManage = transformPopupManage(eo);
        int result = popupManageMapper.updateById(popupManage);
        if (result > 0){
            int deleteResult = popupMemberLevelRelationService.deleteByPopupId(id);
            if (deleteResult > 0){
                eo.getLevelIds().stream().forEach(level->{
                    PopupMemberLevelRelation popupMemberLevelRelation = new PopupMemberLevelRelation();
                    popupMemberLevelRelation.setPopupId(id);
                    popupMemberLevelRelation.setMemberLevelId(level);
                    popupMemberLevelRelationService.create(popupMemberLevelRelation);
                });
            }
            advertContentRelationService.deleteByAdvertTypeAndSetTypeId(AdvertTypeEnum.getEnumByValue(relationList.get(0).getAdvertType()), id);
            return advertContentRelationService.createAdvertContentRelation(ContentSetTypeEnum.POPUP_MANAGE, id,eo.getJsonDtoList()
            );
        }
        return false;
    }

    /**
     *修改弹窗验证
     * @param eo
     */
    private void verificationPopupManageUpdate(PopupManageUpdateDto eo){
        PopupManage popupManage = this.getByName(eo.getName());
        if (null != popupManage && ObjectUtils.notEqual(popupManage.getId(), eo.getId())){
            throw new ApplicationException(ResultEnum.getParameterError("弹窗名称不能重复"));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(String id) {
        int result = popupManageMapper.deleteByIds(id);
        List<AdvertContentRelation> relationList = advertContentRelationService.getBySetTypeId(id);
        if (CollectionUtils.isEmpty(relationList)){
            throw new ApplicationException(ResultEnum.getParameterError("删除失败"));
        }
        if (result > 0){
            int deleteResult = popupMemberLevelRelationService.deleteByPopupId(id);
            if (deleteResult > 0){
                return advertContentRelationService.deleteByAdvertTypeAndSetTypeId(AdvertTypeEnum.getEnumByValue(relationList.get(0).getAdvertType()), id);
            }
        }
        return false;
    }

    @Override
    public PopupManage getByName(String name) {
        return popupManageMapper.getByName(name, appRuntimeEnv.getTenantId());
    }

    @Override
    public Boolean updateStatus(String id, StatusEnum statusEnum) {
        int result = popupManageMapper.updateStatus(id, statusEnum);
        return result>0;
    }

    @Override
    public void initPopupContent() {
        cocRedisService.deleteRedisZsetAllKey(REDIS_POPUP_MANAGE_COUNT);
    }
}