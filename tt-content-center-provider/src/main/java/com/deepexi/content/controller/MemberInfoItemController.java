package com.deepexi.content.controller;

import com.deepexi.content.domain.dto.MemberIdSequenceDto;
import com.deepexi.content.domain.dto.MemberInfoItemConditionDto;
import com.deepexi.content.service.MemberInfoItemService;
import com.deepexi.content.domain.dto.MemberInfoItemDto;
import com.deepexi.util.config.Payload;
import com.deepexi.util.constant.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;

@Service
@Path("/api/v1/memberInfoItems")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class MemberInfoItemController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemberInfoItemService memberInfoItemService;


    /**
     * @Description:分页查询会员信息项信息(含条件查询)
     * @Param: [eo, page, size]
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/9/16 11:46
     */
    @GET
    @Path("/")
    public Payload findPage(@BeanParam MemberInfoItemConditionDto dto,
                            @QueryParam("page") @DefaultValue("1") Integer page,
                            @QueryParam("size") @DefaultValue("10") Integer size) {

        return new Payload(memberInfoItemService.findPage(dto, page, size));
    }


    /**
     * @Description:分页查询会员信息项展示到兴趣项选择页面（不含查询条件，且不展示"会员来源"和"所属分组"）
     * @Param: [page, size]
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/9/17 19:51
     */
    @GET
    @Path("/interest")
    public Payload findPage(@QueryParam("page") @DefaultValue("1") Integer page,
                            @QueryParam("size") @DefaultValue("10") Integer size){

        return new Payload(memberInfoItemService.findPage(page, size));
    }


    /**
     * @Description:增加会员信息项信息
     * @Param: [eo]
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/9/16 20:31
     */
    @POST
    @Path("/")
    public Payload create(MemberInfoItemDto dto) {

        return new Payload(memberInfoItemService.create(dto));
    }

    /**
     * @Description:根据id启动会员信息项
     * @Param: [pk, eo]
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/9/16 16:23
     */
    @PUT
    @Path("/statusOn/{id:[a-zA-Z0-9]+}")
    public Payload updateOn(@PathParam("id") String pk) {
        return new Payload(memberInfoItemService.enable(pk));
    }


    /**
     * @Description:根据id禁用会员信息项
     * @Param: [pk, eo]
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/9/16 16:22
     */
    @PUT
    @Path("/statusOff/{id:[a-zA-Z0-9]+}")
    public Payload updateOff(@PathParam("id") String pk) {
        return new Payload(memberInfoItemService.prohibit(pk));
    }

    /**
     * @Description:会员信息项排序-上升
     * @Param: [memberIdSequenceDto]
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/9/16 17:58
     */
    @PUT
    @Path("/levelUp/{id:[a-zA-Z0-9]+}")
    public Payload updateSequenceUp(@PathParam("id") String id){
        return new Payload(memberInfoItemService.sequenceUp(id));
    }

    /**
     * @Description:会员信息项排序-下降
     * @Param: [memberIdSequenceDto]
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/9/16 17:58
     */
    @PUT
    @Path("/levelDown/{id:[a-zA-Z0-9]+}")
    public Payload updateSequenceDown(@PathParam("id") String id){
        return new Payload(memberInfoItemService.sequenceDown(id));
    }

    /**
     * @Description:根据id删除会员信息项
     * @Param: [pk]
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/9/16 19:05
     */
    @DELETE
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload delete(@PathParam("id") String pk) {
        return new Payload(memberInfoItemService.deleteById(pk));
    }


    @DELETE
    @Path("/")
    public Payload delete(String[] pks) {
        return new Payload(memberInfoItemService.delete(pks));
    }
    @GET
    @Path("/list")
    public Payload findAll(@BeanParam MemberInfoItemDto eo) {
        return new Payload(memberInfoItemService.findAll(eo));
    }

    @GET
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload detail(@PathParam("id") String pk) {
        return new Payload(memberInfoItemService.detail(pk));
    }
}
