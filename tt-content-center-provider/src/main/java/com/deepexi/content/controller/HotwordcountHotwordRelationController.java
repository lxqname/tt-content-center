package com.deepexi.content.controller;

import com.deepexi.content.service.HotwordcountHotwordRelationService;
import com.deepexi.content.domain.dto.HotwordcountHotwordRelationDto;
import com.deepexi.util.config.Payload;
import com.deepexi.util.constant.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;

@Service
@Path("/api/v1/hotwordcountHotwordRelations")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class HotwordcountHotwordRelationController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HotwordcountHotwordRelationService hotwordcountHotwordRelationService;

    @GET
    @Path("/")
    public Payload findPage(@BeanParam HotwordcountHotwordRelationDto eo,
                            @QueryParam("page") @DefaultValue("1") Integer page,
                            @QueryParam("size") @DefaultValue("10") Integer size) {
        return new Payload(hotwordcountHotwordRelationService.findPage(eo, page, size));
    }

    @GET
    @Path("/list")
    public Payload findAll(@BeanParam HotwordcountHotwordRelationDto eo) {
        return new Payload(hotwordcountHotwordRelationService.findAll(eo));
    }

    @GET
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload detail(@PathParam("id") String pk) {
        return new Payload(hotwordcountHotwordRelationService.detail(pk));
    }

    @POST
    @Path("/")
    public Payload create(HotwordcountHotwordRelationDto eo) {
        return new Payload(hotwordcountHotwordRelationService.create(eo));
    }

    @PUT
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload update(@PathParam("id") String pk, HotwordcountHotwordRelationDto eo) {
        return new Payload(hotwordcountHotwordRelationService.update(pk, eo));
    }

    @DELETE
    @Path("/{id:[a-zA-Z0-9,]+}")
    public Payload delete(@PathParam("id") String pk) {
        return new Payload(hotwordcountHotwordRelationService.delete(pk.split(",")));
    }

    @DELETE
    @Path("/")
    public Payload delete(String[] pks) {
        return new Payload(hotwordcountHotwordRelationService.delete(pks));
    }
}
