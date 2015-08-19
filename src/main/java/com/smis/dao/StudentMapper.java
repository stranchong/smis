package com.smis.dao;

import java.util.List;
import java.util.Map;

import com.smis.model.Student;

public interface StudentMapper {

	int insert(Student record);

	int insertSelective(Student record);

	int deleteByPrimaryKey(String sid);

	int updateByPrimaryKeySelective(Student record);

	int updateByPrimaryKey(Student record);

	Student selectByPrimaryKey(String sid);
	
	Student selectStudentRoleByPrimaryKey(String sid); // 登录查询时调用

	Student selectStudentInfoByPrimaryKey(String sid); // 查询单个学生信息时调用

	List<Student> selectAllStudentsByClid(String clid); // 根据clid查询该班级的所有学生

	List<Student> selectPageStudents(Map<String, Object> params); // 模糊查询分页的学生信息

	Long getStudentTotalCount(Map<String, Object> params);	// 模糊查询学生的总记录数

}