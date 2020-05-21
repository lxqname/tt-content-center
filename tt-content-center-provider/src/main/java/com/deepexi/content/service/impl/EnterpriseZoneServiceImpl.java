package com.deepexi.content.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.deepexi.business.domain.eo.Enterpr;
import com.deepexi.business.service.EnterprService;
import com.deepexi.common.constant.Constants;
import com.deepexi.common.domain.vo.LinearTreeVo;
import com.deepexi.common.domain.vo.TreeVo;
import com.deepexi.common.enums.ResultEnum;
import com.deepexi.common.util.TreeUtils;
import com.deepexi.content.domain.dto.*;
import com.deepexi.content.domain.eo.EnterpriseZone;
import com.deepexi.content.domain.vo.EnterpriseZoneDetailVo;
import com.deepexi.content.domain.vo.EnterpriseZoneVo;
import com.deepexi.content.enums.MoveAdjacentTypeEnum;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.EnterpriseZoneMapper;
import com.deepexi.content.service.CocRedisService;
import com.deepexi.content.service.EnterpriseZoneService;
import com.deepexi.content.utils.NumberTypeUtils;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.deepexi.util.extension.ApplicationException;
import org.springframework.boot.autoconfigure.klock.annotation.Klock;
import org.springframework.boot.autoconfigure.klock.annotation.KlockKey;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
/**
 * @author lufeng
 */
@Component
@Service(version = "${demo.service.version}")
public class EnterpriseZoneServiceImpl implements EnterpriseZoneService {

    private static final Logger logger = LoggerFactory.getLogger(FloorPageServiceImpl.class);

    /**
     * 父级等级
     */
    public static final String PARENT_LABEL = "";


    /**
     * 企业专区父级ID
     */
    private final static String ENTERPRISE_ZONE_PARENT_ID = "0";

    /**
     * 进入专区记录
     */
    private final String REDIS_ENTERPRISE_COUNT = "coc:enterprise:zone:count";


    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private EnterpriseZoneMapper enterpriseZoneMapper;

    @Autowired
    private CocRedisService cocRedisService;

    @Reference(version = "${demo.service.version}",check = false)
    private EnterprService enterprService;

    @Override
    public PageBean<EnterpriseZoneVo> findPage(EnterpriseZoneDto eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<EnterpriseZoneVo> list = enterpriseZoneMapper.findList(eo,appRuntimeEnv.getTenantId());
        list.stream().forEach(info->{
            Enterpr enterpr = enterprService.getByOrganizationId(info.getOrganizationId());
            if (null == enterpr){
                info.setEnterpriseName(null);
                info.setEnterpriseShortName(null);
                info.setLogo(null);
            }else {
                info.setEnterpriseName(enterpr.getName());
                info.setEnterpriseShortName(enterpr.getShortName());
                info.setLogo(enterpr.getLogo());
            }
        });
        return new PageBean<>(list);
    }

    @Override
    public List<EnterpriseZoneVo> findAll(EnterpriseZoneDto eo) {
        List<EnterpriseZoneVo> list = enterpriseZoneMapper.findList(eo,appRuntimeEnv.getTenantId());
        return list;
    }

    @Override
    public EnterpriseZoneDetailVo externalDetail(String pk) {
        EnterpriseZone enterpriseZone = enterpriseZoneMapper.selectById(pk);
        EnterpriseZoneDetailVo enterpriseZoneDetailVo = new EnterpriseZoneDetailVo();
        BeanUtils.copyProperties(enterpriseZone, enterpriseZoneDetailVo);
        Enterpr enterpr = enterprService.getByOrganizationId(enterpriseZone.getOrganizationId());
        if (null != enterpr){
            enterpriseZoneDetailVo.setEnterpriseName(enterpr.getName());
        }
        return enterpriseZoneDetailVo;
    }


