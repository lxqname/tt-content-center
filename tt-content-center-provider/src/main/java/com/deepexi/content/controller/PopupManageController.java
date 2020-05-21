package com.deepexi.content.controller;

import com.deepexi.common.annotation.Token;
import com.deepexi.common.enums.StatusEnum;
import com.deepexi.content.domain.dto.PopupManageCreateDto;
import com.deepexi.content.domain.dto.PopupManageQueryDto;
import com.deepexi.content.domain.dto.PopupManageUpdateDto;
import com.deepexi.content.service.PopupManageService;
import com.deepexi.content.domain.dto.PopupManageDto;
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
@Path("/api/v1/popupManages")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class PopupManageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PopupManageService popupManageService;

    @GET
    @Path("/")
    public Payload findPage(@BeanParam PopupManageDto eo,
                            @QueryParam("page") @DefaultValue("1") Integer page,
                            @QueryParam("size") @DefaultValue("10") Integer size) {
        return new Payload(popupManageService.findPage(eo, page, size));
    }

    /**
     * 弹窗列表展示
     * @param eo
     * @return
     */
    @GET
    @Path("/list")
    public Payload findAll(@BeanParam PopupManageDto eo) {
        return new Payload(popupManageService.findAll(eo));
    }

    /**
     * 弹窗详情
     * @param id
     * @return
     */
    @GET
    @Path("/{id:[a-zA-Z0-9]+}")
    @Token(require = false)
    public Payload detail(@PathParam("id") String id) {
        return new Payload(popupManageService.detail(id));
    }

    /**
     * 弹窗详情查询-弹出
     * @param eo
     * @return
     */
    @GET
    @Path("/popup")
    @Token(require = false)
    public Payload detailQuery(@Valid @BeanParam PopupManageQueryDto eo) {
        return new Payload(popupManageService.detailQuery(eo));
    }

    /**
     * 弹窗新建
     * @param eo
     * @return
     */
    @POST
    @Path("/")
    public Payload create(@Valid @NotNull PopupManageCreateDto eo) {
        return new Payload(popupManageService.create(eo));
    }

    /**
     * 弹窗修改
     * @param id
     * @return
     */
    @PUT
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload update(@PathParam("id") String id, @Valid @NotNull PopupManageUpdateDto eo) {
        return new Payload(popupManageService.update(id, eo));
    }

    /**
     * 弹窗删除
     * @param id
     * @return
     */
    @DELETE
    @Path("/{id:[a-zA-Z0-9,]+}")
    public Payload delete(@PathParam("id") String id) {
        return new Payload(popupManageService.delete(id));
    }

    /**
     * 弹窗启用
     * @param id
     * @return
     */
    @PUT
    @Path("/enable/{id:[a-zA-Z0-9,]+}")
    public Payload enable(@PathParam("id") String id) {
        return new Payload(popupManageService.updateStatus(id, StatusEnum.ENABLE));
    }

    /**
     * 弹窗禁用
     * @param id
     * @return
     */
    @PUT
    @Path("/disable/{id:[a-zA-Z0-9,]+}")
    public Payload disable(@PathParam("id") String id) {
        return new Payload(popupManageService.updateStatus(id, StatusEnum.DISABLE));
    }
}
