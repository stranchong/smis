package com.smis.dao;

import com.smis.model.ManageClassKey;

public interface ManageClassMapper {
    int deleteByPrimaryKey(ManageClassKey key);

    int insert(ManageClassKey record);

    int insertSelective(ManageClassKey record);
}