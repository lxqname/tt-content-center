package com.deepexi.content.controller;


import com.deepexi.content.domain.dto.MemberInterestDto;
import com.deepexi.content.service.InterestService;
import com.deepexi.content.domain.dto.InterestDto;
import com.deepexi.util.config.Payload;
import com.deepexi.util.constant.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;

@Service
@Path("/api/v1/interests")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class InterestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private InterestService interestService;

    /**分页查询兴趣项
     * @Description:
     * @Param: [page, size]
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/9/18 15:14
     */
    @GET
    @Path("/")
    public Payload findPage(@QueryParam("page") @DefaultValue("1") Integer page,
                            @QueryParam("size") @DefaultValue("10") Integer size) {

        return new Payload(interestService.findPage(page, size));
    }


    /**
     * @Description:根据id回显引导语
     * @Param: [pk]
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/9/18 15:34
     */
    @GET
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload selectGuideName(@PathParam("id") String pk) {

        return new Payload(interestService.selectGuideName(pk));
    }


    /**
     * @Description:根据id修改引导语
     * @Param: [pk, eo]
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/9/18 15:40
     */
    @PUT
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload updateGuideName(@PathParam("id") String pk, InterestDto eo) {

        return new Payload(interestService.update(pk, eo));
    }


    /**
     * @Description:新增兴趣项
     * @Param: [eo]
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/9/18 11:13
     */
    @POST
    @Path("/")
    public Payload create(InterestDto eo) {

        return new Payload(interestService.create(eo));
    }


    /**
     * @Description:兴趣项排序-上升
     * @Param: [interestIdLevelDto]
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/9/18 17:51
     */
    @PUT
    @Path("/levelUp/{id:[a-zA-Z0-9]+}")
    public Payload updateLevelUp(@PathParam("id") String id) {

        return new Payload(interestService.updateLevelUp(id));
    }

    /**
     * @Description:兴趣项排序-下降
     * @Param: [interestIdLevelDto]
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/9/18 17:51
     */
    @PUT
    @Path("/levelDown/{id:[a-zA-Z0-9]+}")
    public Payload updateLevelDown(@PathParam("id") String id) {

        return new Payload(interestService.updateLevelDown(id));
    }

    /**
     * @Description: 根据id删除兴趣项(会员信息项中isInterest状态也要改变)
     * @Param: [pk]
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/9/18 17:03
     */
    @DELETE
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload delete(@PathParam("id") String pk) {

        return new Payload(interestService.delete(pk));
    }



    /**
     * @Description: 展示所有兴趣项到H5页面
     * @Param: [eo]
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/9/26 15:43
     */
    @GET
    @Path("/list")
    public Payload findAllInterest() {

        return new Payload(interestService.findAllInterest());
    }


    /**
     * @Description: 保存会员在H5页面选择的兴趣项/值
     * @Param: []
     * @returns: com.deepexi.util.config.Payload
     * @Author: wujie
     * @Date: 2019/10/8 15:14
     */
    @POST
    @Path("/memberSave")
    public Payload saveInterest(MemberInterestDto memberInterestDto) {


        return new Payload(interestService.saveInterest(memberInterestDto));
    }

    @DELETE
    @Path("/")
    public Payload delete(String[] pks) {
        return new Payload(interestService.delete(pks));
    }
}
