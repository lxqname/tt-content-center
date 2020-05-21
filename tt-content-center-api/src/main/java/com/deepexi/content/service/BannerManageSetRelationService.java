package com.deepexi.content.service;

import java.util.List;

/**
 * @author admin
 */
public interface BannerManageSetRelationService {

    /**
     * 创建管理与设置的关系
     * @param setId
     * @return
     */
    Boolean create(String setId);

    /**
     * 通过manageId获取关系表中setId
     * @param manageId
     * @return
     */
    List<String> getSetIdByManageId(String manageId);

    /**
     * 删除
     * @param pk
     * @return
     */
    Boolean deleteByBannerSetId(String pk);

}
