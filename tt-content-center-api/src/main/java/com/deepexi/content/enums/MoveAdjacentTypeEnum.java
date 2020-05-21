package com.deepexi.content.enums;

/**
 * @author: hongchungen
 * @date: 2019/9/26 00:06
 */
public enum MoveAdjacentTypeEnum {
    /**
     * id前一个位置
     */
    AFTER(1, "id后一个位置"),
    /**
     * id前一个位置
     */
    BEFORE(2, "id前一个位置"),
    ;

    private Integer state;

    private String msg;

    MoveAdjacentTypeEnum(Integer state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
