package com.deepexi.content.domain.dto;

import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;
import java.io.Serializable;
/**
 * @Author:LuFeng
 */
public class PopupManageQueryDto implements Serializable {

    /**
     * 弹窗页面ID
     */
    @QueryParam("pageId")
    @NotNull(message = "弹窗页面ID不能为空")
    private String pageId;
    /**
     * 用户账号ID
     */
    @QueryParam("accountId")
    @NotNull(message = "用户账号ID不能为空")
    private String accountId;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
