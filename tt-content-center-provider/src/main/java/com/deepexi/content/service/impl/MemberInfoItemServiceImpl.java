package com.deepexi.content.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.deepexi.common.enums.ResultEnum;
import com.deepexi.content.domain.dto.*;
import com.deepexi.content.domain.eo.MemberInterestIdRelation;
import com.deepexi.content.domain.vo.InterestToH5Vo;
import com.deepexi.content.domain.vo.MemberInfoItemVo;
import com.deepexi.content.domain.vo.MemberToInterestVo;
import com.deepexi.content.mapper.MemberInfoItemValueMapper;
import com.deepexi.content.mapper.MemberInfoItemValueRelationMapper;
import com.deepexi.content.service.*;
import com.deepexi.content.domain.eo.MemberInfoItem;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.MemberInfoItemMapper;
import com.deepexi.member.api.MemberExpansionInfoService;
import com.deepexi.member.domain.dto.AddNewMemberFieldDto;
import com.deepexi.user.service.AccountService;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.klock.annotation.Klock;
import org.springframework.boot.autoconfigure.klock.model.LockTimeoutStrategy;
import org.springframework.stereotype.Component;
import com.deepexi.util.BeanPowerHelper;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
@Service(version = "${demo.service.version}")
public class MemberInfoItemServiceImpl implements MemberInfoItemService {

    private static final int ZERO=0;

    private static final int ONE=1;

    private static final int THREE=3;

    /**
     *会员信息项类型(自定义类型)
     */
    private static final int ITEMTYPE=3;

    private static final Logger logger = LoggerFactory.getLogger(MemberInfoItemServiceImpl.class);

    @Autowired
    private MemberInfoItemMapper memberInfoItemMapper;

    @Autowired
    private MemberInfoItemValueRelationMapper memberInfoItemValueRelationMapper;

    @Autowired
    private MemberInfoItemValueMapper memberInfoItemValueMapper;

    @Autowired
    private MemberInfoItemValueService memberInfoItemValueService;

    @Autowired
    private InterestMemberRelationService interestMemberRelationService;

    @Autowired
    private InterestService interestService;

    @Autowired
    private MemberInfoItemValueRelationService memberInfoItemValueRelationService;

    @Autowired
    private MemberInterestIdRelationService memberInterestIdRelationService;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Reference(version = "${demo.service.version}", check = false)
    private AccountService accountService;

    @Reference(version = "${demo.service.version}", check = false)
    private MemberExpansionInfoService memberExpansionInfoService;

    /**
     * @Description:分页查询会员信息
     * @Param: [eo, page, size]
     * @returns: com.github.pagehelper.PageInfo<com.deepexi.content.domain.vo.MemberInfoItemVo>
     * @Author: wujie
     * @Date: 2019/9/16 15:42
     */
    @Override
    public PageBean<MemberInfoItemVo> findPage(MemberInfoItemConditionDto dto, Integer page, Integer size) {
        //添加序号
        //本页初始序号
        Integer nowOrderNum=(page-ONE)*size+ONE;
        //获取tenantId
        dto.setTenantCode(appRuntimeEnv.getTenantId());
        PageHelper.startPage(page, size);
        List<MemberInfoItemVo> list = memberInfoItemMapper.findPage(dto);
        //遍历list集合，根据id获取会员信息-会员信息值关联表中的会员信息值的id,并用此id获取该项会员信息对应的会员信息的值
        for (MemberInfoItemVo memberInfoItemVo:list) {
            memberInfoItemVo.setOrderNum(nowOrderNum);
            nowOrderNum++;
            String id = memberInfoItemVo.getId();
            //获得对应的会员信息项值的id集合
            List<String> itemValueIds = memberInfoItemValueRelationMapper.findById(id);
            //必须当itemValueIds集合的长度不为0时才开始下一步的查询，否则在xxxmapper.xml的foreach里会抛异常
            if (itemValueIds.size()>ZERO){
                //用此id集合查找会员信息项值
                List<String>  values= memberInfoItemValueMapper.findById(itemValueIds);
                //将信息项值的集合赋值给memberInfoItemVo
                memberInfoItemVo.setItemValues(values);
            }

        }
        return new PageBean<>(list);
    }


