package com.deepexi.content.service;

import com.deepexi.content.domain.eo.DivUrl;
import com.deepexi.content.domain.dto.DivUrlDto;

import java.util.List;

/**
 * @author admin
 */
public interface DivUrlService {

    /**
     * 自定义URL详情
     * @param pk
     * @return
     */
    DivUrl detail(String pk);

    /**
     * 更新自定义URL
     * @param pk
     * @param eo
     * @return
     */
    Boolean update(String pk, DivUrlDto eo);

    /**
     * 新建自定义URL
     * @param eo
     * @return
     */
    Boolean create(DivUrl eo);

    /**
     * 删除自定义URL
     * @param pk
     * @return
     */
    Boolean delete(String... pk);

    /**
     * 通过id获取url
     * @param id
     * @return
     */
    DivUrl getById(String id);

    /**
     * 通过url获取id
     * @param url
     * @return
     */
    List<String> getByUrl(String url);
}