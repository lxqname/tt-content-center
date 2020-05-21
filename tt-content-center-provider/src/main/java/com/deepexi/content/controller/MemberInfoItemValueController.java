package com.deepexi.content.controller;

import com.deepexi.content.service.MemberInfoItemValueService;
import com.deepexi.content.domain.dto.MemberInfoItemValueDto;
import com.deepexi.util.config.Payload;
import com.deepexi.util.constant.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;

@Service
@Path("/api/v1/memberInfoItemValues")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class MemberInfoItemValueController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemberInfoItemValueService memberInfoItemValueService;

    @GET
    @Path("/")
    public Payload findPage(@BeanParam MemberInfoItemValueDto eo,
                            @QueryParam("page") @DefaultValue("1") Integer page,
                            @QueryParam("size") @DefaultValue("10") Integer size) {
        return new Payload(memberInfoItemValueService.findPage(eo, page, size));
    }

    @GET
    @Path("/list")
    public Payload findAll(@BeanParam MemberInfoItemValueDto eo) {
        return new Payload(memberInfoItemValueService.findAll(eo));
    }

    @GET
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload detail(@PathParam("id") String pk) {
        return new Payload(memberInfoItemValueService.detail(pk));
    }

    @POST
    @Path("/")
    public Payload create(MemberInfoItemValueDto eo) {
        return new Payload(memberInfoItemValueService.create(eo));
    }

    @PUT
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload update(@PathParam("id") String pk, MemberInfoItemValueDto eo) {
        return new Payload(memberInfoItemValueService.update(pk, eo));
    }




}
