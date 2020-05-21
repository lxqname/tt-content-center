package com.deepexi.content.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.deepexi.content.domain.dto.*;
import com.deepexi.content.domain.eo.MemberChooseInterestIdRelation;
import com.deepexi.content.domain.eo.MemberChooseInterestValue;
import com.deepexi.content.domain.eo.MemberInterestIdRelation;
import com.deepexi.content.domain.vo.InterestToH5Vo;
import com.deepexi.content.domain.vo.InterestVo;
import com.deepexi.content.service.*;
import com.deepexi.content.domain.eo.Interest;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.InterestMapper;
import com.deepexi.member.api.MemberExpansionInfoService;
import com.deepexi.member.api.MemberService;
import com.deepexi.user.service.AccountService;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.klock.annotation.Klock;
import org.springframework.boot.autoconfigure.klock.model.LockTimeoutStrategy;
import org.springframework.stereotype.Component;
import com.deepexi.util.BeanPowerHelper;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Component
@Service(version = "${demo.service.version}")
public class InterestServiceImpl implements InterestService {

    private static final int ONE=1;

    private static final int ZERO=0;

    private static final int THREE=3;

    private static final String EMPTY_STR="";

    private static final Logger logger = LoggerFactory.getLogger(InterestServiceImpl.class);

    @Autowired
    private InterestMapper interestMapper;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private InterestMemberRelationService interestMemberRelationService;

    @Autowired
    private MemberInfoItemService memberInfoItemService;

    @Autowired
    private MemberInfoItemValueRelationServiceImpl memberInfoItemValueRelationService;

    @Autowired
    private MemberInfoItemValueService memberInfoItemValueService;

    @Autowired
    private MemberInterestIdRelationService memberInterestIdRelationService;

    @Autowired
    private MemberChooseInterestValueService memberChooseInterestValueService;

    @Autowired
    private MemberChooseInterestIdRelationService memberChooseInterestIdRelationService;

    @Reference(version = "${demo.service.version}", check = false)
    private MemberService memberService;

    @Reference(version = "${demo.service.version}", check = false)
    private MemberExpansionInfoService memberExpansionInfoService;