    public EnterpriseZone detail(String pk) {
        EnterpriseZone enterpriseZone = enterpriseZoneMapper.selectById(pk);
        return enterpriseZone;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime = NumberTypeUtils.FIVE)
    public Boolean create(EnterpriseZoneCreateDto eo) {
        verificationEnterpriseZoneConfig(eo);
        EnterpriseZoneCreateDto enterpriseZoneCreateDto = weightSet(eo);
        enterpriseZoneCreateDto.setpId(ENTERPRISE_ZONE_PARENT_ID);
        EnterpriseZone enterpriseZone = new EnterpriseZone();
        BeanUtils.copyProperties(enterpriseZoneCreateDto, enterpriseZone);
        int result = enterpriseZoneMapper.insert(enterpriseZone);
        if (result>NumberTypeUtils.ZERO){
            return true;
        }
        return false;
    }

    /**
     * 创建时判断企业专区是否可以创建
     * @param eo
     */
    private void verificationEnterpriseZoneConfig(EnterpriseZoneCreateDto eo) {
        EnterpriseZone enterpriseZone = this.getByName(eo.getName());
        if (null != enterpriseZone){
            throw new ApplicationException(ResultEnum.getParameterError("企业专区名称不允许重复"));
        }
    }

    /**
     * 企业专区创建时设置权重
     * @param eo
     * @return
     */
    private EnterpriseZoneCreateDto weightSet(EnterpriseZoneCreateDto eo){
        EnterpriseZone enterpriseZone = getMaxWeight();
        Integer maxWeight = enterpriseZone.getWeight();
        if (null == maxWeight){
            eo.setWeight(NumberTypeUtils.ONE);
        } else {
            eo.setWeight(maxWeight+NumberTypeUtils.ONE);
        }
        return eo;
    }

