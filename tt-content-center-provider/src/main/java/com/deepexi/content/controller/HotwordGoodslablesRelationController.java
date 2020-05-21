package com.deepexi.content.controller;

import com.deepexi.content.service.HotwordGoodslablesRelationService;
import com.deepexi.content.domain.dto.HotwordGoodslablesRelationDto;
import com.deepexi.util.config.Payload;
import com.deepexi.util.constant.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;

@Service
@Path("/api/v1/hotwordGoodslablesRelations")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class HotwordGoodslablesRelationController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HotwordGoodslablesRelationService hotwordGoodslablesRelationService;

    @GET
    @Path("/")
    public Payload findPage(@BeanParam HotwordGoodslablesRelationDto eo,
                            @QueryParam("page") @DefaultValue("1") Integer page,
                            @QueryParam("size") @DefaultValue("10") Integer size) {
        return new Payload(hotwordGoodslablesRelationService.findPage(eo, page, size));
    }

    @GET
    @Path("/list")
    public Payload findAll(@BeanParam HotwordGoodslablesRelationDto eo) {
        return new Payload(hotwordGoodslablesRelationService.findAll(eo));
    }

    @GET
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload detail(@PathParam("id") String pk) {
        return new Payload(hotwordGoodslablesRelationService.detail(pk));
    }

    @PUT
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload update(@PathParam("id") String pk, HotwordGoodslablesRelationDto eo) {
        return new Payload(hotwordGoodslablesRelationService.update(pk, eo));
    }

}
