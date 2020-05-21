package com.deepexi.content.controller;

/**
 * @author lxq
 */

import com.deepexi.content.domain.dto.*;
import com.deepexi.content.service.EnterpriseFloorPageService;
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
@Path("/api/v1/enterpriseFloorPage")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class EnterpriseFloorPageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EnterpriseFloorPageService enterpriseFloorPageService;

    /**
     * 返回楼层列表树形结构(统计多少楼层数也可以调用这)
     * @param eo
     * @return
     */
    @GET
    @Path("/list")
    public Payload findAll(@BeanParam EnterpriseFloorPageDto eo) {
        return new Payload(enterpriseFloorPageService.getTreeByFloor(null));
    }

    /**
     * 新增楼层
     * @param eo
     * @return
     */
    @POST
    @Path("/")
    public Payload create(EnterpriseFloorPageCreateDto eo) {
        return new Payload(enterpriseFloorPageService.create(eo));
    }

    /**
     * 获取楼层里的详情
     * @param pk
     * @return
     */
    @GET
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload details(@PathParam("id") String pk) {
        return new Payload(enterpriseFloorPageService.details(pk));
    }

    /**
     * 修改编辑楼层页
     * @param pk
     * @param eo
     * @return
     */
    @PUT
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload update(@PathParam("id") String pk, EnterpriseFloorPageUpdateDto eo) {
        return new Payload(enterpriseFloorPageService.update(pk, eo));
    }

    /**
     * 删除楼层页
     * @param pk
     * @return
     */
    @DELETE
    @Path("/{id:[a-zA-Z0-9,]+}")
    public Payload delete(@PathParam("id") String pk) {
        return new Payload(enterpriseFloorPageService.delete(pk));
    }

    /**
     * 楼层页拖动排序
     * @param eo
     * @return
     */
    @POST
    @Path("/move")
    public Payload move(@Valid @NotNull MovePageDto eo) {
        return new Payload(enterpriseFloorPageService.move(eo));
    }

    @GET
    @Path("/")
    public Payload findAll() {
        return new Payload(enterpriseFloorPageService.find());
    }
}
