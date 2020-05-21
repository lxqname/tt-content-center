package com.deepexi.content.enums;

/**
 * @author admin
 */
public enum ContentSetTypeEnum {

    /**
     * BannerImg
     */
    BANNER_IMG(1),
    /**
     * 专题页设置
     */
    SPECIAL_CONFIG(2),
    /**
     * 楼层页设置
     */
    FLOOR_CONFIG(3),
    /**
     * 弹窗页设置
     */
    POPUP_MANAGE(4),
    /**
     * 话题设置
     */
    TOPIC_CONFIG(5),
    ;

    private Integer value;

    ContentSetTypeEnum(Integer value){
        this.value = value;
    }

    public static ContentSetTypeEnum getEnumByValue(Integer value) {

        ContentSetTypeEnum[] businessModeEnums = values();
        for (ContentSetTypeEnum businessModeEnum : businessModeEnums) {
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
