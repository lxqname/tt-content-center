package com.deepexi.content.domain.vo;

/**
 * @author ：wujie
 * @date ：Created in 2019/9/16 15:07
 * @description：会员信息项值的Vo
 * @version: 1.0.0
 */
public class MemberInfoItemValueVo {

    /**
     * 信息项的值
     */
    private String itemValue;

    public MemberInfoItemValueVo() {
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    @Override
    public String toString() {
        return "MemberInfoItemValueVo{" +
                "itemValue='" + itemValue + '\'' +
                '}';
    }
}
