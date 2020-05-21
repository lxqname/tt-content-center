package com.deepexi.content.controller;

import com.deepexi.common.annotation.TenantId;
import com.deepexi.common.annotation.Token;
import com.deepexi.content.service.BannerManageService;
import com.deepexi.content.domain.dto.BannerManageDto;
import com.deepexi.util.config.Payload;
import com.deepexi.util.constant.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;

/**
 * @author admin
 */
@Service
@Path("/api/v1/bannerManages")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class BannerManageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BannerManageService bannerManageService;

    @GET
    @Path("/")
    public Payload findPage(@BeanParam BannerManageDto eo,
                            @QueryParam("page") @DefaultValue("1") Integer page,
                            @QueryParam("size") @DefaultValue("10") Integer size) {
        return new Payload(bannerManageService.findPage(eo, page, size));
    }

    @GET
    @Path("/list")
    public Payload findAll(@BeanParam BannerManageDto eo) {
        return new Payload(bannerManageService.findAll(eo));
    }

    @GET
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload detail(@PathParam("id") String pk) {
        return new Payload(bannerManageService.detail(pk));
    }

    @POST
    @Path("/")
    public Payload create(BannerManageDto eo) {
        return new Payload(bannerManageService.create(eo));
    }

    @PUT
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload update(@PathParam("id") String pk, BannerManageDto eo) {
        return new Payload(bannerManageService.update(pk, eo));
    }

    @DELETE
    @Path("/{id:[a-zA-Z0-9,]+}")
    public Payload delete(@PathParam("id") String pk) {
        return new Payload(bannerManageService.delete(pk.split(",")));
    }

    @DELETE
    @Path("/")
    public Payload delete(String[] pks) {
        return new Payload(bannerManageService.delete(pks));
    }

    @GET
    @Token(require = false)
    @TenantId(require = false)
    @Path("/getBanner")
    public Payload getBannerManage() {
        return new Payload(bannerManageService.getBanner());
    }
}
