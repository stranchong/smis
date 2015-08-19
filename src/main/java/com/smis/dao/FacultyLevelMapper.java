package com.smis.dao;

import com.smis.model.FacultyLevel;
import com.smis.model.FacultyLevelKey;

public interface FacultyLevelMapper {
    int deleteByPrimaryKey(FacultyLevelKey key);

    int insert(FacultyLevel record);

    int insertSelective(FacultyLevel record);

    FacultyLevel selectByPrimaryKey(FacultyLevelKey key);

    int updateByPrimaryKeySelective(FacultyLevel record);

    int updateByPrimaryKey(FacultyLevel record);
}