    @Override
    public EnterpriseZone getByName(String name) {
        return enterpriseZoneMapper.getByName(name,appRuntimeEnv.getTenantId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime = NumberTypeUtils.FIVE)
    public Boolean update(@KlockKey String pk, EnterpriseZoneUpdateDto eo) {
        eo.setId(pk);
        verificationUpdateEnterpriseZoneConfig(eo);
        boolean enterpriseId = verificationUpdateEnterpriseId(pk,eo);
        EnterpriseZone enterpriseZone = new EnterpriseZone();
        BeanUtils.copyProperties(eo, enterpriseZone);
        enterpriseZone.setId(pk);
        if (enterpriseId){
            enterpriseZone.setUvCount(NumberTypeUtils.ZERO);
        }
        int result = enterpriseZoneMapper.updateById(enterpriseZone);
        if (result>NumberTypeUtils.ZERO){
            return true;
        }
        return false;
    }

    /**
     * 验证关联组织ID是否改变
     * @return
     */
    private boolean verificationUpdateEnterpriseId(String id,EnterpriseZoneUpdateDto dto){
        EnterpriseZone detail = this.detail(id);
        if (dto.getOrganizationId().equals(detail.getOrganizationId())){
            return false;
        }
        return true;
    }

    /**
     * 更新时判断楼层页是否可以更新
     * @param eo
     */
    private void verificationUpdateEnterpriseZoneConfig(EnterpriseZoneUpdateDto eo) {
        EnterpriseZone enterpriseZone = this.getByName(eo.getName());
        if (null != enterpriseZone && ObjectUtils.notEqual(enterpriseZone.getId(), eo.getId())){
            throw new ApplicationException(ResultEnum.getParameterError("企业专区名称不允许重复"));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime = NumberTypeUtils.FIVE)
    public Boolean delete(@KlockKey String pk) {
        int result = enterpriseZoneMapper.deleteByIds(pk);
        if (result>NumberTypeUtils.ZERO){
            return true;
        }
        return false;
    }

    /**
     * 获取企业专区树结构
     * @param dto
     * @return
     */
    @Override
    public List<TreeVo> getTreeByEnterprise(EnterpriseZoneDto dto) {
        if (dto == null){
            dto = new EnterpriseZoneDto();
        }
        // 获取树形结构的楼层架构
        List<TreeVo> treeVoList = this.getTree(dto, Constants.DEFAULT_P_ID);
        if (CollectionUtil.isEmpty(treeVoList)) {
            return null;
        }
        return treeVoList;
    }

    /**
     * 获取树结构
     * @param dto
     * @param parentId
     * @return
     */
    @Override
    public List<TreeVo> getTree(EnterpriseZoneDto dto, String parentId) {
        List<LinearTreeVo> linearTreeVoList = enterpriseZoneMapper.getLinearTree(dto, appRuntimeEnv.getTenantId());
        String parentLabel = PARENT_LABEL;
        EnterpriseZone enterpriseZone = this.detail(parentId);
        if (null != enterpriseZone){
            parentLabel = enterpriseZone.getName();
        }
        List<TreeVo> treeVoList = TreeUtils.linearToTree(linearTreeVoList, parentId, parentLabel);
        return treeVoList;
    }


    /**
     * 获取最后一个权重
     * @return
     */
    @Override
    public EnterpriseZone getMaxWeight() {
        Integer maxWeight = enterpriseZoneMapper.getMaxWeight(appRuntimeEnv.getTenantId());
        EnterpriseZone enterpriseZone = new EnterpriseZone();
        enterpriseZone.setWeight(maxWeight);
        return enterpriseZone;
    }

    /**
     * 企业专区拖拽
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime = NumberTypeUtils.FIVE)
    public Boolean move(MovePageDto dto) {
        EnterpriseZone enterpriseZone = this.getById(dto.getId());
        EnterpriseZone locationFloorConfig = this.getById(dto.getLocationId());
        // 后往前移
        if (Objects.equals(MoveAdjacentTypeEnum.AFTER.getState(), dto.getType())) {
            enterpriseZone.setWeight(locationFloorConfig.getWeight());
            enterpriseZoneMapper.addWeight(locationFloorConfig.getWeight(),appRuntimeEnv.getTenantId());

        } else if (Objects.equals(MoveAdjacentTypeEnum.BEFORE.getState(), dto.getType())) {
            // 前往后
            enterpriseZone.setWeight(locationFloorConfig.getWeight()+NumberTypeUtils.ONE);
            enterpriseZoneMapper.addWeight(enterpriseZone.getWeight(),appRuntimeEnv.getTenantId());

        }
        enterpriseZoneMapper.updateById(enterpriseZone);
        return true;
    }


    @Override
    public EnterpriseZone getById(String id) {
        EnterpriseZone enterpriseZone = enterpriseZoneMapper.selectById(id);
        if (null == enterpriseZone){
            throw new ApplicationException(ResultEnum.getParameterError("企业专区ID错误，未找到对应信息:id="+id));
        }
        return enterpriseZone;
    }


    @Override
    public Boolean addUvCount(EnterpriseUvCountDto dto) {
        Double score = cocRedisService.getRedisZsetScore(REDIS_ENTERPRISE_COUNT,dto.getId()+":"+dto.getAccountId());
        if (null == score){
            int result = enterpriseZoneMapper.addUvCount(dto.getId());
            if (result>NumberTypeUtils.ZERO){
                Integer seconds = getToDayLastSecond();
                cocRedisService.setRedisZset(REDIS_ENTERPRISE_COUNT,dto.getId()+":"+dto.getAccountId(),seconds);
            }
        }else {
            return false;
        }
        return true;
    }

    private Integer getToDayLastSecond(){
        Calendar curDate = Calendar.getInstance();
        Calendar tommorowDate = new GregorianCalendar(curDate
                .get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate
                .get(Calendar.DATE) + 1, 0, 0, 0);
        return (int)(tommorowDate.getTimeInMillis() - curDate .getTimeInMillis()) / 1000;
    }
}
