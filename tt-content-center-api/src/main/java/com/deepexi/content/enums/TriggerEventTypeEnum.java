package com.deepexi.content.enums;

/**
 * @version V1.0
 * @author: LuFeng
 * @Package com.deepexi.content.enums
 * @Description:
 * @date: 2019/9/19
 */
public enum TriggerEventTypeEnum {
    
    /**
     * 当日首次登陆
     */
    TODAY_FIRST_LOGIN(1),
    /**
     * 当日首次进入
     */
    TODAY_FIRST_ENTER(2),
    ;
    
    private Integer value;

    TriggerEventTypeEnum(Integer value){
        this.value = value;
    }

    public static TriggerEventTypeEnum getEnumByValue(Integer value) {

        TriggerEventTypeEnum[] businessModeEnums = values();
        for (TriggerEventTypeEnum businessModeEnum : businessModeEnums) {
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
