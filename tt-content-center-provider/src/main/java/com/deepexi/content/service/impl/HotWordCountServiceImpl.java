package com.deepexi.content.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.deepexi.common.enums.ResultEnum;
import com.deepexi.content.domain.dto.DateConditionDto;
import com.deepexi.content.domain.dto.GoodsNameAndIdDto;
import com.deepexi.content.domain.dto.HotWordDto;
import com.deepexi.content.domain.vo.HotWordCountVo;
import com.deepexi.content.domain.vo.HotWordVo;
import com.deepexi.content.service.HotWordCountService;
import com.deepexi.content.domain.eo.HotWordCount;
import com.deepexi.content.domain.dto.HotWordCountDto;
import com.deepexi.content.extension.AppRuntimeEnv;
import com.deepexi.content.mapper.HotWordCountMapper;
import com.deepexi.content.service.HotWordService;
import com.deepexi.content.service.HotwordGoodslablesRelationService;
import com.deepexi.content.service.HotwordcountHotwordRelationService;
import com.deepexi.content.utils.BeanCopyUtils;
import com.deepexi.content.utils.GetYesterdayOrToday;
import com.deepexi.product.domain.dto.ItemTagDto;
import com.deepexi.product.domain.dto.ItemTagQueryDto;
import com.deepexi.product.service.ItemTagService;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.deepexi.util.BeanPowerHelper;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Component
@Service(version = "${demo.service.version}")
public class HotWordCountServiceImpl implements HotWordCountService {

    private static final String EMPTY="";

    /**
     * 表示当前热词集正在展示中
     */
    private static final int ON_SHOW=1;

    /**
     * 表示热词集未展示
     */
    private static final int NOT_ON_SHOW=0;

    private static final String CREATEB_BY="system";

    private static final String TENANT_CODE="tt";

    private static final int ZERO=0;

    private static final int ONE=1;

    private static final String TOP="TOP10-";

    private static final String SPACE="  ";

    private static final String ISO="ISO-8859-1";

    private static final String UTF8="utf-8";

    private static final Logger logger = LoggerFactory.getLogger(HotWordCountServiceImpl.class);

    @Autowired
    private HotWordCountMapper hotWordCountMapper;

   /* @Autowired
    private GoodsLabelMapper goodsLabelMapper;*/

    @Autowired
    private HotwordcountHotwordRelationService hotwordcountHotwordRelationService;

    @Autowired
    private HotwordGoodslablesRelationService hotwordGoodslablesRelationService;

    @Autowired
    private HotWordService hotWordService;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Reference(version = "${demo.service.version}", check = false)
    private ItemTagService itemTagService;

    /**
     * @Description:  *****定时任务：每天凌晨统计前天新增当日热词统计栏,并统计前天热词的总点击次数*****  新增时默认将前一天热词作为推荐热词
     * @Param: [eo]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/21 14:17
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean task() {
        Boolean aBoolean=true;
        //创建热词统计对象
        HotWordCountDto hotWordCountDto = new HotWordCountDto();
        hotWordCountDto.setOnShow(ON_SHOW);
        hotWordCountDto.setShowDate(new Date());
        hotWordCountDto.setCreatedBy(CREATEB_BY);
        hotWordCountDto.setTenantCode(TENANT_CODE);
        HotWordCount hotWordCount = BeanPowerHelper.mapPartOverrider(hotWordCountDto, HotWordCount.class);
        int result = hotWordCountMapper.insert(hotWordCount);
        //获取新增的热词统计id
        String hotWordCountId = hotWordCount.getId();
        //获取昨天日期
        String yesterday = GetYesterdayOrToday.getYesterday();
        //根据yesterday获取昨天的热词统计id
        String yHotWordCountId = hotWordCountMapper.selectIdByShowDate(yesterday);
        //改变展示昨天热词统计的展示状态
        HotWordCount hotWordCountStatus = new HotWordCount();
        hotWordCountStatus.setId(yHotWordCountId);
        hotWordCountStatus.setOnShow(NOT_ON_SHOW);
        aBoolean = hotWordCountMapper.updateById(hotWordCountStatus)>ZERO;
        //***查询到相关热词信息并封装存入相关表中***
        //1.从"热词-热词统计表"中获取热词id集合
        List<String> yHotWordIds = hotwordcountHotwordRelationService.selectHotWordIdByHotWordCountId(yHotWordCountId);
        if (yHotWordIds.size()>ZERO){
            //1-1昨日热词搜索次数求和
            int totalSearchNum = hotWordService.sumSearchNum(yHotWordIds);
            //1-2改变昨天热词统计表热词集的搜索次数
            HotWordCount yHotWordCount = new HotWordCount();
            yHotWordCount.setId(yHotWordCountId);
            yHotWordCount.setSearchNum(totalSearchNum);
            aBoolean = hotWordCountMapper.updateById(yHotWordCount)>ZERO;
            //2.查询昨天热词信息
            List<HotWordDto> yHotWordDtos = hotWordService.selectHotWordByYhotWordIds(yHotWordIds);
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
                hotWordDto.setId(EMPTY);
                //5.设置热词来源
                hotWordDto.setSource(yesterday+SPACE+TOP+top);
                top++;
            }
             aBoolean = hotWordService.create(yHotWordDtos,hotWordCountId);
        }
        return aBoolean;
    }


    /**
     * @Description: 加入热词集到热词统计表
     * @Param: [id, hotWordValuesStr]
     * @returns: java.lang.Boolean
     * @Author: wujie
     * @Date: 2019/9/21 17:08
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateHotWords(String id, String hotWordValuesStr) {
        HotWordCount hotWordCount = new HotWordCount();
        hotWordCount.setId(id);
        hotWordCount.setHotWords(hotWordValuesStr);
        int i = hotWordCountMapper.updateById(hotWordCount);

        return i>ZERO;
    }


    /**
     * @Description: 根据展示日期查询主键id
     * @Param: [yesterday]
     * @returns: java.lang.String
     * @Author: wujie
     * @Date: 2019/9/25 15:03
     */
    @Override
    public String selectIdByShowDate(String date) {
        String id = hotWordCountMapper.selectIdByShowDate(date);
        return id;
    }


