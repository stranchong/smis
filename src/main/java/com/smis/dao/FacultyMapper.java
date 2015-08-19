package com.smis.dao;

import com.smis.model.Faculty;

public interface FacultyMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(Faculty record);

    int insertSelective(Faculty record);

    Faculty selectByPrimaryKey(Long fid);

    int updateByPrimaryKeySelective(Faculty record);

    int updateByPrimaryKey(Faculty record);
}