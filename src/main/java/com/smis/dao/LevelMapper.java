package com.smis.dao;

import com.smis.model.Level;

public interface LevelMapper {
    int deleteByPrimaryKey(Long lid);

    int insert(Level record);

    int insertSelective(Level record);

    Level selectByPrimaryKey(Long lid);

    int updateByPrimaryKeySelective(Level record);

    int updateByPrimaryKey(Level record);
}