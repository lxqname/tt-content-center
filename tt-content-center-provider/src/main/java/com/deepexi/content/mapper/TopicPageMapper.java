package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.common.enums.StatusEnum;
import com.deepexi.content.domain.dto.TopicQueryDto;
import com.deepexi.content.domain.eo.TopicPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hongchunegn
 * @date 2019/10/20 21:01
 */
@Mapper
public interface TopicPageMapper extends BaseMapper<TopicPage> {

    /**
     * 根据相关setId获取banner列表
     * @param query
     * @param tenantId
     * @return
     */
    List<TopicPage> findList(@Param("query") TopicQueryDto query, @Param("tenantId") String tenantId);

    /**
     * 通过名字获取话题
     * @param name
     * @param tenantId
     * @return
     */
    TopicPage getByName(@Param("name") String name, @Param("tenantId") String tenantId);

    /**
     * 修改状态
     * @param id ID
     * @param statusEnum 状态
     * @return
     */
    Boolean updateStatus(@Param("id") String id, @Param("statusEnum") StatusEnum statusEnum);

}
