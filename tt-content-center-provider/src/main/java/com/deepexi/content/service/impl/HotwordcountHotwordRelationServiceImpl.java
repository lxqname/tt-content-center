package com.deepexi.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.deepexi.content.service.HotwordcountHotwordRelationService;
import com.deepexi.content.domain.eo.HotwordcountHotwordRelation;
import com.deepexi.content.domain.dto.HotwordcountHotwordRelationDto;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.HotwordcountHotwordRelationMapper;
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
@Service(version ="${demo.service.version}" )
public class HotwordcountHotwordRelationServiceImpl implements HotwordcountHotwordRelationService {

    private static final int ZERO=0;

    private static final Logger logger = LoggerFactory.getLogger(HotwordcountHotwordRelationServiceImpl.class);

    @Autowired
    private HotwordcountHotwordRelationMapper hotwordcountHotwordRelationMapper;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;


    /**
     * @Description:  增加热词-热词统计id到关系表
     * @Param: [eo]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/23 14:11
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean create(HotwordcountHotwordRelation eo) {
        int i = hotwordcountHotwordRelationMapper.insert(eo);
        return i>ZERO;
    }


    /**
     * @Description: 根据热词id删除热词-热词统计关系表中数据
     * @Param: [hotWordId]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/23 14:26
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteRelation(String hotWordId) {
        //根据热词id查询"热词-热词统计关系表"主键
        List<String> ids = hotwordcountHotwordRelationMapper.selectIdByHotWordId(hotWordId);
        //根据主键ids批量删除
        if (ids.size()>ZERO){
        int i = hotwordcountHotwordRelationMapper.deleteByIds(ids);
        return i>ZERO;
        }
        return true;
    }


    /**
     *
     * @Description: 根据热词id集合删除"热词-热词统计表"相关数据
     * @Param: [hotWordIds]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/25 16:38
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteRelation(List<String> hotWordIds) {
        int i = hotwordcountHotwordRelationMapper.deleteByHotWordIds(hotWordIds);

        return i>ZERO;
    }


    /**
     * @Description:根据热词id获取热词统计id
     * @Param: [hotWordId]
     * @returns: java.lang.String
     * @Author: wujie
     * @Date: 2019/9/25 11:34
     */
    @Override
    public String selectHotWordCountIdByHotWordId(String hotWordId) {
        String hotWordIhotWordCountId = hotwordcountHotwordRelationMapper.selectHotCountIdByHotWordId(hotWordId);
        return hotWordIhotWordCountId;
    }


    /**
     * @Description: 根据热词统计id查询热词id集合
     * @Param: [hotWordCountId]
     * @returns: java.lang.String
     * @Author: wujie
     * @Date: 2019/9/25 11:44
     */
    @Override
    public List<String> selectHotWordIdByHotWordCountId(String hotWordCountId) {
        List<String> hotWordIds=hotwordcountHotwordRelationMapper.selectHotWordIdByHotWordCountId(hotWordCountId);
        return hotWordIds;
    }


    /**
     * @Description: 根据热词统计id删除"热词-热词统计表"相关数据
     * @Param: [hotWordCountId]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/25 14:29
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteRelationByHotWordCountId(String hotWordCountId) {
        int i = hotwordcountHotwordRelationMapper.deleteByHotWordCountId(hotWordCountId);
        return i>ZERO;
    }


    /**
     * @Description: 根据统计id查询所有历史热词
     * @Param: [hotWordCountId]
     * @returns: java.util.List<java.lang.String>
     * @Author: wujie
     * @Date: 2019/9/30 15:36
     */
    @Override
    public List<String> selectHistoryHotWordIds(String hotWordCountId) {
        List<String> hotWordIds = hotwordcountHotwordRelationMapper.selectHistoryIds(hotWordCountId);

        return hotWordIds;
    }


    @Override
    public PageBean findPage(HotwordcountHotwordRelationDto eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<HotwordcountHotwordRelation> list = hotwordcountHotwordRelationMapper.findList(eo);
        return new PageBean<>(list);
    }

    @Override
    public List<HotwordcountHotwordRelation> findAll(HotwordcountHotwordRelationDto eo) {
        List<HotwordcountHotwordRelation> list = hotwordcountHotwordRelationMapper.findList(eo);
        return list;
    }
    @Override
    public HotwordcountHotwordRelation detail(String pk) {
        return hotwordcountHotwordRelationMapper.selectById(pk);
    }

    @Override
    public Boolean create(HotwordcountHotwordRelationDto eo) {
        int result = hotwordcountHotwordRelationMapper.insert(BeanPowerHelper.mapPartOverrider(eo,HotwordcountHotwordRelation.class));
        return result > 0;
    }


    @Override
    public Boolean update(String pk,HotwordcountHotwordRelationDto eo) {
        eo.setId(pk);
        int result = hotwordcountHotwordRelationMapper.updateById(BeanPowerHelper.mapPartOverrider(eo,HotwordcountHotwordRelation.class));
        return result > 0;
    }

    @Override
    public Boolean delete(String...pk) {
        int result = hotwordcountHotwordRelationMapper.deleteByIds(pk);
        return result > 0;
    }


}