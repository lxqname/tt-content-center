package com.deepexi.content.enums;


/**
 * @version V1.0
 * @author: LuFeng
 * @Package com.deepexi.content.enums
 * @Description:
 * @date: 2019/9/19
 */
public enum PopupTypeEnum {

    /**
     * 常规活动弹窗
     */
    ROUTINE_ACTIVITY(1),
    /**
     * 新会员弹窗
     */
    NEW_MEMBER(2),
    ;

    private Integer value;


    PopupTypeEnum(Integer value) {
        this.value = value;
    }

    public static PopupTypeEnum getEnumByValue(Integer value) {

        PopupTypeEnum[] businessModeEnums = values();
        for (PopupTypeEnum businessModeEnum : businessModeEnums) {
            if (businessModeEnum.value.equals(value)) {
                return businessModeEnum;
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}