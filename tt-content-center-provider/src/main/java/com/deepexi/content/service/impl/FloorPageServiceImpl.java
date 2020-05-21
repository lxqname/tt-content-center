package com.deepexi.content.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.deepexi.common.constant.Constants;
import com.deepexi.common.domain.vo.LinearTreeVo;
import com.deepexi.common.domain.vo.TreeVo;
import com.deepexi.common.enums.ResultEnum;
import com.deepexi.common.util.TreeUtils;
import com.deepexi.content.domain.dto.*;
import com.deepexi.content.domain.eo.AdvertContentRelation;
import com.deepexi.content.domain.eo.FloorPage;
import com.deepexi.content.domain.vo.FloorPageDetailVo;
import com.deepexi.content.enums.*;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.FloorPageMapper;
import com.deepexi.content.service.AdvertContentRelationService;
import com.deepexi.content.service.AdvertContentService;
import com.deepexi.content.service.FloorPageService;
import com.deepexi.content.utils.NumberTypeUtils;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.klock.annotation.Klock;
import org.springframework.boot.autoconfigure.klock.annotation.KlockKey;
import org.springframework.boot.autoconfigure.klock.model.LockTimeoutStrategy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author hongchungen
 * @date 2019/09/25 15:51
 */
@Component
@Service(version = "${demo.service.version}")
public class FloorPageServiceImpl implements FloorPageService {

    private static final Logger logger = LoggerFactory.getLogger(FloorPageServiceImpl.class);

    /**
     * 父级等级
     */
    public static final String PARENT_LABEL = "";

    /**
     * 楼层页最大数
     */
    private final static int FLOOR_MAX_NUM = 10;

    /**
     * 楼层页父级ID
     */
    private final static String FLOOR_PARENT_ID = "0";

    @Autowired
    private FloorPageMapper floorPageMapper;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private AdvertContentRelationService advertContentRelationService;

    @Autowired
    private AdvertContentService advertContentService;

    @Override
    public PageBean findPage(FloorPageDto eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<FloorPage> list = floorPageMapper.findList(eo,appRuntimeEnv.getTenantId());
        return new PageBean<>(list);
    }

    @Override
    public List<FloorPage> findAll(FloorPageDto eo) {
        List<FloorPage> list = floorPageMapper.findList(eo,appRuntimeEnv.getTenantId());
        return list;
    }

    @Override
    public FloorPage detail(String pk){
        FloorPage floorPage = floorPageMapper.selectById(pk);
        return floorPage;
    }

    @Override
    public FloorPageDetailVo details(String pk) {
        FloorPage floorPage = floorPageMapper.selectById(pk);
        if (null == floorPage){
            throw new ApplicationException(ResultEnum.getParameterError("不存在此楼层页ID:"+pk));
        }
        //查询详情信息
        FloorPageDetailVo vo = getFloorPageDetailVo(floorPage);
        return vo;
    }

    /**
     * 查询楼层页详情信息
     * @param floorPage
     * @return
     */
    private FloorPageDetailVo getFloorPageDetailVo(FloorPage floorPage) {
        FloorPageDetailVo vo = new FloorPageDetailVo();
        BeanUtils.copyProperties(floorPage, vo);
        List<AdvertContentRelation> relationList = advertContentRelationService.getBySetTypeId(floorPage.getId());
        if (null == relationList){
            throw new ApplicationException(ResultEnum.getParameterError("查询不到相关信息"));
        }
        int advertType = relationList.get(NumberTypeUtils.ZERO).getAdvertType();
        vo.setAdvertTypeEnum(AdvertTypeEnum.getEnumByValue(advertType));
        List<AdvertContentJsonDto> jsonDtoList = advertContentService.getAdvertContentJsonDto(AdvertTypeEnum.getEnumByValue(advertType), floorPage.getId());
        vo.setJsonDtoList(jsonDtoList);
        return vo;
    }

    /**
     * 创建
     * @param eo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime = NumberTypeUtils.FIVE)
    public Boolean create(FloorPageCreateDto eo) {
        verificationFloorConfig(eo);
        FloorPageCreateDto floorPageCreateDto = weightSet(eo);
        floorPageCreateDto.setpId(FLOOR_PARENT_ID);
        FloorPage floorPage = new FloorPage();
        BeanUtils.copyProperties(floorPageCreateDto, floorPage);
        int result = floorPageMapper.insert(floorPage);
        if (result > NumberTypeUtils.ZERO){
            return advertContentRelationService.createAdvertContentRelation(ContentSetTypeEnum.FLOOR_CONFIG, floorPage.getId(), eo.getJsonDtoList());
        }
        return false;
    }

    /**
     * 创建时判断楼层页是否可以创建
     * @param eo
     */
    private void verificationFloorConfig(FloorPageCreateDto eo) {
        FloorPage floorPage = this.getByName(eo.getName());
        if (null != floorPage){
            throw new ApplicationException(ResultEnum.getParameterError("楼层页名称不允许重复"));
        }
        List<FloorPage> floorConfigs = this.findAll(new FloorPageDto());
        if (floorConfigs.size() >= FLOOR_MAX_NUM){
            throw new ApplicationException(ResultEnum.getParameterError("最多支持10个楼层页"));
        }
    }

