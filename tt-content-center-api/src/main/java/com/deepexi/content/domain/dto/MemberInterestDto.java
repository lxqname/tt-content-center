package com.deepexi.content.domain.dto;



import java.io.Serializable;
import java.util.List;

/**
 * @author ：wujie
 * @date ：Created in 2019/10/8 15:07
 * @description：会员在H5页面保存时的数据传输对象
 * @version: 1.0.0
 */
public class MemberInterestDto implements Serializable {

    /**
     * 会员的id
     */
    private String id;

    /**
     * 兴趣id和兴趣值的封装对象集合
     */
    private List<MemberSaveInterestDto> memberSaveInterestDtos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<MemberSaveInterestDto> getMemberSaveInterestDtos() {
        return memberSaveInterestDtos;
    }

    public void setMemberSaveInterestDtos(List<MemberSaveInterestDto> memberSaveInterestDtos) {
        this.memberSaveInterestDtos = memberSaveInterestDtos;
    }

    @Override
    public String toString() {
        return "MemberInterestDto{" +
                "id='" + id + '\'' +
                ", memberSaveInterestDtos=" + memberSaveInterestDtos +
                '}';
    }
}
