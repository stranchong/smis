package com.smis.dao;

import com.smis.model.TeachCourseKey;

public interface TeachCourseMapper {
    int deleteByPrimaryKey(TeachCourseKey key);

    int insert(TeachCourseKey record);

    int insertSelective(TeachCourseKey record);
}