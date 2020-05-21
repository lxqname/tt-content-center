package com.deepexi.content.controller;

import com.deepexi.common.annotation.Token;
import com.deepexi.content.service.HotWordService;
import com.deepexi.content.domain.dto.HotWordDto;
import com.deepexi.util.config.Payload;
import com.deepexi.util.constant.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import java.util.List;

@Service
@Path("/api/v1/hotWords")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class HotWordController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HotWordService hotWordService;

    /**
     * @Description: 增加热词(批量增加)
     * @Param: [eo]
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/9/21 14:34
     */
    @POST
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload create(List<HotWordDto> eo,@PathParam("id") String hotWordCountId) {

        return new Payload(hotWordService.create(eo,hotWordCountId));
    }




    /**
     * @Description: 根据热词统计id回显热词到弹窗页面
     * @Param: [hotWordCountId]
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/9/25 17:28
     */
    @GET
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload displayHotWords(@PathParam("id") String hotWordCountId) {

        return new Payload(hotWordService.displayHotWords(hotWordCountId));
    }


    /**
     * @Description:展示所有商品标签
     * @Param: []
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/9/26 11:10
     */
    @GET
    @Path("/goodsLabel")
    public Payload showGoodsLabel() {

        return new Payload(hotWordService.showGoodsLabel());
    }


    /**
     * @Description: 还原推荐热词 (根据统计id查询查询当日的推荐热词即来源为2019-xx-xx ...形式的)
     * @Param: []
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/9/30 15:19
     */
    @GET
    @Path("/historyWords/{id:[a-zA-Z0-9]+}")
    public Payload reduction(@PathParam("id") String hotWordCountId) {


        return new Payload(hotWordService.reduction(hotWordCountId));
    }



    /**
     * @Description: 展示今日热词到H5端
     * @Param: []
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/9/26 14:30
     */
    @GET
    @Path("/list")
    @Token(require = false)
    public Payload findAll() {

        return new Payload(hotWordService.showHotWordToH5());
    }



    /**
     * @Description: 根据热词id更新热词点击次数
     * @Param: [pk]
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/9/26 14:20
     */
    @PUT
    @Path("/{id:[a-zA-Z0-9]+}")
    @Token(require = false)
    public Payload update(@PathParam("id") String pk) {

        return new Payload(hotWordService.updateSearchNum(pk));
    }

}