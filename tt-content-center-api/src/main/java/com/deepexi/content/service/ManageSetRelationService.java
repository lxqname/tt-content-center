package com.deepexi.content.service;

import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.content.domain.eo.ManageSetRelation;
import com.deepexi.content.domain.dto.ManageSetRelationDto;
import java.util.*;

public interface ManageSetRelationService {

    PageBean<ManageSetRelation> findPage(ManageSetRelationDto eo, Integer page, Integer size);

    List<ManageSetRelation> findAll(ManageSetRelationDto eo);

    ManageSetRelation detail(String pk);

    Boolean update(String pk, ManageSetRelationDto eo);

    Boolean create(ManageSetRelationDto eo);

    Boolean delete(String... pk);
}