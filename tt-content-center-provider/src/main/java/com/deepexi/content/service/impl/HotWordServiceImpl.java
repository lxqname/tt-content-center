package com.deepexi.content.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.deepexi.content.domain.dto.GoodsNameAndIdDto;
import com.deepexi.content.domain.eo.HotWordCount;
import com.deepexi.content.domain.eo.HotwordcountHotwordRelation;
import com.deepexi.content.domain.vo.HotWordVo;
import com.deepexi.content.service.HotWordCountService;
import com.deepexi.content.service.HotWordService;
import com.deepexi.content.domain.eo.HotWord;
import com.deepexi.content.domain.dto.HotWordDto;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.HotWordMapper;
import com.deepexi.content.service.HotwordGoodslablesRelationService;
import com.deepexi.content.service.HotwordcountHotwordRelationService;
import com.deepexi.content.utils.BeanCopyUtils;
import com.deepexi.content.utils.GetYesterdayOrToday;
import com.deepexi.product.domain.dto.ItemTagDto;
import com.deepexi.product.domain.dto.ItemTagQueryDto;
import com.deepexi.product.service.ItemTagService;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.deepexi.util.BeanPowerHelper;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Component
@Service(version = "${demo.service.version}")
public class HotWordServiceImpl implements HotWordService {

    //private static final String DATE_PATTERN="yyyy-MM-dd";

    private static final int ONE=1;

    private static final String TOP="TOP10-";

    private static final String SPACE="  ";

    private static final int ZERO=0;

    private static final String EMPTY="";

    private static final Logger logger = LoggerFactory.getLogger(HotWordServiceImpl.class);

    @Autowired
    private HotWordMapper hotWordMapper;


    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private HotwordGoodslablesRelationService hotwordGoodslablesRelationService;

    @Autowired
    private HotWordCountService hotWordCountService;

    @Autowired
    private HotwordcountHotwordRelationService hotwordcountHotwordRelationService;

    @Autowired
    private HotWordService hotWordService;

    @Reference(version = "${demo.service.version}", check = false)
    private ItemTagService itemTagService;



