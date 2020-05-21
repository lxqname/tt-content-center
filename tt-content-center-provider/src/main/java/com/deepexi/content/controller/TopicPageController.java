package com.deepexi.content.controller;

import com.deepexi.common.annotation.TenantId;
import com.deepexi.common.annotation.Token;
import com.deepexi.common.enums.StatusEnum;
import com.deepexi.content.domain.dto.*;
import com.deepexi.content.service.TopicPageService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.constant.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;

/**
 * @author hongchunegn
 * @date 2019/10/20 20:39
 */
@Service
@Path("/api/v1/topicPages")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class TopicPageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TopicPageService topicPageService;

    /**
     * 分页
     * @param eo
     * @param page
     * @param size
     * @return
     */
    @GET
    @Path("/")
    @Token(require = false)
    public Payload findPage(@BeanParam TopicQueryDto eo,
                            @QueryParam("page") @DefaultValue("1") Integer page,
                            @QueryParam("size") @DefaultValue("10") Integer size) {
        return new Payload(topicPageService.findPage(eo, page, size));
    }

    /**
     * 创建话题页
     * @param eo
     * @return
     */
    @POST
    @Path("/")
    public Payload topicPageCreate(@Valid @NotNull TopicPageCreateDto eo) {
        return new Payload(topicPageService.topicPageCreate(eo));
    }

    /**
     * 更新
     * @param pk
     * @param eo
     * @return
     */
    @PUT
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload update(@PathParam("id") String pk, TopicPageUpdateDto eo) {
        return new Payload(topicPageService.update(pk, eo));
    }

    /**
     * 删除
     * @param pk
     * @return
     */
    @DELETE
    @Path("/{id:[a-zA-Z0-9,]+}")
    public Payload delete(@PathParam("id") String pk) {
        return new Payload(topicPageService.delete(pk));
    }

    /**
     * 启用
     * @param id
     * @return
     */
    @PUT
    @Path("/enable/{id:[a-zA-Z0-9,]+}")
    public Payload enable(@PathParam("id") String id) {
        return new Payload(topicPageService.updateStatus(id, StatusEnum.ENABLE));
    }

    /**
     * 禁用
     * @param id
     * @return
     */
    @PUT
    @Path("/disable/{id:[a-zA-Z0-9,]+}")
    public Payload disable(@PathParam("id") String id) {
        return new Payload(topicPageService.updateStatus(id, StatusEnum.DISABLE));
    }

    /**
     * 话题详情
     * @param pk
     * @return
     */
    @GET
    @Path("/detail/{id:[a-zA-Z0-9]+}")
    @Token(require = false)
    public Payload detail(@PathParam("id") String pk) {
        return new Payload(topicPageService.details(pk));
    }

}
