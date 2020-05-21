package com.deepexi.content.controller;

import com.deepexi.content.domain.dto.MemberChooseInterestValueDto;
import com.deepexi.content.service.MemberChooseInterestValueService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.constant.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;

@Service
@Path("/api/v1/memberChooseInterestValues")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class MemberChooseInterestValueController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemberChooseInterestValueService memberChooseInterestValueService;

    @GET
    @Path("/")
    public Payload findPage(@BeanParam MemberChooseInterestValueDto eo,
                            @QueryParam("page") @DefaultValue("1") Integer page,
                            @QueryParam("size") @DefaultValue("10") Integer size) {
        return new Payload(memberChooseInterestValueService.findPage(eo, page, size));
    }

    @GET
    @Path("/list")
    public Payload findAll(@BeanParam MemberChooseInterestValueDto eo) {
        return new Payload(memberChooseInterestValueService.findAll(eo));
    }

    @GET
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload detail(@PathParam("id") String pk) {
        return new Payload(memberChooseInterestValueService.detail(pk));
    }

    @POST
    @Path("/")
    public Payload create(MemberChooseInterestValueDto eo) {
        return new Payload(memberChooseInterestValueService.create(eo));
    }

    @PUT
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload update(@PathParam("id") String pk, MemberChooseInterestValueDto eo) {
        return new Payload(memberChooseInterestValueService.update(pk, eo));
    }

    @DELETE
    @Path("/{id:[a-zA-Z0-9,]+}")
    public Payload delete(@PathParam("id") String pk) {
        return new Payload(memberChooseInterestValueService.delete(pk.split(",")));
    }

    @DELETE
    @Path("/")
    public Payload delete(String[] pks) {
        return new Payload(memberChooseInterestValueService.delete(pks));
    }
}
