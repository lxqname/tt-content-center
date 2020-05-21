package com.deepexi.content.enums;

/**
 * @version V1.0
 * @author: LuFeng
 * @Package com.deepexi.content.enums
 * @Description:
 * @date: 2019/9/19
 */
public enum  MemberTypeEnum {
    /**
     * 全部
     */
    ALL(0),

    /**
     * 注册会员
     */
    REGISTER(1),
    /**
     * 没有注册会员
     */
    NO_REGISTER(2),
    ;

    private Integer value;


    MemberTypeEnum(Integer value) {
        this.value = value;
    }

    public static MemberTypeEnum getEnumByValue(Integer value) {

        MemberTypeEnum[] businessModeEnums = values();
        for (MemberTypeEnum businessModeEnum : businessModeEnums) {
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
