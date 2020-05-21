package com.deepexi.content.controller;

import com.deepexi.content.domain.dto.MovePageDto;
import com.deepexi.content.domain.dto.ProjectPageCreateDto;
import com.deepexi.content.domain.dto.ProjectPageUpdateDto;
import com.deepexi.content.service.ProjectPageService;
import com.deepexi.content.domain.dto.ProjectPageDto;
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
 * @author hongchungen
 */
@Service
@Path("/api/v1/projectPages")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class ProjectPageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProjectPageService projectPageService;

    @GET
    @Path("/")
    public Payload findPage(@BeanParam ProjectPageDto eo,
                            @QueryParam("page") @DefaultValue("1") Integer page,
                            @QueryParam("size") @DefaultValue("10") Integer size) {
        return new Payload(projectPageService.findPage(eo, page, size));
    }

    @GET
    @Path("/")
    public Payload findAll() {
        return new Payload(projectPageService.findAll(null));
    }

    @GET
    @Path("/list")
    public Payload findAll(@BeanParam ProjectPageDto eo) {
        return new Payload(projectPageService.getTreeByProject(null));
    }

    @GET
    @Path("/detail/{id:[a-zA-Z0-9]+}")
    public Payload detail(@PathParam("id") String pk) {
        return new Payload(projectPageService.detail(pk));
    }

    @GET
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload details(@PathParam("id") String pk) {
        return new Payload(projectPageService.details(pk));
    }

    @POST
    @Path("/")
    public Payload create(ProjectPageCreateDto eo) {
        return new Payload(projectPageService.create(eo));
    }

    @PUT
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload update(@PathParam("id") String pk, ProjectPageUpdateDto eo) {
        return new Payload(projectPageService.update(pk, eo));
    }

    @DELETE
    @Path("/{id:[a-zA-Z0-9,]+}")
    public Payload delete(@PathParam("id") String pk) {
        return new Payload(projectPageService.delete(pk));
    }

    /**
     * 楼层页拖动排序
     * @param eo
     * @return
     */
    @POST
    @Path("/move")
    public Payload move(@Valid @NotNull MovePageDto eo) {
        return new Payload(projectPageService.move(eo));
    }
}
