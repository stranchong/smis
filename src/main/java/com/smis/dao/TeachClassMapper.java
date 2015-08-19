package com.smis.dao;

import com.smis.model.TeachClassKey;

public interface TeachClassMapper {
    int deleteByPrimaryKey(TeachClassKey key);

    int insert(TeachClassKey record);

    int insertSelective(TeachClassKey record);
}