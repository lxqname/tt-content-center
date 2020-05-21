package com.deepexi.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.common.enums.StatusEnum;
import com.deepexi.content.domain.dto.PopupManageDto;
import com.deepexi.content.domain.dto.PopupManageQueryDto;
import com.deepexi.content.domain.eo.PopupManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface PopupManageMapper extends BaseMapper<PopupManage> {

    /**
     * 获取弹窗列表
     * @param eo
     * @param tenantId
     * @return
     */
    List<PopupManage> findList(@Param("eo") PopupManageDto eo,@Param("tenantId")  String tenantId);

    /**
     * 删除弹窗
     * @param pks
     * @return
     */
    int deleteByIds(@Param("pks") String... pks);


    /**
     * 获得弹窗
     * @param name
     * @param tenantId
     * @return
     */
    PopupManage getByName(@Param("name") String name,@Param("tenantId")  String tenantId);


    /**
     * 修改状态
     * @param id
     * @param statusEnum
     * @return
     */
    int updateStatus(@Param("id") String id,@Param("statusEnum") StatusEnum statusEnum);

    /**
     * 获得弹窗
     * @param eo
     * @param tenantId 租戶ID
     * @return
     */
    List<PopupManage> listByQuery(@Param("eo") PopupManageQueryDto eo, @Param("tenantId")  String tenantId);
}
