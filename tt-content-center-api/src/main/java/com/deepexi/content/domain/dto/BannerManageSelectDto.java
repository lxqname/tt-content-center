package com.deepexi.content.domain.dto;

import com.deepexi.common.domain.eo.SuperEntity;
import com.deepexi.content.domain.eo.BannerSet;

import java.io.Serializable;
import java.util.List;

/**
 * @author honghcungen
 * @date 2019/09/24 15:49
 */
public class BannerManageSelectDto extends SuperEntity implements Serializable {

    /**
     * banner位置
     */
    private String site;

    /**
     * banner所属应用
     */
    private String applicationOfAffiliation;

    /**
     * banner页
     */
    private List<BannerSet> bannerSetList;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getApplicationOfAffiliation() {
        return applicationOfAffiliation;
    }

    public void setApplicationOfAffiliation(String applicationOfAffiliation) {
        this.applicationOfAffiliation = applicationOfAffiliation;
    }

    public List<BannerSet> getBannerSetList() {
        return bannerSetList;
    }

    public void setBannerSetList(List<BannerSet> bannerSetList) {
        this.bannerSetList = bannerSetList;
    }
}
