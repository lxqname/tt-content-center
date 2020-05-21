package com.deepexi.content.controller;

import com.deepexi.content.service.InterestMemberRelationService;
import com.deepexi.content.domain.dto.InterestMemberRelationDto;
import com.deepexi.util.config.Payload;
import com.deepexi.util.constant.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;

@Service
@Path("/api/v1/interestMemberRelations")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class InterestMemberRelationController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private InterestMemberRelationService interestMemberRelationService;

    @GET
    @Path("/")
    public Payload findPage(@BeanParam InterestMemberRelationDto eo,
                            @QueryParam("page") @DefaultValue("1") Integer page,
                            @QueryParam("size") @DefaultValue("10") Integer size) {
        return new Payload(interestMemberRelationService.findPage(eo, page, size));
    }

    @GET
    @Path("/list")
    public Payload findAll(@BeanParam InterestMemberRelationDto eo) {
        return new Payload(interestMemberRelationService.findAll(eo));
    }

    @GET
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload detail(@PathParam("id") String pk) {
        return new Payload(interestMemberRelationService.detail(pk));
    }



    @PUT
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload update(@PathParam("id") String pk, InterestMemberRelationDto eo) {
        return new Payload(interestMemberRelationService.update(pk, eo));
    }

    @DELETE
    @Path("/{id:[a-zA-Z0-9,]+}")
    public Payload delete(@PathParam("id") String pk) {
        return new Payload(interestMemberRelationService.delete(pk.split(",")));
    }

    @DELETE
    @Path("/")
    public Payload delete(String[] pks) {
        return new Payload(interestMemberRelationService.delete(pks));
    }
}
