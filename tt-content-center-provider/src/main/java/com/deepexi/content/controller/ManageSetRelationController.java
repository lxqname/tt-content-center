package com.deepexi.content.controller;

import com.deepexi.content.service.ManageSetRelationService;
import com.deepexi.content.domain.dto.ManageSetRelationDto;
import com.deepexi.util.config.Payload;
import com.deepexi.util.constant.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;

@Service
@Path("/api/v1/manageSetRelations")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class ManageSetRelationController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ManageSetRelationService manageSetRelationService;

    @GET
    @Path("/")
    public Payload findPage(@BeanParam ManageSetRelationDto eo,
                            @QueryParam("page") @DefaultValue("1") Integer page,
                            @QueryParam("size") @DefaultValue("10") Integer size) {
        return new Payload(manageSetRelationService.findPage(eo, page, size));
    }

    @GET
    @Path("/list")
    public Payload findAll(@BeanParam ManageSetRelationDto eo) {
        return new Payload(manageSetRelationService.findAll(eo));
    }

    @GET
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload detail(@PathParam("id") String pk) {
        return new Payload(manageSetRelationService.detail(pk));
    }

    @POST
    @Path("/")
    public Payload create(ManageSetRelationDto eo) {
        return new Payload(manageSetRelationService.create(eo));
    }

    @PUT
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload update(@PathParam("id") String pk, ManageSetRelationDto eo) {
        return new Payload(manageSetRelationService.update(pk, eo));
    }

    @DELETE
    @Path("/{id:[a-zA-Z0-9,]+}")
    public Payload delete(@PathParam("id") String pk) {
        return new Payload(manageSetRelationService.delete(pk.split(",")));
    }

    @DELETE
    @Path("/")
    public Payload delete(String[] pks) {
        return new Payload(manageSetRelationService.delete(pks));
    }
}