    /**
     * @Description: 增加热词(并且同步增加热词id和商品标签id到关联表中)
     * @Param: [eo]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/21 14:47
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean create(List<HotWordDto> eo,String hotWordCountId) {
        Boolean aBoolean=true;
        //首先根据hotWordCountId获取目前所有的热词id
        List<String> hotWordIds = hotwordcountHotwordRelationService.selectHotWordIdByHotWordCountId(hotWordCountId);
        //创建热词名称的集合(作为统计表的热词集)
        List<String> hotWordValues = new ArrayList<>();
        for (HotWordDto hotWordDto:eo){
            String hotWordId = hotWordDto.getId();
            //增加之前必须判断此热词是否已经存在(根据id是否为""判断)
            //如果不为""说明此热词已经存在与热词表中，应根据id先delete数据(关联表中的数据也要delete),然后重新增加
            if (!EMPTY.equals(hotWordId)){
                //把已存在但又重新存储的热词id从hotWordIds中去除
                hotWordIds.remove(hotWordId);
                //先查询该热词的搜索次数
                int searchNum = hotWordMapper.selectSearchNumById(hotWordId);
                //设置搜索次数
                hotWordDto.setSearchNum(searchNum);
                //然后删热词表数据
                hotWordMapper.deleteById(hotWordId);
                //再删热词和商品标签关系表
                hotwordGoodslablesRelationService.deleteRelation(hotWordId);
                //最后删热词和热词统计表
                hotwordcountHotwordRelationService.deleteRelation(hotWordId);
            }
            //清空id让其自生成
            hotWordDto.setId(null);
            //增加hotWord到热词表
            HotWord hotWord = BeanPowerHelper.mapPartOverrider(hotWordDto, HotWord.class);
            aBoolean=hotWordMapper.insert(hotWord)>ZERO;
            //获取生成的uuid
            hotWordId = hotWord.getId();
            //创建热词-热词统计关联对象，并存入集合
            HotwordcountHotwordRelation hotwordcountHotwordRelation = new HotwordcountHotwordRelation();
            hotwordcountHotwordRelation.setHotWordId(hotWordId);
            hotwordcountHotwordRelation.setHotWordCountId(hotWordCountId);
            //存入关联对象到关系表中
            aBoolean = hotwordcountHotwordRelationService.create(hotwordcountHotwordRelation);
            //获取商品标签id的集合
            List<GoodsNameAndIdDto> goodsNameAndId = hotWordDto.getGoodsNameAndId();
            List<String> goodLabelIds = new ArrayList<>();
            for (GoodsNameAndIdDto goodsNameAndIdDto:goodsNameAndId){
                goodLabelIds.add(goodsNameAndIdDto.getId());
            }
            if (goodLabelIds.size()>ZERO){
                //同步增加热词id和商品标签id到关联表中
                aBoolean=hotwordGoodslablesRelationService.create(hotWordId,goodLabelIds);
            }
            //把热词名加入集合
            hotWordValues.add(hotWordDto.getName());
        }
        //最后需将热词值集合打入热词统计表
        //转集合为json字符串格式
        String hotWordValuesStr = JSONArray.toJSONString(hotWordValues);
        aBoolean = hotWordCountService.updateHotWords(hotWordCountId, hotWordValuesStr);
        if (hotWordIds.size()>ZERO){
            //删除已存在但未重存的热词
            aBoolean = hotWordMapper.deleteByIds(hotWordIds)>ZERO;
            //再删热词和商品标签关系表
            aBoolean = hotwordGoodslablesRelationService.deleteRelation(hotWordIds);
            //最后删热词和热词统计表
            aBoolean = hotwordcountHotwordRelationService.deleteRelation(hotWordIds);
        }
        return aBoolean;
    }


    /**
     * @Description:根据热词统计id查询热词和标签
     * @Param: [hotWordCountId]
     * @returns: java.util.List<com.deepexi.content.domain.vo.HotWordVo>
     * @Author: wujie
     * @Date: 2019/9/25 17:40
     */
    @Override
    public List<HotWordVo> displayHotWords(String hotWordCountId) {
        //1.查询关联的热词id
        List<String> hotWordIds = hotwordcountHotwordRelationService.selectHotWordIdByHotWordCountId(hotWordCountId);
        if (hotWordIds.size()>ZERO){
        //2.查询热词信息
            List<HotWordVo> hotWordVos = hotWordMapper.selectHotWordsById(hotWordIds);
            for (HotWordVo hotWordVo:hotWordVos){
                //3.查询商品标签id
                List<String> goodsLabelIds = hotwordGoodslablesRelationService.selectGoodsLabelsId(hotWordVo.getId());
                if (goodsLabelIds.size()>ZERO){
                    //获取所有商品集合
                    List<ItemTagDto> allTags = itemTagService.findAll(new ItemTagQueryDto());
                    List<GoodsNameAndIdDto> goodsNameAndIdDtos = new ArrayList<>();
                    for (ItemTagDto itemTagDto:allTags){
                        if (goodsLabelIds.contains(itemTagDto.getId())){
                            GoodsNameAndIdDto goodsNameAndIdDto = new GoodsNameAndIdDto();
                            BeanCopyUtils.copy(itemTagDto,goodsNameAndIdDto);
                            goodsNameAndIdDtos.add(goodsNameAndIdDto);
                        }
                    }
                    hotWordVo.setGoodsNameAndId(goodsNameAndIdDtos);
                }
            }
        return hotWordVos;

        }
        //尚未关联任何热词
        return null;
    }


    /**
     * @Description: 查询前一天hotword信息
     * @Param: [yHotWordIds]
     * @returns: java.util.List<com.deepexi.content.domain.dto.HotWordDto>
     * @Author: wujie
     * @Date: 2019/9/25 19:39
     */
    @Override
    public List<HotWordDto> selectHotWordByYhotWordIds(List<String> yHotWordIds) {
        List<HotWordDto> yHotWordDtos = hotWordMapper.selectYhotWordsById(yHotWordIds);

        return yHotWordDtos;
    }


