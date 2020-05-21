package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.content.domain.eo.DivUrl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DivUrlMapper extends BaseMapper<DivUrl> {


    /**
     * 删除自定义URL
     * @param pks
     * @return
     */
    int deleteByIds(@Param("pks") String... pks);

    /**
     * 通过url获取id
     * @param url
     * @param tenantId
     * @return
     */
    List<String> getByUrl(@Param("url") String url, @Param("tenantId") String tenantId);
}
