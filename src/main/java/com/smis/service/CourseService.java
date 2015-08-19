package com.smis.service;

import java.util.List;
import java.util.Map;

import com.smis.dto.PageInfo;
import com.smis.model.Course;

public interface CourseService {

	public void addCourse(Course course) throws Exception;

	public void deleteCourse(String cid) throws Exception;

	public void updateCourse(Course course) throws Exception;

	public Course getCourse(String cid) throws Exception;

	public List<Course> getPageCourses(Map<String, Object> params) throws Exception;

	public Long getCourseTotalCount(Map<String, Object> params) throws Exception;
	
	public List<Course> getAllCourses(Map<String, Object> params) throws Exception;

	/** teacher */
	public List<Course> getAllCoursesByTid(String tid) throws Exception;


}