    /**
     * @Description:分页查询会员信息项展示到兴趣项选择页面
     * @Param: [page, size]
     * @returns: com.github.pagehelper.PageInfo<com.deepexi.content.domain.vo.MemberToInterestVo>
     * @Author: wujie
     * @Date: 2019/9/17 19:56
     */
    @Override
    public PageBean<MemberToInterestVo> findPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<MemberToInterestVo> list = memberInfoItemMapper.findPageToInterest(appRuntimeEnv.getTenantId());
        //遍历list集合，根据id获取会员信息-会员信息值关联表中的会员信息值的id,并用此id获取该项会员信息对应的会员信息的值
        for (MemberToInterestVo memberToInterestVo:list) {
            String id = memberToInterestVo.getId();
            //获得对应的会员信息项值的id集合
            List<String> itemValueIds = memberInfoItemValueRelationMapper.findById(id);
            //必须当itemValueIds集合的长度不为0时才开始下一步的查询，否则在xxxmapper.xml的foreach里会抛异常
            if (itemValueIds!=null&&itemValueIds.size()>ZERO){
                //用此id集合查找会员信息项值
                List<String>  values= memberInfoItemValueMapper.findById(itemValueIds);
                //将信息项值的集合赋值给memberToInterestVo
                memberToInterestVo.setItemValues(values);
            }

        }
        return new PageBean<>(list);
    }


    /**
     * @Description:增加会员信息项信息
     * @Param: [eo]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/17 10:00
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(lockTimeoutStrategy= LockTimeoutStrategy.FAIL_FAST)
    public Boolean create(MemberInfoItemDto dto) {
        Integer isLabel = dto.getIsLabel();
        //tableAttribute全部转小写
        String tableAttribute = dto.getTableAttribute();
        String lowerTableAttribute = tableAttribute.toLowerCase();
        if (tableAttribute!=null){
            dto.setTableAttribute(lowerTableAttribute);
        }
        //判断lowerTableAttribute是否唯一
        List<String> ids = memberInfoItemMapper.checkLowerTableAttribute(lowerTableAttribute);
        if (ids.size()>ZERO){
            throw new ApplicationException(ResultEnum.setMsg(ResultEnum.PARAMETER_ERROR, "参数异常，关联表属性名重复"));
        }
        //对象转换 dto>>eo
        MemberInfoItem memberInfoItem = BeanPowerHelper.mapPartOverrider(dto, MemberInfoItem.class);
        //获取此时数据库中的最大序列号
        int maxSequence = memberInfoItemMapper.selectMaxSequence();
        //给memberInfoItem设置最大序列号
        memberInfoItem.setSequence(++maxSequence);
        //给memberInfoItem设置会员信息项类型 默认为3
        memberInfoItem.setItemType(THREE);
        //插入memberInfoItem
        int result = memberInfoItemMapper.insert(memberInfoItem);
        //获取会员信息项表自生成的id
        String memberInfoItemId = memberInfoItem.getId();
        //获取会员信息项值的集合
        List<String> values = dto.getValues();
        //判断values是否为null和size()是否大于0
        Boolean aboolean=result>ZERO;
        if (values!=null&&values.size()>ZERO){
            //增加会员信息项的值
            aboolean = memberInfoItemValueService.insertMemberInfoItemValue(values,memberInfoItemId);
        }
        //判断是否同步为标签,是的话同步打标到原生标签
       if (isLabel==ONE){
       //同步
        AddNewMemberFieldDto addNewMemberFieldDto = new AddNewMemberFieldDto();
        addNewMemberFieldDto.setFieldName(dto.getTableAttribute());
        addNewMemberFieldDto.setFieldType(dto.getFieldType());
        addNewMemberFieldDto.setFieldComment(dto.getName());
        List<AddNewMemberFieldDto> addNewMemberFieldDtos = new ArrayList<>();
        addNewMemberFieldDtos.add(addNewMemberFieldDto);
        memberExpansionInfoService.addMemberExpansionInfoField(addNewMemberFieldDtos,isLabel);
  }
        return aboolean;
    }


    /**
     * @Description:  IsInterest改变为1
     * @Param: [pk, eo]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/18 17:37
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateToOne(String pk,MemberInfoItemDto eo) {
        eo.setId(pk);
        //is_Interest字段变为1
        eo.setIsInterest(ONE);
        int result = memberInfoItemMapper.updateById(BeanPowerHelper.mapPartOverrider(eo,MemberInfoItem.class));
        return result > ZERO;
    }


    /**
     * @Description:IsInterest改变为0
     * @Param: [pk, eo]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/18 17:37
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateToZero(String pk, MemberInfoItemDto eo) {
        eo.setId(pk);
        //is_Interest字段变为0
        eo.setIsInterest(ZERO);
        int result = memberInfoItemMapper.updateById(BeanPowerHelper.mapPartOverrider(eo,MemberInfoItem.class));
        return result > ZERO;
    }


    /**
     * @Description:根据id启用会员信息项
     * @Param: [id]
     * @returns: int
     * @Author: wujie
     * @Date: 2019/9/16 15:52
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean enable(String id) {

        //把更新所需信息封装到实体类中
        MemberInfoItem memberInfoItem = new MemberInfoItem();
        memberInfoItem.setId(id);
        memberInfoItem.setStatus(ONE);

        int i = memberInfoItemMapper.updateById(memberInfoItem);
        Boolean aBoolean=i>ZERO;
        //关联兴趣项****************(改变兴趣项的状态)
        //到兴趣项-会员信息项关联表中查询对应的兴趣项id
        String interestId=interestMemberRelationService.selectInterestIdByMemberInfoId(id);
        //根据interestId到兴趣项表中改变status为1
        //需判断interestId是否已经被删除
        if (interestId!=null){
            InterestDto interestDto = new InterestDto();
            interestDto.setStatus(ONE);
            aBoolean = interestService.updateStatus(interestId, interestDto);
        }
        // 关联会员详情 》扩展信息
        //改变coc_member_interest_id_relation表中的status即改变了会员所选的兴趣项的状态直接影响到扩展信息
        MemberInterestIdRelationDto memberInterestIdRelationDto = new MemberInterestIdRelationDto();
        memberInterestIdRelationDto.setStatus(ONE);
        memberInterestIdRelationDto.setInterestId(interestId);
        memberInterestIdRelationDto.setTenantCode(appRuntimeEnv.getTenantId());
        memberInterestIdRelationDto.setUpdatedAt(new Date());
        memberInterestIdRelationDto.setUpdatedBy(accountService.getLoginAccountIdByToken(appRuntimeEnv.getToken()));
        aBoolean = memberInterestIdRelationService.updateByInterestId(memberInterestIdRelationDto);
        return aBoolean;
    }

    /**
     * @Description:根据id禁用会员信息项
     * @Param: [id]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/16 16:19
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean prohibit(String id) {


        //把更新所需信息封装到实体类中
        MemberInfoItem memberInfoItem = new MemberInfoItem();
        memberInfoItem.setId(id);
        memberInfoItem.setStatus(ZERO);
        int i = memberInfoItemMapper.updateById(memberInfoItem);
        Boolean aBoolean=i>0;
        //关联兴趣项****************(改变兴趣项的状态)
        //到兴趣项-会员信息项关联表中查询对应的兴趣项id
        String interestId=interestMemberRelationService.selectInterestIdByMemberInfoId(id);
        //根据interestId到兴趣项表中改变status为0
        if (interestId!=null){
            InterestDto interestDto = new InterestDto();
            interestDto.setStatus(ZERO);
            aBoolean = interestService.updateStatus(interestId, interestDto);
        }
        // 关联会员详情 》扩展信息
        //改变coc_member_interest_id_relation表中的status即改变了会员所选的兴趣项的状态直接影响到扩展信息
        MemberInterestIdRelationDto memberInterestIdRelationDto = new MemberInterestIdRelationDto();
        memberInterestIdRelationDto.setStatus(ZERO);
        memberInterestIdRelationDto.setInterestId(interestId);
        memberInterestIdRelationDto.setTenantCode(appRuntimeEnv.getTenantId());
        memberInterestIdRelationDto.setUpdatedAt(new Date());
        memberInterestIdRelationDto.setUpdatedBy(accountService.getLoginAccountIdByToken(appRuntimeEnv.getToken()));
        aBoolean = memberInterestIdRelationService.updateByInterestId(memberInterestIdRelationDto);
        return aBoolean;
    }


    /**
     * @Description:会员信息项排序-上升
     * @Param: [memberIdSequenceDto]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/16 17:44
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean sequenceUp(String id) {
        //根据id查询对应sequence
        int rSequence=memberInfoItemMapper.selectSequenceById(id);
        //根据rSequence上一行的id和sequence
        MemberInfoItemVo memberInfoItemVo = memberInfoItemMapper.selectIdByUpSequence(rSequence);
        //改变序号
        MemberInfoItem rMemberInfoItem = new MemberInfoItem();
        rMemberInfoItem.setId(id);
        rMemberInfoItem.setSequence(memberInfoItemVo.getSequence());
        int i = memberInfoItemMapper.updateById(rMemberInfoItem);

        MemberInfoItem dMemberInfoItem = new MemberInfoItem();
        dMemberInfoItem.setId(memberInfoItemVo.getId());
        dMemberInfoItem.setSequence(rSequence);
        int j = memberInfoItemMapper.updateById(dMemberInfoItem);

        if (i==ONE&&j==ONE){
            return true;
        }
        return false;
    }

    /**
     * @Description:会员信息项排序-下降
     * @Param: [memberIdSequenceDto]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/16 17:44
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean sequenceDown(String id) {
        //根据id查询对应sequence
        int rSequence=memberInfoItemMapper.selectSequenceById(id);
        //根据rSequence查dId
        MemberInfoItemVo memberInfoItemVo = memberInfoItemMapper.selectIdByDownSequence(rSequence);
        //改变序号
        MemberInfoItem rMemberInfoItem = new MemberInfoItem();
        rMemberInfoItem.setId(id);
        rMemberInfoItem.setSequence(memberInfoItemVo.getSequence());
        int i = memberInfoItemMapper.updateById(rMemberInfoItem);

        MemberInfoItem dMemberInfoItem = new MemberInfoItem();
        dMemberInfoItem.setId(memberInfoItemVo.getId());
        dMemberInfoItem.setSequence(rSequence);
        int j = memberInfoItemMapper.updateById(dMemberInfoItem);

        if (i==ONE&&j==ONE){
            return true;
        }
        return false;
    }

    /**
     * @Description:根据id删除会员信息项
     * @Param: [id]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/16 19:06
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteById(String id) {
        //查询删除条件是否满足(是否启用，是否转为兴趣项，是否是默认类型)
        MemberInfoItemVo memberInfoItemVo=memberInfoItemMapper.selectDeleteConditionsById(id);
        if (memberInfoItemVo.getItemType()!=ITEMTYPE||memberInfoItemVo.getIsInterest()!=ZERO||memberInfoItemVo.getStatus()!=ZERO){
            return false;
        }

        int i = memberInfoItemMapper.deleteById(id);
        Boolean aBoolean=i>ZERO;
        //再通过关联表获取会员信息项值的id集合,并通过会员信息项id删除关联表中信息
        //然后删除会员信息项值的表中的数据，通过valueIds集合
        List<String> valueIds = memberInfoItemValueRelationService.selectByItemId(id);
        //需先判断valueIds集合是否为[]
        if (valueIds!=null&&valueIds.size()>ZERO){
            aBoolean = memberInfoItemValueRelationService.deleteById(id);
            aBoolean = memberInfoItemValueService.delete(valueIds);
        }
        // 关联会员详情 》扩展信息
        //改变coc_member_interest_id_relation表中的status即改变了会员所选的兴趣项的状态直接影响到扩展信息
        //到兴趣项-会员信息项关联表中查询对应的兴趣项id
        String interestId=interestMemberRelationService.selectInterestIdByMemberInfoId(id);
        MemberInterestIdRelationDto memberInterestIdRelationDto = new MemberInterestIdRelationDto();
        memberInterestIdRelationDto.setStatus(ZERO);
        memberInterestIdRelationDto.setInterestId(interestId);
        memberInterestIdRelationDto.setTenantCode(appRuntimeEnv.getTenantId());
        memberInterestIdRelationDto.setUpdatedAt(new Date());
        memberInterestIdRelationDto.setUpdatedBy(accountService.getLoginAccountIdByToken(appRuntimeEnv.getToken()));
        aBoolean = memberInterestIdRelationService.updateByInterestId(memberInterestIdRelationDto);
        return aBoolean;
    }


    /**
     * @Description: 查询类型和关联属性名
     * @Param: [memberInfoId]
     * @returns: java.util.List<com.deepexi.content.domain.vo.InterestToH5Vo>
     * @Author: wujie
     * @Date: 2019/9/26 16:24
     */
    @Override
    public MemberInfoItemDto selectInfoToH5ById(String memberInfoId) {
        MemberInfoItemDto memberInfoItemDto = memberInfoItemMapper.selectInfoToH5ById(memberInfoId);
        return memberInfoItemDto;
    }


    @Override
    public List<MemberInfoItem> findAll(MemberInfoItemDto eo) {
        List<MemberInfoItem> list = memberInfoItemMapper.findList(eo);
        return list;
    }
    @Override
    public MemberInfoItem detail(String pk) {
        return memberInfoItemMapper.selectById(pk);
    }
    @Override
    public Boolean delete(String...pk) {
        int result = memberInfoItemMapper.deleteByIds(pk);
        return result > ZERO;
    }
}
