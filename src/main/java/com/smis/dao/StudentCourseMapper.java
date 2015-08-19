package com.smis.dao;

import java.util.List;
import java.util.Map;

import com.smis.model.StudentCourse;
import com.smis.model.StudentCourseKey;

public interface StudentCourseMapper {

	int deleteByPrimaryKey(StudentCourseKey key);

	int insert(StudentCourse record);

	int insertSelective(StudentCourse record);

	StudentCourse selectByPrimaryKey(StudentCourseKey key);

	int updateByPrimaryKeySelective(StudentCourse record);

	int updateByPrimaryKey(StudentCourse record);

	// 学生查询成绩时调用
	List<StudentCourse> selectPageStudentScoresBySid(Map<String, Object> params);

	// 学生查询成绩总条数时调用
	Long selectStudentScoreTotalCountBySid(String sid);

	// 教师查询成绩时调用
	List<StudentCourse> selectPageStudentScoresByTid(Map<String, Object> params);

	// 教师查询成绩总条数时调用
	Long selectStudentScoreTotalCountByTid(String tid);

}