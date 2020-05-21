package com.deepexi.content.controller;

import com.deepexi.content.domain.dto.DateConditionDto;
import com.deepexi.content.service.HotWordCountService;
import com.deepexi.content.domain.dto.HotWordCountDto;
import com.deepexi.util.config.Payload;
import com.deepexi.util.constant.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;

@Service
@Path("/api/v1/hotWordCounts")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class HotWordCountController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HotWordCountService hotWordCountService;


    @GET
    @Path("/list")
    public Payload findAll(@BeanParam HotWordCountDto eo) {
        return new Payload(hotWordCountService.findAll(eo));
    }

    @PUT
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload update(@PathParam("id") String pk, HotWordCountDto eo) {
        return new Payload(hotWordCountService.update(pk, eo));
    }

    @DELETE
    @Path("/{id:[a-zA-Z0-9,]+}")
    public Payload delete(@PathParam("id") String pk) {
        return new Payload(hotWordCountService.delete(pk.split(",")));
    }

    @DELETE
    @Path("/")
    public Payload delete(String[] pks) {
        return new Payload(hotWordCountService.delete(pks));
    }


    /**
     * @Description: 分页查询热词统计信息
     * @Param: [eo, page, size]
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/9/26 10:22
     */
    @GET
    @Path("/")
    public Payload findPage(@BeanParam DateConditionDto dto,
                            @QueryParam("page") @DefaultValue("1") Integer page,
                            @QueryParam("size") @DefaultValue("10") Integer size) {
        return new Payload(hotWordCountService.findPage(dto, page, size));
    }

}
