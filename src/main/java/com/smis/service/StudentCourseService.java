package com.smis.service;

import java.util.List;
import java.util.Map;

import com.smis.model.StudentCourse;
import com.smis.model.StudentCourseKey;

public interface StudentCourseService {

	public void addStudentCourse(StudentCourse studentCourse) throws Exception;

	public void deleteStudentCourse(StudentCourseKey key) throws Exception;

	public void updateStudentCourse(StudentCourse studentCourse) throws Exception;

	public StudentCourse getStudentCourse(StudentCourseKey key) throws Exception;

	/** student */
	public List<StudentCourse> getPageStudentScoresBySid(Map<String, Object> params) throws Exception;

	public Long getStudentScoreTotalCountBySid(String sid) throws Exception;

	/** teacher */
	public List<StudentCourse> getPageStudentScoresByTid(Map<String, Object> params) throws Exception;

	public Long getStudentScoreTotalCountByTid(String tid) throws Exception;

}