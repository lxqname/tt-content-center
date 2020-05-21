package com.deepexi.content.controller;

import com.deepexi.common.annotation.Token;
import com.deepexi.common.enums.StatusEnum;
import com.deepexi.content.domain.dto.BannerQueryDto;
import com.deepexi.content.domain.dto.BannerSetCreateDto;
import com.deepexi.content.domain.dto.BannerSetUpdateDto;
import com.deepexi.content.service.BannerSetService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.constant.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;

/**
 * @author admin
 * @date 2019/09/25 17:04
 */
@Service
@Path("/api/v1/bannerSets")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class BannerSetController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BannerSetService bannerSetService;

    /**
     * 获取banner列表，根据bannerManageId获取
     * @param eo
     * @param page
     * @param size
     * @param id
     * @return
     */
    @GET
    @Path("/")
    @Token(require = false)
    public Payload findPage(@BeanParam BannerQueryDto eo,
                            @QueryParam("page") @DefaultValue("1") Integer page,
                            @QueryParam("size") @DefaultValue("10") Integer size,
                            @QueryParam("id") String id) {

        return new Payload(bannerSetService.findPage(eo, page, size, id));
    }

    @GET
    @Path("/{id:[a-zA-Z0-9]+}")
    @Token(require = false)
    public Payload detail(@PathParam("id") String pk) {
        return new Payload(bannerSetService.detail(pk));
    }

    @POST
    @Path("/")
    public Payload create(@Valid @NotNull BannerSetCreateDto eo) {

        return new Payload(bannerSetService.create(eo));
    }

    @PUT
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload update(@PathParam("id") String pk, BannerSetUpdateDto eo) {
        return new Payload(bannerSetService.update(pk, eo));
    }

    @DELETE
    @Path("/{id:[a-zA-Z0-9,]+}")
    public Payload delete(@PathParam("id") String pk) {
        return new Payload(bannerSetService.delete(pk));
    }


    @GET
    @Path("/getBannerDetail/{id:[a-zA-Z0-9]+}")
    @Token(require = false)
    public Payload getBannerSetDetail(@PathParam("id") String id) {
        return new Payload(bannerSetService.getBannerSetDetail(id));
    }

    @PUT
    @Path("/move/up/{id:[a-zA-Z0-9,]+}")
    public Payload moveUp(@PathParam("id") String id) {
        return new Payload(bannerSetService.upBanner(id));
    }

    @PUT
    @Path("/move/down/{id:[a-zA-Z0-9,]+}")
    public Payload moveDown(@PathParam("id") String id) {
        return new Payload(bannerSetService.downBanner(id));
    }

    /**
     * 启用
     * @param id
     * @return
     */
    @PUT
    @Path("/enable/{id:[a-zA-Z0-9,]+}")
    public Payload enable(@PathParam("id") String id) {
        return new Payload(bannerSetService.updateStatus(id, StatusEnum.ENABLE));
    }

    /**
     * 禁用
     * @param id
     * @return
     */
    @PUT
    @Path("/disable/{id:[a-zA-Z0-9,]+}")
    public Payload disable(@PathParam("id") String id) {
        return new Payload(bannerSetService.updateStatus(id, StatusEnum.DISABLE));
    }

    /**
     * H5前端展示
     * @return
     */
    @GET
    @Path("/frontPage")
    @Token(require = false)
    public Payload frontPage() {
        return new Payload(bannerSetService.frontPage());
    }

}
