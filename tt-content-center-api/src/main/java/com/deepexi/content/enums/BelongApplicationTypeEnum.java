package com.deepexi.content.enums;

/**
 * @author admin
 */
public enum BelongApplicationTypeEnum {

    /**
     * 前台H5-会员端
     */
    H5_MEMBER("H5_MEMBER"),
    ;

    private String value;

    BelongApplicationTypeEnum(String value){
        this.value = value;
    }

    public static BelongApplicationTypeEnum getEnumByValue(String belongApplication){
        BelongApplicationTypeEnum[] businessModeEnums = values();
        for (BelongApplicationTypeEnum businessModeEnum : businessModeEnums) {
            if (businessModeEnum.value.equals(belongApplication)) {
                return businessModeEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
