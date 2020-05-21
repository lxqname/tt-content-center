package com.deepexi.content.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.deepexi.common.constant.Constants;
import com.deepexi.common.domain.vo.LinearTreeVo;
import com.deepexi.common.domain.vo.TreeVo;
import com.deepexi.common.enums.ResultEnum;
import com.deepexi.common.util.TreeUtils;
import com.deepexi.content.domain.dto.*;
import com.deepexi.content.domain.eo.AdvertContentRelation;
import com.deepexi.content.domain.vo.ProjectPageDetailVo;
import com.deepexi.content.enums.AdvertTypeEnum;
import com.deepexi.content.enums.ContentSetTypeEnum;
import com.deepexi.content.enums.MoveAdjacentTypeEnum;
import com.deepexi.content.service.AdvertContentRelationService;
import com.deepexi.content.service.AdvertContentService;
import com.deepexi.content.service.ProjectPageService;
import com.deepexi.content.domain.eo.ProjectPage;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.ProjectPageMapper;
import com.deepexi.content.utils.NumberTypeUtils;
import com.deepexi.util.NumberUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.klock.annotation.Klock;
import org.springframework.boot.autoconfigure.klock.model.LockTimeoutStrategy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author hongchungen
 * @date 2019/09/19 11.23
 */

@Component
@Service(version = "${demo.service.version}")
public class ProjectPageServiceImpl implements ProjectPageService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectPageServiceImpl.class);

    /**
     * 专题页最多数量
     */
    private static final int PROJECT_MAX_NUM = 10;

    /**
     * 专题页父级ID
     */
    private final static String PROJECT_PARENT_ID = "0";

    /**
     * 父级等级
     */
    public static final String PARENT_LABEL = "";


    @Autowired
    private ProjectPageMapper projectPageMapper;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private AdvertContentRelationService advertContentRelationService;

    @Autowired
    private AdvertContentService advertContentService;

    @Override
    public PageBean findPage(ProjectPageDto eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<ProjectPage> list = projectPageMapper.findList(eo, appRuntimeEnv.getTenantId());
        return new PageBean<>(list);
    }

    @Override
    public List<ProjectPage> findAll(ProjectPageDto eo) {
        List<ProjectPage> list = projectPageMapper.findList(eo, appRuntimeEnv.getTenantId());
        return list;
    }

    @Override
    public ProjectPage detail(String pk) {
        ProjectPage projectPage = projectPageMapper.selectById(pk);
        return projectPage;
    }

    @Override
    public ProjectPageDetailVo details(String pk) {
        ProjectPage projectPage = projectPageMapper.selectById(pk);
        if (null == projectPage){
            throw new ApplicationException(ResultEnum.getParameterError("不存在此专题页ID:"+pk));
        }
        ProjectPageDetailVo vo = getProjectPageDetailVo(projectPage);
        return vo;
    }

    /**
     * 查询专题页详情
     * @param projectPage
     * @return
     */
    private ProjectPageDetailVo getProjectPageDetailVo(ProjectPage projectPage) {
        ProjectPageDetailVo vo = new ProjectPageDetailVo();
        BeanUtils.copyProperties(projectPage, vo);
        List<AdvertContentRelation> relationList = advertContentRelationService.getBySetTypeId(projectPage.getId());
        if (null == relationList){
            throw new ApplicationException(ResultEnum.getParameterError("查询不到相关信息"));
        }
        int advertType = relationList.get(NumberTypeUtils.ZERO).getAdvertType();
        vo.setAdvertTypeEnum(AdvertTypeEnum.getEnumByValue(advertType));
        List<AdvertContentJsonDto> jsonDtoList = advertContentService.getAdvertContentJsonDto(AdvertTypeEnum.getEnumByValue(advertType), projectPage.getId());
        vo.setJsonDtoList(jsonDtoList);
        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime=NumberTypeUtils.FIVE)
    public Boolean create(ProjectPageCreateDto eo) {
        //先判断是否可以创建
        verificationCreateProjectPage(eo);
        //设置权重
        ProjectPageCreateDto projectPageCreateDto = weightSet(eo);
        projectPageCreateDto.setpId(PROJECT_PARENT_ID);
        ProjectPage projectPage = new ProjectPage();
        BeanUtils.copyProperties(projectPageCreateDto, projectPage);
        int result = projectPageMapper.insert(projectPage);
        if(result > NumberTypeUtils.ZERO){
            return advertContentRelationService.createAdvertContentRelation(ContentSetTypeEnum.SPECIAL_CONFIG, projectPage.getId(), eo.getJsonDtoList());
        }
        return false;
    }

    /**
     * 创建时判断专题页是否可以创建
     * @param eo
     */
    private void verificationCreateProjectPage(ProjectPageCreateDto eo) {
        ProjectPage projectPage = this.getByName(eo.getName());
        if (null != projectPage){
            throw new ApplicationException(ResultEnum.getParameterError("专题页名称不允许重复"));
        }
        List<ProjectPage> projectPages = this.findAll(new ProjectPageDto());
        if (projectPages.size() >= PROJECT_MAX_NUM){
            throw new ApplicationException(ResultEnum.getParameterError("最多支持10个专题页"));
        }
    }

    /**
     * 设置权重，第一次时设置为1，以后加1
     * @param eo
     * @return
     */
    private ProjectPageCreateDto weightSet(ProjectPageCreateDto eo){
        ProjectPage projectPage = getMaxWeight();
        Integer maxWeight = projectPage.getWeight();
        if (null == maxWeight){
            eo.setWeight(NumberTypeUtils.ONE);
        } else {
            eo.setWeight(maxWeight+NumberTypeUtils.ONE);
        }
        return eo;
    }

    @Override
    public ProjectPage getByName(String name) {
        return projectPageMapper.getByName(name,appRuntimeEnv.getTenantId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime = NumberTypeUtils.FIVE)
    public Boolean update(String pk, ProjectPageUpdateDto eo) {
        eo.setId(pk);
        verificationUpdateProjectPage(eo);
        List<AdvertContentRelation> relationList = advertContentRelationService.getBySetTypeId(pk);
        ProjectPage projectPage = new ProjectPage();
        BeanUtils.copyProperties(eo, projectPage);
        int result = projectPageMapper.updateById(projectPage);
        if (result > NumberTypeUtils.ZERO){
            advertContentRelationService.deleteByAdvertTypeAndSetTypeId(AdvertTypeEnum.getEnumByValue(relationList.get(NumberTypeUtils.ZERO).getAdvertType()), projectPage.getId());
            return advertContentRelationService.createAdvertContentRelation(ContentSetTypeEnum.SPECIAL_CONFIG, projectPage.getId(), eo.getJsonDtoList());
        }
        return false;
    }

    /**
     * 更新时判断专题页是否可以更新
     * @param eo
     */
    private void verificationUpdateProjectPage(ProjectPageUpdateDto eo) {
        ProjectPage projectPage = this.getByName(eo.getName());
        if (null != projectPage && ObjectUtils.notEqual(projectPage.getId(), eo.getId())){
            throw new ApplicationException(ResultEnum.getParameterError("专题页名称不允许重复"));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(String pk) {
        List<AdvertContentRelation> list = advertContentRelationService.getBySetTypeId(pk);
        int result = projectPageMapper.deleteById(pk);
        if(result > NumberTypeUtils.ZERO){
            return advertContentRelationService.deleteByAdvertTypeAndSetTypeId(AdvertTypeEnum.getEnumByValue(list.get(NumberTypeUtils.ZERO).getAdvertType()), pk);
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
    public List<TreeVo> getTree(ProjectPageDto dto, String parentId) {
        List<LinearTreeVo> linearTreeVoList = projectPageMapper.getLinearTree(dto, appRuntimeEnv.getTenantId());
        String parentLabel = PARENT_LABEL;
        ProjectPage projectPage = this.detail(parentId);
        if (projectPage != null) {
            parentLabel = projectPage.getName();
        }
        List<TreeVo> treeVoList = TreeUtils.linearToTree(linearTreeVoList, parentId, parentLabel);
        return treeVoList;
    }

    /**
     * 获取专题页树结构
     * @param dto
     * @return
     */
    @Override
    public List<TreeVo> getTreeByProject(ProjectPageDto dto) {
        if (dto == null) {
            dto = new ProjectPageDto();
        }
        // 获取树形结构的专题架构
        List<TreeVo> treeVoList = this.getTree(dto, Constants.DEFAULT_P_ID);
        if (CollectionUtil.isEmpty(treeVoList)) {
            return null;
        }
        return treeVoList;
    }

    /**
     * 获取最后的权重
     * @return
     */
    @Override
    public ProjectPage getMaxWeight() {
        Integer maxWeight = projectPageMapper.getMaxWeight(appRuntimeEnv.getTenantId());
        ProjectPage projectPage = new ProjectPage();
        projectPage.setWeight(maxWeight);
        return projectPage;
    }

    /**
     * 专题页拖拽
     * @param eo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Klock(waitTime = NumberTypeUtils.THIRTY, leaseTime = NumberTypeUtils.FIVE)
    public Boolean move(MovePageDto eo) {
        ProjectPage projectPage = this.getById(eo.getId());
        ProjectPage locationFloorConfig = this.getById(eo.getLocationId());

        // 后往前移
        if (Objects.equals(MoveAdjacentTypeEnum.AFTER.getState(), eo.getType())) {
            projectPage.setWeight(locationFloorConfig.getWeight());
            projectPageMapper.addWeight(locationFloorConfig.getWeight(),appRuntimeEnv.getTenantId());

        } else if (Objects.equals(MoveAdjacentTypeEnum.BEFORE.getState(), eo.getType())) {
            // 前往后
            projectPage.setWeight(locationFloorConfig.getWeight()+NumberTypeUtils.ONE);
            projectPageMapper.addWeight(projectPage.getWeight(),appRuntimeEnv.getTenantId());
        }

        projectPageMapper.updateById(projectPage);
        return true;
    }

    @Override
    public ProjectPage getById(String id) {
        ProjectPage projectPage = projectPageMapper.selectById(id);
        if (null == projectPage){
            throw new ApplicationException(ResultEnum.getParameterError("专题页ID错误，未找到对应信息:id="+id));
        }
        return projectPage;
    }
}