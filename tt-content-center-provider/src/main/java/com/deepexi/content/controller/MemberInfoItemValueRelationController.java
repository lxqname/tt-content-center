package com.deepexi.content.controller;

import com.deepexi.content.service.MemberInfoItemValueRelationService;
import com.deepexi.content.domain.dto.MemberInfoItemValueRelationDto;
import com.deepexi.util.config.Payload;
import com.deepexi.util.constant.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;

@Service
@Path("/api/v1/memberInfoItemValueRelations")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class MemberInfoItemValueRelationController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemberInfoItemValueRelationService memberInfoItemValueRelationService;

    @GET
    @Path("/")
    public Payload findPage(@BeanParam MemberInfoItemValueRelationDto eo,
                            @QueryParam("page") @DefaultValue("1") Integer page,
                            @QueryParam("size") @DefaultValue("10") Integer size) {
        return new Payload(memberInfoItemValueRelationService.findPage(eo, page, size));
    }

    @GET
    @Path("/list")
    public Payload findAll(@BeanParam MemberInfoItemValueRelationDto eo) {
        return new Payload(memberInfoItemValueRelationService.findAll(eo));
    }

    @GET
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload detail(@PathParam("id") String pk) {
        return new Payload(memberInfoItemValueRelationService.detail(pk));
    }

    @POST
    @Path("/")
    public Payload create(MemberInfoItemValueRelationDto eo) {
        return new Payload(memberInfoItemValueRelationService.create(eo));
    }

    @PUT
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload update(@PathParam("id") String pk, MemberInfoItemValueRelationDto eo) {
        return new Payload(memberInfoItemValueRelationService.update(pk, eo));
    }

    @DELETE
    @Path("/{id:[a-zA-Z0-9,]+}")
    public Payload delete(@PathParam("id") String pk) {
        return new Payload(memberInfoItemValueRelationService.delete(pk.split(",")));
    }

    @DELETE
    @Path("/")
    public Payload delete(String[] pks) {
        return new Payload(memberInfoItemValueRelationService.delete(pks));
    }
}
