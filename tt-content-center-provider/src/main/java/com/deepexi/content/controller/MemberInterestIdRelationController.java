package com.deepexi.content.controller;

import com.deepexi.content.domain.dto.MemberInterestIdRelationDto;
import com.deepexi.content.service.MemberInterestIdRelationService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.constant.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;

@Service
@Path("/api/v1/memberInterestIdRelations")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class MemberInterestIdRelationController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemberInterestIdRelationService memberInterestIdRelationService;

    @GET
    @Path("/")
    public Payload findPage(@BeanParam MemberInterestIdRelationDto eo,
                            @QueryParam("page") @DefaultValue("1") Integer page,
                            @QueryParam("size") @DefaultValue("10") Integer size) {
        return new Payload(memberInterestIdRelationService.findPage(eo, page, size));
    }

    @GET
    @Path("/list")
    public Payload findAll(@BeanParam MemberInterestIdRelationDto eo) {
        return new Payload(memberInterestIdRelationService.findAll(eo));
    }

    @GET
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload detail(@PathParam("id") String pk) {
        return new Payload(memberInterestIdRelationService.detail(pk));
    }

    @POST
    @Path("/")
    public Payload create(MemberInterestIdRelationDto eo) {
        return new Payload(memberInterestIdRelationService.create(eo));
    }

    @PUT
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload update(@PathParam("id") String pk, MemberInterestIdRelationDto eo) {
        return new Payload(memberInterestIdRelationService.update(pk, eo));
    }

    @DELETE
    @Path("/{id:[a-zA-Z0-9,]+}")
    public Payload delete(@PathParam("id") String pk) {
        return new Payload(memberInterestIdRelationService.delete(pk.split(",")));
    }

    @DELETE
    @Path("/")
    public Payload delete(String[] pks) {
        return new Payload(memberInterestIdRelationService.delete(pks));
    }
}
