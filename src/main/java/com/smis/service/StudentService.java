package com.smis.service;

import java.util.List;
import java.util.Map;

import com.smis.model.Student;

public interface StudentService {

	public void addStudent(Student student) throws Exception;

	public void deleteStudent(String sid) throws Exception;

	public void updateStudent(Student student) throws Exception;

	public Student getStudentRoleBySid(String sid) throws Exception;

	public Student getStudentInfoBySid(String sid) throws Exception;

	/** teacher */
	public List<Student> getAllStudentsByClid(String clid) throws Exception;

	/** manager */
	public List<Student> getPageStudents(Map<String, Object> params) throws Exception;

	public Long getStudentTotalCount(Map<String, Object> params) throws Exception;

}