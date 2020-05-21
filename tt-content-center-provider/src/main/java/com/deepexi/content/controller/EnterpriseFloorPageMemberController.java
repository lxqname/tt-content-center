package com.deepexi.content.controller;

/**
 * @author lxq
 */

import com.deepexi.common.annotation.Token;
import com.deepexi.content.domain.dto.EnterpriseFloorPageCreateDto;
import com.deepexi.content.domain.dto.EnterpriseFloorPageDto;
import com.deepexi.content.domain.dto.EnterpriseFloorPageUpdateDto;
import com.deepexi.content.domain.dto.MovePageDto;
import com.deepexi.content.service.EnterpriseFloorPageMemberService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.constant.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;

@Service
@Path("/api/v1/enterpriseFloorPageMember")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class EnterpriseFloorPageMemberController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EnterpriseFloorPageMemberService enterpriseFloorPageMemberService;

    @GET
    @Path("/")
    public Payload findPage(@BeanParam EnterpriseFloorPageDto eo,
                            @QueryParam("page") @DefaultValue("1") Integer page,
                            @QueryParam("size") @DefaultValue("10") Integer size) {
        return new Payload(enterpriseFloorPageMemberService.findPage(eo, page, size));
    }

    /**
     * 用户端楼层页列表
     * @param eo
     * @return
     */
    @GET
    @Path("/list")
    @Token(require = false)
    public Payload findAll(@BeanParam EnterpriseFloorPageDto eo) {
        return new Payload(enterpriseFloorPageMemberService.findAll(eo));
    }

    @GET
    @Path("/detail/{id:[a-zA-Z0-9]+}")
    public Payload detail(@PathParam("id") String pk) {
        return new Payload(enterpriseFloorPageMemberService.detail(pk));
    }

    /**
     * 获取楼层里的详情
     * @param pk
     * @return
     */
    @GET
    @Path("/{id:[a-zA-Z0-9]+}")
    @Token(require = false)
    public Payload details(@PathParam("id") String pk) {
        return new Payload(enterpriseFloorPageMemberService.details(pk));
    }

    @POST
    @Path("/")
    public Payload create(EnterpriseFloorPageCreateDto eo) {
        return new Payload(enterpriseFloorPageMemberService.create(eo));
    }

    @PUT
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload update(@PathParam("id") String pk, EnterpriseFloorPageUpdateDto eo) {
        return new Payload(enterpriseFloorPageMemberService.update(pk, eo));
    }

    @DELETE
    @Path("/{id:[a-zA-Z0-9,]+}")
    public Payload delete(@PathParam("id") String pk) {
        return new Payload(enterpriseFloorPageMemberService.delete(pk));
    }

    @POST
    @Path("/move")
    public Payload move(@Valid @NotNull MovePageDto eo) {
        return new Payload(enterpriseFloorPageMemberService.move(eo));
    }
}
