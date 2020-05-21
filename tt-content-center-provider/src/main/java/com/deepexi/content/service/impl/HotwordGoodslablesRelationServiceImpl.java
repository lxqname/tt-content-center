package com.deepexi.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.deepexi.content.domain.dto.HotWordDto;
import com.deepexi.content.service.HotwordGoodslablesRelationService;
import com.deepexi.content.domain.eo.HotwordGoodslablesRelation;
import com.deepexi.content.domain.dto.HotwordGoodslablesRelationDto;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.HotwordGoodslablesRelationMapper;
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
public class HotwordGoodslablesRelationServiceImpl implements HotwordGoodslablesRelationService {

    private static final int ZERO=0;

    private static final Logger logger = LoggerFactory.getLogger(HotwordGoodslablesRelationServiceImpl.class);

    @Autowired
    private HotwordGoodslablesRelationMapper hotwordGoodslablesRelationMapper;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;


    /**
     * @Description:同步增加热词id和商品标签id到关联表中
     * @Param: [hotWordId, goodLabelIds]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/21 15:00
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean create(String hotWordId,List<String> goodLabelIds) {
        Boolean aBoolean=true;
        for (String goodLabelId:goodLabelIds){
            //创建关系表的dto
            HotwordGoodslablesRelationDto hotwordGoodslablesRelationDto = new HotwordGoodslablesRelationDto();
            hotwordGoodslablesRelationDto.setHotWordId(hotWordId);
            hotwordGoodslablesRelationDto.setGoodsLabelsId(goodLabelId);
            aBoolean = hotwordGoodslablesRelationMapper.insert(BeanPowerHelper.mapPartOverrider(hotwordGoodslablesRelationDto,HotwordGoodslablesRelation.class))>ZERO;
        }

        return aBoolean ;
    }


    /**
     * @Description: 根据热词id查询商品标签的id
     * @Param: [hotWordId]
     * @returns: java.util.List<java.lang.String>
     * @Author: wujie
     * @Date: 2019/9/25 18:40
     */
    @Override
    public List<String> selectGoodsLabelsId(String hotWordId) {
        List<String> goodsLabelIds = hotwordGoodslablesRelationMapper.selectGoodsLabelIdsByHotWordId(hotWordId);
        return goodsLabelIds;
    }


    /**
     * @Description:删除"热词-商品标签"关系表中对应数据
     * @Param: [hotWordId]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/21 16:06
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteRelation(String hotWordId) {
        //查询关系表主键id集合
        List<String> ids = hotwordGoodslablesRelationMapper.selectByHotWordId(hotWordId);
        if (ids.size()>ZERO){
            //批量删除
            int i = hotwordGoodslablesRelationMapper.deleteByIds(ids);
            return i>ZERO;
        }

        return true;
    }


    /**
     *
     * @Description: 根据传来的热词id集合删除"热词-标签表"相关数据
     * @Param: [hotWordIds]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/25 14:40
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteRelation(List<String> hotWordIds) {
        int i = hotwordGoodslablesRelationMapper.deleteByHotWordIds(hotWordIds);
        return i>ZERO;

    }


    @Override
    public PageBean findPage(HotwordGoodslablesRelationDto eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<HotwordGoodslablesRelation> list = hotwordGoodslablesRelationMapper.findList(eo);
        return new PageBean<>(list);
    }

    @Override
    public List<HotwordGoodslablesRelation> findAll(HotwordGoodslablesRelationDto eo) {
        List<HotwordGoodslablesRelation> list = hotwordGoodslablesRelationMapper.findList(eo);
        return list;
    }
    @Override
    public HotwordGoodslablesRelation detail(String pk) {
        return hotwordGoodslablesRelationMapper.selectById(pk);
    }

    @Override
    public Boolean update(String pk,HotwordGoodslablesRelationDto eo) {
        eo.setId(pk);
        int result = hotwordGoodslablesRelationMapper.updateById(BeanPowerHelper.mapPartOverrider(eo,HotwordGoodslablesRelation.class));
        return result > 0;
    }

}