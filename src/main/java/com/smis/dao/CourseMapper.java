package com.smis.dao;

import java.util.List;
import java.util.Map;

import com.smis.model.Course;

public interface CourseMapper {

	int deleteByPrimaryKey(String cid);

	int insert(Course record);

	int insertSelective(Course record);

	Course selectByPrimaryKey(String cid);

	int updateByPrimaryKeySelective(Course record);

	int updateByPrimaryKey(Course record);

	List<Course> selectPageCourses(Map<String, Object> params);

	Long getCourseTotalCount(Map<String, Object> params);

	List<Course> selectAllCourses(Map<String, Object> params);

	/** teacher */
	List<Course> selectAllCoursesByTid(String tid);

}