    /**
     * @Description: 含条件分页查询热词统计表
     * @Param: [dto, page, size]
     * @returns: com.deepexi.util.pageHelper.PageBean
     * @Author: wujie
     * @Date: 2019/9/26 10:29
     */
    @Override
    public PageBean findPage(DateConditionDto dto, Integer page, Integer size) {
        //前端联调用增加本日热词实时统计代码
        String  today= GetYesterdayOrToday.transForm(new Date());
        String todayHotWordCountId = hotWordCountMapper.selectIdByShowDate(today);
        List<String> todayHotWordIds = hotwordcountHotwordRelationService.selectHotWordIdByHotWordCountId(todayHotWordCountId);
        if (todayHotWordIds.size()>ZERO) {
            //热词搜索次数求和
            int totalSearchNum = hotWordService.sumSearchNum(todayHotWordIds);
            //改变热词统计表热词集的搜索次数
            HotWordCount hotWordCount = new HotWordCount();
            hotWordCount.setId(todayHotWordCountId);
            hotWordCount.setSearchNum(totalSearchNum);
            hotWordCountMapper.updateById(hotWordCount);
        }
        //****************后期删除上面代码**************
        //日期格式转换
        long startTime = dto.getStartTime();
        long endTime = dto.getEndTime();
        if (startTime!=ZERO&&endTime!=ZERO){
        dto.setStart(GetYesterdayOrToday.transForm(new Date(startTime)));
        dto.setEnd(GetYesterdayOrToday.transForm(new Date(endTime)));
        }
        PageHelper.startPage(page, size);
        List<HotWordCountVo> hotWordCountVos = hotWordCountMapper.findPage(dto);

        //转json字符串为集合
        for (HotWordCountVo hotWordCountVo:hotWordCountVos){
            //日期格式转换
            String dateStr = GetYesterdayOrToday.transForm(hotWordCountVo.getShowDate());
            hotWordCountVo.setOnShowDate(dateStr);
            hotWordCountVo.setShowDate(null);
            String hotWordsJsonStr = hotWordCountVo.getHotWords();
            //json字符串转码
            if (hotWordsJsonStr!=null){
                try {
                    hotWordsJsonStr = new String(hotWordsJsonStr.getBytes(ISO),UTF8);
                } catch (UnsupportedEncodingException e) {
                    throw new ApplicationException(ResultEnum.UNKNOWN_ERROR);
                }
                List<String> hotWordsList = JSONArray.parseArray(hotWordsJsonStr, String.class);
                hotWordCountVo.setHotWords(null);
                hotWordCountVo.setHotwordList(hotWordsList);
            }

        }
        return new PageBean<>(hotWordCountVos);
    }


    /**
     * @Description: 根据热词统计id查询当日热词详情
     * @Param: [pk]
     * @returns: com.deepexi.content.domain.vo.HotWordCountVo
     * @Author: wujie
     * @Date: 2019/9/26 10:59
     */
    @Override
    public List<HotWordVo> detail(String pk) {

        List<HotWordVo> hotWordVos = hotWordService.displayHotWords(pk);
        return hotWordVos;
    }


    @Override
    public List<HotWordCount> findAll(HotWordCountDto eo) {
        List<HotWordCount> list = hotWordCountMapper.findList(eo);
        return list;
    }


    @Override
    public Boolean update(String pk,HotWordCountDto eo) {
        eo.setId(pk);
        int result = hotWordCountMapper.updateById(BeanPowerHelper.mapPartOverrider(eo,HotWordCount.class));
        return result > 0;
    }


    @Override
    public Boolean delete(String...pk) {
        int result = hotWordCountMapper.deleteByIds(pk);
        return result > 0;
    }



}