    /**
     * @Description: *******查询商品标签信息 *******
     * @Param: []
     * @returns: java.util.List<com.deepexi.content.domain.dto.GoodsNameAndIdDto>
     * @Author: wujie
     * @Date: 2019/9/26 11:05
     */
    @Override
    public List<GoodsNameAndIdDto> showGoodsLabel() {
        //******远程调用接口获取标签信息******
        List<ItemTagDto> allTags = itemTagService.findAll(new ItemTagQueryDto());
        List<GoodsNameAndIdDto> goodsNameAndIdDtos = new ArrayList<>();
        for (ItemTagDto itemTagDto:allTags){
            GoodsNameAndIdDto goodsNameAndIdDto = new GoodsNameAndIdDto();
            BeanCopyUtils.copy(itemTagDto,goodsNameAndIdDto);
            goodsNameAndIdDtos.add(goodsNameAndIdDto);
        }
        return goodsNameAndIdDtos;
    }


    /**
     * @Description: 指定id的热词搜索次数求和
     * @Param: [yHotWordIds]
     * @returns: int
     * @Author: wujie
     * @Date: 2019/9/26 11:28
     */
    @Override
    public int sumSearchNum(List<String> yHotWordIds) {
        int sum = hotWordMapper.sumSearchNum(yHotWordIds);
        return sum;
    }


    /**
     * @Description: 根据热词id更新热词点击次数
     * @Param: [hotWordId]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/26 14:22
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateSearchNum(String hotWordId) {
        int i = hotWordMapper.updateSearchNum(hotWordId);
        return i>ZERO;
    }


    /**
     * @Description: 展示今日热词到H5端
     * @Param: []
     * @returns: java.util.List<com.deepexi.content.domain.vo.HotWordVo>
     * @Author: wujie
     * @Date: 2019/9/26 14:37
     */
    @Override
    public List<HotWordVo> showHotWordToH5() {
        //查询今日统计热词id
        String hotWordCountId = hotWordCountService.selectIdByShowDate(GetYesterdayOrToday.getToday());
        //查询热词id
        List<String> hotWordIds = hotwordcountHotwordRelationService.selectHotWordIdByHotWordCountId(hotWordCountId);
        if (hotWordIds.size()>ZERO){
            List<HotWordVo> hotWordVos = hotWordMapper.selectHotWordsById(hotWordIds);
            //查询热词关联标签详情
            for (HotWordVo hotWordVo:hotWordVos){
                List<String> goodsLabelIds = hotwordGoodslablesRelationService.selectGoodsLabelsId(hotWordVo.getId());
                if (goodsLabelIds.size()>ZERO){
                    //获取所有商品集合
                    List<ItemTagDto> allTags = itemTagService.findAll(new ItemTagQueryDto());
                    List<GoodsNameAndIdDto> goodsNameAndIdDtos = new ArrayList<>();
                    for (ItemTagDto itemTagDto:allTags){
                        if (goodsLabelIds.contains(itemTagDto.getId())){
                            GoodsNameAndIdDto goodsNameAndIdDto = new GoodsNameAndIdDto();
                            BeanCopyUtils.copy(itemTagDto,goodsNameAndIdDto);
                            goodsNameAndIdDtos.add(goodsNameAndIdDto);
                        }
                    }
                    hotWordVo.setGoodsNameAndId(goodsNameAndIdDtos);
                }
            }

            return hotWordVos;
        }
        return null;
    }


