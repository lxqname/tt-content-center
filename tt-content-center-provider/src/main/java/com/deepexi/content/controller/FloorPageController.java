package com.deepexi.content.controller;

/**
 * @author hongchungen
 */

import com.deepexi.common.annotation.Token;
import com.deepexi.content.domain.dto.MovePageDto;
import com.deepexi.content.domain.dto.FloorPageCreateDto;
import com.deepexi.content.domain.dto.FloorPageDto;
import com.deepexi.content.domain.dto.FloorPageUpdateDto;
import com.deepexi.content.service.FloorPageService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.constant.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;

@Service
@Path("/api/v1/floorPages")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class FloorPageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FloorPageService floorPageService;

    @GET
    @Path("/")
    public Payload findPage(@BeanParam FloorPageDto eo,
                            @QueryParam("page") @DefaultValue("1") Integer page,
                            @QueryParam("size") @DefaultValue("10") Integer size) {
        return new Payload(floorPageService.findPage(eo, page, size));
    }

    @GET
    @Path("/list")
    public Payload findAll(@BeanParam FloorPageDto eo) {
        return new Payload(floorPageService.getTreeByFloor(null));
    }

    @GET
    @Path("/detail/{id:[a-zA-Z0-9]+}")
    public Payload detail(@PathParam("id") String pk) {
        return new Payload(floorPageService.detail(pk));
    }

    @GET
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload details(@PathParam("id") String pk) {
        return new Payload(floorPageService.details(pk));
    }

    @POST
    @Path("/")
    public Payload create(FloorPageCreateDto eo) {
        return new Payload(floorPageService.create(eo));
    }

    @PUT
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload update(@PathParam("id") String pk, FloorPageUpdateDto eo) {
        return new Payload(floorPageService.update(pk, eo));
    }

    @DELETE
    @Path("/{id:[a-zA-Z0-9,]+}")
    public Payload delete(@PathParam("id") String pk) {
        return new Payload(floorPageService.delete(pk));
    }

    /**
     * 楼层页拖动排序
     * @param eo
     * @return
     */
    @POST
    @Path("/move")
    public Payload move(@Valid @NotNull MovePageDto eo) {
        return new Payload(floorPageService.move(eo));
    }
}
