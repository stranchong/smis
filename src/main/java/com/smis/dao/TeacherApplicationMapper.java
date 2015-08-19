package com.smis.dao;

import com.smis.model.TeacherApplication;
import com.smis.model.TeacherApplicationKey;

public interface TeacherApplicationMapper {

	int deleteByPrimaryKey(TeacherApplicationKey key);

	int insert(TeacherApplication record);

	int insertSelective(TeacherApplication record);

	TeacherApplication selectByPrimaryKey(TeacherApplicationKey key);

	int updateByPrimaryKeySelective(TeacherApplication record);

	int updateByPrimaryKey(TeacherApplication record);
}