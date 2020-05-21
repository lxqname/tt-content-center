package com.deepexi.content.enums;

/**
 * @author admin
 */
public enum  AdvertTypeEnum {
    /**
     * 关联内容：商品
     */
    BE_ITEM(1),
    /**
     * 关联内容：活动
     */
    BE_ACTIVITY(2),
    /**
     * 关联内容：自定义URL
     */
    BE_DIV_URL(3),
    /**
     * 关联内容：无
     */
    BE_NULL(4),
    ;
    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    AdvertTypeEnum(int value) {
        this.value = value;
    }

    public static AdvertTypeEnum getEnumByValue(Integer value) {

        AdvertTypeEnum[] businessModeEnums = values();
        for (AdvertTypeEnum businessModeEnum : businessModeEnums) {
            if (businessModeEnum.value.equals(value)) {
                return businessModeEnum;
            }
        }
        return null;
    }
}