    /**
     * @Description: 查询当日推荐热词即热词还原
     * @Param: [hotWordCountId]
     * @returns: java.util.List<com.deepexi.content.domain.vo.HotWordVo>
     * @Author: wujie
     * @Date: 2019/9/30 15:31
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<HotWordVo> reduction(String hotWordCountId) {
        //获取昨天日期
        String yesterday = GetYesterdayOrToday.getYesterday();
        //根据yesterday获取昨天的热词统计id
        String yHotWordCountId = hotWordCountService.selectIdByShowDate(yesterday);
        //***查询到相关热词信息并封装存入相关表中***
        //1.从"热词-热词统计表"中获取热词id集合
        List<String> yHotWordIds = hotwordcountHotwordRelationService.selectHotWordIdByHotWordCountId(yHotWordCountId);
        if (yHotWordIds.size()>ZERO){
            //查询历史热词信息
            List<HotWordDto> yHotWordDtos = hotWordMapper.selectYhotWordsById(yHotWordIds);
            //定义top-10初始值
            int top=ONE;
            for (HotWordDto hotWordDto:yHotWordDtos){
                //3.查询商品标签id
                List<String> goodsLabelsIds = hotwordGoodslablesRelationService.selectGoodsLabelsId(hotWordDto.getId());
                if (goodsLabelsIds.size()>ZERO){
                    //******调用远程接口******
                    List<ItemTagDto> allTags = itemTagService.findAll(new ItemTagQueryDto());
                    List<GoodsNameAndIdDto> goodsNameAndIdDtos = new ArrayList<>();
                    for (ItemTagDto itemTagDto:allTags){
                        if (goodsLabelsIds.contains(itemTagDto.getId())){
                            GoodsNameAndIdDto goodsNameAndIdDto = new GoodsNameAndIdDto();
                            BeanCopyUtils.copy(itemTagDto,goodsNameAndIdDto);
                            goodsNameAndIdDtos.add(goodsNameAndIdDto);
                        }
                    }
                    hotWordDto.setGoodsNameAndId(goodsNameAndIdDtos);
                }
                hotWordDto.setId(null);
                //5.设置热词来源
                hotWordDto.setSource(yesterday+SPACE+TOP+top);
                top++;
            }
            //集合对象转换
            List<HotWordVo> hotWordVos = new ArrayList<HotWordVo>();
            for(HotWordDto hotWordDto:yHotWordDtos){
                HotWordVo hotWordVo = new HotWordVo();
                BeanCopyUtils.copy(hotWordDto,hotWordVo);
                hotWordVo.setId(EMPTY);
                hotWordVos.add(hotWordVo);
            }
            //设置id为""
            for (HotWordDto hotWordDto:yHotWordDtos){
                hotWordDto.setId(EMPTY);
            }
            //推荐热词入库
            hotWordService.create(yHotWordDtos,hotWordCountId);
            return hotWordVos;
        }else{
            //昨日无热词，还原至初始状态
            List<String> todayHotwordIds = hotwordcountHotwordRelationService.selectHotWordIdByHotWordCountId(hotWordCountId);
            hotwordcountHotwordRelationService.deleteRelationByHotWordCountId(hotWordCountId);
            if (todayHotwordIds.size()>ZERO){
                hotWordMapper.deleteByIds(todayHotwordIds);
                hotwordGoodslablesRelationService.deleteRelation(todayHotwordIds);
                List<String> values = new ArrayList<>();
                String hotWordValuesStr = JSONArray.toJSONString(values);
                hotWordCountService.updateHotWords(hotWordCountId, hotWordValuesStr);
            }
        //返回null
        return null;
        }
    }


    @Override
    public PageBean findPage(HotWordDto eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<HotWord> list = hotWordMapper.findList(eo);
        return new PageBean<>(list);
    }

    @Override
    public List<HotWord> findAll(HotWordDto eo) {
        List<HotWord> list = hotWordMapper.findList(eo);
        return list;
    }
    @Override
    public HotWord detail(String pk) {
        return hotWordMapper.selectById(pk);
    }


    @Override
    public Boolean update(String pk,HotWordDto eo) {
        eo.setId(pk);
        int result = hotWordMapper.updateById(BeanPowerHelper.mapPartOverrider(eo,HotWord.class));
        return result > 0;
    }



}