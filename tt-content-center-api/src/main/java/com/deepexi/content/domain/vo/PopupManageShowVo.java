package com.deepexi.content.domain.vo;

import java.io.Serializable;
/**
 * @Author:LuFeng
 */
public class PopupManageShowVo implements Serializable {

    /**
     * 弹窗详情
     */
    private PopupManageDetailVo detailVO;

    /**
     * 弹窗弹出与否
     */
    private Boolean flag;

    public PopupManageDetailVo getDetailVO() {
        return detailVO;
    }

    public void setDetailVO(PopupManageDetailVo detailVO) {
        this.detailVO = detailVO;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "PopupManageShowVo{" +
                "detailVO=" + detailVO +
                ", flag=" + flag +
                '}';
    }
}
