package com.deepexi.content.controller;


import com.deepexi.common.annotation.Token;
import com.deepexi.content.domain.dto.*;
import com.deepexi.content.service.EnterpriseZoneService;
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
@Path("/api/v1/enterpriseZone")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class EnterpriseZoneController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EnterpriseZoneService enterpriseZoneService;

    @GET
    @Path("/")
    @Token(require = false)
    public Payload findPage(@BeanParam EnterpriseZoneDto eo,
                            @QueryParam("page") @DefaultValue("1") Integer page,
                            @QueryParam("size") @DefaultValue("10") Integer size) {
        return new Payload(enterpriseZoneService.findPage(eo, page, size));
    }

    @GET
    @Path("/list")
    public Payload findAll(@BeanParam EnterpriseZoneDto eo) {
        return new Payload(enterpriseZoneService.getTreeByEnterprise(null));
    }

    @GET
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload detail(@PathParam("id") String pk) {
        return new Payload(enterpriseZoneService.externalDetail(pk));
    }

    @POST
    @Path("/")
    public Payload create(EnterpriseZoneCreateDto eo) {
        return new Payload(enterpriseZoneService.create(eo));
    }

    @PUT
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload update(@PathParam("id") String pk, EnterpriseZoneUpdateDto eo) {
        return new Payload(enterpriseZoneService.update(pk, eo));
    }

    @DELETE
    @Path("/{id:[a-zA-Z0-9,]+}")
    public Payload delete(@PathParam("id") String pk) {
        return new Payload(enterpriseZoneService.delete(pk));
    }

    /**
     * 企业专区拖动排序
     * @param eo
     * @return
     */
    @POST
    @Path("/move")
    public Payload move(@Valid @NotNull MovePageDto eo) {
        return new Payload(enterpriseZoneService.move(eo));
    }


    @POST
    @Path("/addUv")
    @Token(require = false)
    public Payload addUv(@Valid @NotNull EnterpriseUvCountDto eo){
        return new Payload(enterpriseZoneService.addUvCount(eo));
    }
}