    /**
     * @Description:分页查询兴趣引导项到页面
     * @Param: [page, size]
     * @returns: com.github.pagehelper.PageInfo
     * @Author: wujie
     * @Date: 2019/9/18 14:37
     */
    @Override
    public PageBean findPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<InterestVo> list = interestMapper.findPage(appRuntimeEnv.getTenantId());
        //添加序号
        //本页初始序号
        Integer nowOrderNum=(page-ONE)*size+ONE;
        for (InterestVo interestVo:list){
            interestVo.setOrderNum(nowOrderNum);
            nowOrderNum++;
        }
        return new PageBean(list);
    }


    /**
     * @Description:增加兴趣项数据
     * @Param: [eo]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/18 21:13
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(lockTimeoutStrategy= LockTimeoutStrategy.FAIL_FAST)
    public Boolean create(InterestDto eo) {

        //查询此时数据库最大的level号
        int level = interestMapper.selectMaxLevel();
        eo.setLevel(++level);
        //判断引导语
        if (eo.getGuideName()==null||EMPTY_STR.equals(eo.getGuideName())){
            eo.setGuideName(eo.getInterestName());
        }
        //dto转eo
        Interest interest = BeanPowerHelper.mapPartOverrider(eo, Interest.class);
        int result = interestMapper.insert(interest);
        Boolean aBoolean=result>ZERO;
        //获取数据库生成的Interest对象的id
        String interestId = interest.getId();
        //获取InterestDto，会员信息项的id
        String memberInfoId = eo.getMemberInfoId();
        //增加两个id到关联表
         aBoolean = interestMemberRelationService.create(interestId, memberInfoId);
        //当会员信息项选择为兴趣引导项成功后，会员信息项的is_interest字段需改变为1
        aBoolean = memberInfoItemService.updateToOne(memberInfoId, new MemberInfoItemDto());


        return aBoolean;
    }

    /**
     * @Description: 根据id修改引导语
     * @Param: [pk, eo]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/18 15:42
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean update(String pk,InterestDto eo) {
        eo.setId(pk);
        //若guidename为""时转eo时会成null
        Interest interest = BeanPowerHelper.mapPartOverrider(eo, Interest.class);
        //处理guidename为null的情况
        if (interest.getGuideName()==null){
            interest.setGuideName(EMPTY_STR);
        }
        int result = interestMapper.updateById(interest);
        return result > ZERO;
    }

    /**
     * @Description:根据id改变status状态
     * @Param: [pk, eo]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/18 20:09
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateStatus(String pk, InterestDto eo) {
        eo.setId(pk);
        int i = interestMapper.updateById(BeanPowerHelper.mapPartOverrider(eo, Interest.class));

        return i>ZERO;
    }


    /**
     * @Description:根据id删除兴趣项(会员信息项中isInterest状态也要改变)
     * @Param: [id]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/18 17:05
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(String id) {
        //先删兴趣项表
        int i = interestMapper.deleteById(id);
        Boolean aBoolean=i>ZERO;
        //查询对应的会员信息项的id
        String memberInfoId = interestMemberRelationService.selectMemberInfoIdByInterestId(id);
        //再删兴趣项和会员项的关联表中数据，一对一关系
        aBoolean = interestMemberRelationService.deleteByInterestId(id);
        //再改变对应的会员信息项中isInterest状态
        //改变会员信息项的isInterest状态
        aBoolean = memberInfoItemService.updateToZero(memberInfoId, new MemberInfoItemDto());
        return aBoolean;
    }

    /**
     * @Description:根据id回显引导语
     * @Param: [id]
     * @returns: com.deepexi.content.domain.vo.InterestVo
     * @Author: wujie
     * @Date: 2019/9/18 15:36
     */
    @Override
    public InterestVo selectGuideName(String id) {
        InterestVo interestVo = interestMapper.selectGuideName(id);
        return interestVo;
    }

    /**
     * @Description:兴趣项排序-上升
     * @Param: [interestIdLevelDto]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/18 17:53
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateLevelUp(String id) {
        //根据id查询本行level
        int rLevel = interestMapper.selectLevelById(id);
        //根据rLevel查询上一行数据的id和level
        InterestVo interestVo=interestMapper.selectIdByUpLevel(rLevel);
        //更新id和dId的序号
        Interest rInterest = new Interest();
        rInterest.setId(id);
        rInterest.setLevel(interestVo.getLevel());
        int i = interestMapper.updateById(rInterest);
        Interest dInterest = new Interest();
        dInterest.setId(interestVo.getId());
        dInterest.setLevel(rLevel);
        int j = interestMapper.updateById(dInterest);
        if (i>ZERO&&j>ZERO){
            return true;
        }
        return false;
    }

    /**
     * @Description:兴趣项排序-下降
     * @Param: [interestIdLevelDto]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/18 17:53
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateLevelDown(String id) {
        //根据id查询本行level
        int rLevel = interestMapper.selectLevelById(id);
        //根据rLevel查询下一行数据的id
        InterestVo interestVo=interestMapper.selectIdByDownLevel(rLevel);
        //更新id和dId的序号
        Interest rInterest = new Interest();
        rInterest.setId(id);
        rInterest.setLevel(interestVo.getLevel());
        int i = interestMapper.updateById(rInterest);
        Interest dInterest = new Interest();
        dInterest.setId(interestVo.getId());
        dInterest.setLevel(rLevel);
        int j = interestMapper.updateById(dInterest);
        if (i>ZERO&&j>ZERO){
            return true;
        }
        return false;
    }


    /**
     * @Description: 查询所有兴趣项到H5页面
     * @Param: []
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/26 16:00
     */
    @Override
    public List<InterestToH5Vo> findAllInterest() {
        List<InterestToH5Vo> interestToH5Vos = interestMapper.selectAllInterest();
        //查询兴趣项类型,关联表属性值,兴趣项值
        if (interestToH5Vos.size()>ZERO){
            for (InterestToH5Vo interestToH5Vo:interestToH5Vos){
                //得到会员信息项的id
                String memberInfoId = interestMemberRelationService.selectMemberInfoIdByInterestId(interestToH5Vo.getId());
                MemberInfoItemDto memberInfoItemDto = memberInfoItemService.selectInfoToH5ById(memberInfoId);
                interestToH5Vo.setInterestType(memberInfoItemDto.getFieldType());
                interestToH5Vo.setTableAttribute(memberInfoItemDto.getTableAttribute());
                interestToH5Vo.setInterestItemType(memberInfoItemDto.getItemType());
                //获取会员信息值的Id的集合
                List<String> valueIds = memberInfoItemValueRelationService.selectByItemId(memberInfoId);
                if (valueIds.size()>ZERO){
                    List<String> values = memberInfoItemValueService.selectValuesByIds(valueIds);
                    interestToH5Vo.setValues(values);
                }else{
                    interestToH5Vo.setValues(new ArrayList<String>());
                }

            }
        }
        return interestToH5Vos;
    }


    /**
     * @Description: 保存H5端会员所选择的兴趣项/值
     * @Param: [memberInterestDto]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/10/8 15:18
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveInterest(MemberInterestDto memberInterestDto) {
        Boolean aBoolean=true;
        String memberId = memberInterestDto.getId();
        //把传来的信息传给会员端，让会员端保存系统预置的部分字段作为会员基本信息(根据interestItemType=2以及兴趣项名来区分字段赋值)
        //本地取出自定义的兴趣项存入相关表中，作为会员中心的扩展信息项
        //presetMemberSaveInterestDtos存预置的，customMemberSaveInterestDtos存放自定义的
        List<MemberSaveInterestDto> memberSaveInterestDtos = memberInterestDto.getMemberSaveInterestDtos();
        List<MemberSaveInterestDto> presetMemberSaveInterestDtos=new ArrayList<MemberSaveInterestDto>();
        List<MemberSaveInterestDto> customMemberSaveInterestDtos=new ArrayList<MemberSaveInterestDto>();
        //筛选预置与自定义
        for (MemberSaveInterestDto memberSaveInterestDto:memberSaveInterestDtos){
            if (memberSaveInterestDto.getInterestItemType()==THREE){
                customMemberSaveInterestDtos.add(memberSaveInterestDto);
            }else{
                presetMemberSaveInterestDtos.add(memberSaveInterestDto);
            }
        }

        //*****调用会员中心的远程接口,传入预置部分的数据*****
        memberInterestDto.setMemberSaveInterestDtos(presetMemberSaveInterestDtos);
        memberService.updateInfoByInterest(memberInterestDto);
        HashMap<String, Object> customHashMap = new HashMap<>();
        for (MemberSaveInterestDto memberSaveInterestDto:customMemberSaveInterestDtos){
            String tableAttribute = memberSaveInterestDto.getTableAttribute();
            //得到兴趣项id
            String interestId = memberSaveInterestDto.getId();
            MemberInterestIdRelation memberInterestIdRelation = new MemberInterestIdRelation();
            memberInterestIdRelation.setMemberId(memberId);
            memberInterestIdRelation.setInterestId(interestId);
            //默认状态为启用
            memberInterestIdRelation.setStatus(ONE);
            //1.先将会员id和会员所选择的兴趣项id存入coc_member_interest_id_relation表中
            aBoolean = memberInterestIdRelationService.create(memberInterestIdRelation);
            //得到对应的兴趣项的值的集合
            //2.再将会员选择的兴趣项的值存入coc_member_choose_interest_value表
            List<String> values = memberSaveInterestDto.getValues();
            //values转JSON字符串
            String valuesJson = JSONArray.toJSONString(values);
            customHashMap.put(tableAttribute,valuesJson);
            if (values.size()>ZERO){
               for (String value:values){
                   MemberChooseInterestValue memberChooseInterestValue = new MemberChooseInterestValue();
                   memberChooseInterestValue.setValue(value);
                   aBoolean = memberChooseInterestValueService.create(memberChooseInterestValue);
                   //获取自增的id,并和兴趣项的id一起存入coc_member_choose_interest_id_relation
                   MemberChooseInterestIdRelation memberChooseInterestIdRelation = new MemberChooseInterestIdRelation();
                   memberChooseInterestIdRelation.setMemberChooseId(memberSaveInterestDto.getId());
                   memberChooseInterestIdRelation.setMemberChooseValueId(memberChooseInterestValue.getId());
                   memberChooseInterestIdRelation.setMemberId(memberId);
                   aBoolean = memberChooseInterestIdRelationService.create(memberChooseInterestIdRelation);
               }
            }
        }
        memberExpansionInfoService.saveMemberExpansionInfo(customHashMap,memberId);
        return aBoolean;
    }


    /**
     * @Description: 远程调用，根据会员id查询会员所选的自定义的兴趣的名称和值并且作为会员的扩展信息
     * @Param: [memberId]
     * @returns: java.util.List<com.deepexi.content.domain.eo.Interest>
     * @Author: wujie
     * @Date: 2019/10/9 17:53
     */
    @Override
    public List<MemberInterestRpcDto> findMemberInterest(String memberId) {
        List<MemberInterestRpcDto> memberInterestRpcDtos = new ArrayList<>();
        //根据会员id到coc_member_interest_id_relation表查询兴趣项id
        List<String> interestIds = memberInterestIdRelationService.selectInterestIdByMemberId(memberId);
        if (interestIds.size()>ZERO){
            for (String interestId:interestIds){
                MemberInterestRpcDto memberInterestRpcDto = new MemberInterestRpcDto();
                //查询兴趣项名称
                Interest interest = interestMapper.selectById(interestId);
                memberInterestRpcDto.setInterestName(interest.getInterestName());
                //查询兴趣项值的id
                List<String> valueIds = memberChooseInterestIdRelationService.selectMemberChooseValueId(interestId,memberId);
                List<String> values = new ArrayList<>();
                if (valueIds.size()>ZERO){
                    //查询会员所选兴趣值
                    for (String valueId:valueIds){
                        String value = memberChooseInterestValueService.selectInterestValue(valueId);
                        values.add(value);
                    }
                }
                memberInterestRpcDto.setInterestValues(values);
                memberInterestRpcDtos.add(memberInterestRpcDto);
            }
        }
        return memberInterestRpcDtos;
    }



    @Override
    public List<Interest> findAll(InterestDto eo) {
        List<Interest> list = interestMapper.findList(eo);
        return list;
    }
    @Override
    public Interest detail(String pk) {
        return interestMapper.selectById(pk);
    }
    @Override
    public Boolean delete(String...pk) {
        int result = interestMapper.deleteByIds(pk);
        return result > 0;
    }
}