    /**
     * 楼层页创建时设置权重
     * @param eo
     * @return
     */
    private FloorPageCreateDto weightSet(FloorPageCreateDto eo){
        FloorPage floorPage = getMaxWeight();
        Integer maxWeight = floorPage.getWeight();
        if (null == maxWeight){
            eo.setWeight(NumberTypeUtils.ONE);
        } else {
            eo.setWeight(maxWeight+NumberTypeUtils.ONE);
        }
        return eo;
    }

    /**
     * 根据名称获取楼层页
     * @param name
     * @return
     */
    @Override
    public FloorPage getByName(String name) {
        return floorPageMapper.getByName(name,appRuntimeEnv.getTenantId());
    }

    /**
     * 更新
     * @param pk
     * @param eo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime = NumberTypeUtils.FIVE)
    public Boolean update(@KlockKey String pk, FloorPageUpdateDto eo) {
        eo.setId(pk);
        verificationUpdateFloorConfig(eo);
        FloorPage floorPage = new FloorPage();
        BeanUtils.copyProperties(eo, floorPage);
        int result = floorPageMapper.updateById(floorPage);
        if (result > NumberTypeUtils.ZERO){
            advertContentRelationService.deleteByAdvertTypeAndSetTypeId(AdvertTypeEnum.BE_ITEM, floorPage.getId());
            return advertContentRelationService.createAdvertContentRelation(ContentSetTypeEnum.FLOOR_CONFIG, floorPage.getId(),eo.getJsonDtoList());
        }
        return false;
    }

    /**
     * 更新时判断楼层页是否可以更新
     * @param eo
     */
    private void verificationUpdateFloorConfig(FloorPageUpdateDto eo) {
        FloorPage floorPage = this.getByName(eo.getName());
        if (null != floorPage && ObjectUtils.notEqual(floorPage.getId(), eo.getId())){
            throw new ApplicationException(ResultEnum.getParameterError("楼层页名称不允许重复"));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime = NumberTypeUtils.FIVE)
    public Boolean delete(@KlockKey String pk) {
        int result = floorPageMapper.deleteByIds(pk);
        if (result > NumberTypeUtils.ZERO){
           return advertContentRelationService.deleteByAdvertTypeAndSetTypeId(AdvertTypeEnum.BE_ITEM, pk);
        }
        return false;
    }

    /**
     * 获取树结构
     * @param dto
     * @param parentId
     * @return
     */
    @Override
    public List<TreeVo> getTree(FloorPageDto dto, String parentId) {
        List<LinearTreeVo> linearTreeVoList = floorPageMapper.getLinearTree(dto, appRuntimeEnv.getTenantId());
        String parentLabel = PARENT_LABEL;
        FloorPage floorPage = this.detail(parentId);
        if (floorPage != null) {
            parentLabel = floorPage.getName();
        }
        List<TreeVo> treeVoList = TreeUtils.linearToTree(linearTreeVoList, parentId, parentLabel);
        return treeVoList;
    }

    /**
     * 获取楼层页树结构
     * @param dto
     * @return
     */
    @Override
    public List<TreeVo> getTreeByFloor(FloorPageDto dto) {
        if (dto == null) {
            dto = new FloorPageDto();
        }
        // 获取树形结构的楼层架构
        List<TreeVo> treeVoList = this.getTree(dto, Constants.DEFAULT_P_ID);
        if (CollectionUtil.isEmpty(treeVoList)) {
            return null;
        }
        return treeVoList;
    }

    /**
     * 获取最后一个权重
     * @return
     */
    @Override
    public FloorPage getMaxWeight() {
        Integer maxWeight = floorPageMapper.getMaxWeight(appRuntimeEnv.getTenantId());
        FloorPage floorPage = new FloorPage();
        floorPage.setWeight(maxWeight);
        return floorPage;
    }

    /**
     * 楼层页拖拽
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime = NumberTypeUtils.FIVE)
    public Boolean move(MovePageDto dto) {
        FloorPage floorPage = this.getById(dto.getId());
        FloorPage locationFloorConfig = this.getById(dto.getLocationId());
        // 后往前移
        if (Objects.equals(MoveAdjacentTypeEnum.AFTER.getState(), dto.getType())) {
            floorPage.setWeight(locationFloorConfig.getWeight());
            floorPageMapper.addWeight(locationFloorConfig.getWeight(),appRuntimeEnv.getTenantId());

        } else if (Objects.equals(MoveAdjacentTypeEnum.BEFORE.getState(), dto.getType())) {
            // 前往后
            floorPage.setWeight(locationFloorConfig.getWeight()+NumberTypeUtils.ONE);
            floorPageMapper.addWeight(floorPage.getWeight(),appRuntimeEnv.getTenantId());

        }
        floorPageMapper.updateById(floorPage);
        return true;
    }

    @Override
    public FloorPage getById(String id) {
        FloorPage floorPage = floorPageMapper.selectById(id);
        if (null == floorPage){
            throw new ApplicationException(ResultEnum.getParameterError("楼层页ID错误，未找到对应信息:id="+id));
        }
        return floorPage;
    }
}