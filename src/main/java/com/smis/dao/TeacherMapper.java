package com.smis.dao;

import java.util.List;

import com.smis.dto.PageInfo;
import com.smis.model.Teacher;

public interface TeacherMapper {

	int deleteByPrimaryKey(String tid);

	int insert(Teacher record);

	int insertSelective(Teacher record);

	Teacher selectByPrimaryKey(String tid);

	int updateByPrimaryKeySelective(Teacher record);

	int updateByPrimaryKey(Teacher record);

	/** teacher */
	Teacher selectTeacherRoleByPrimaryKey(String tid);

	Teacher selectTeacherInfoByPrimaryKey(String tid);

	Teacher selectSuperiorTeacherByPrimaryKey(String tid);

	/** student */
	List<Teacher> selectAllManageTeachersBySid(String sid);

	/** manager */
	List<Teacher> selectPageTeacher(PageInfo pageInfo);

	List<Teacher> selectAllTeacher();

}