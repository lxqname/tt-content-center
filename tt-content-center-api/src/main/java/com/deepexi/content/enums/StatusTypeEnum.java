package com.deepexi.content.enums;

/**
 * @author hongchungen
 */
public enum  StatusTypeEnum {
    /**
     * 进行中
     */
    RUNNING(0),

    /**
     * 待生效
     */
    TU_BE_EFFECTIVE(1),

    /**
     * 已失效
     */
    LOSE_EFFICACY(2),
    ;

    private Integer value;

    StatusTypeEnum(Integer value) {
        this.value = value;
    }

    public static StatusTypeEnum getEnumByValue(Integer value) {

        StatusTypeEnum[] statusTypeEnums = values();
        for (StatusTypeEnum statusTypeEnum : statusTypeEnums) {
            if (statusTypeEnum.value.equals(value)) {
                return statusTypeEnum;
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
