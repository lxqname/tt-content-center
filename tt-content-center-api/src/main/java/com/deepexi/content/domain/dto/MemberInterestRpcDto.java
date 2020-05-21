package com.deepexi.content.domain.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：wujie
 * @date ：Created in 2019/10/9 17:46
 * @description：会员选择兴趣项名称和兴趣项值的rpc传输对象
 * @version: 1.0.0
 */
public class MemberInterestRpcDto implements Serializable {

    /**
     * 会员选择的兴趣项的名称
     */
    private String interestName;

    /**
     * 会员选择的兴趣项的值
     */
    private List<String> interestValues;

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

    public List<String> getInterestValues() {
        return interestValues;
    }

    public void setInterestValues(List<String> interestValues) {
        this.interestValues = interestValues;
    }
}
