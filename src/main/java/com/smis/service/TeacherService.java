package com.smis.service;

import java.util.List;

import com.smis.dto.PageInfo;
import com.smis.model.Teacher;

public interface TeacherService {

	public void addTeacher(Teacher teacher) throws Exception;

	public void deleteTeacher(String tid) throws Exception;

	public void updateTeacher(Teacher teacher) throws Exception;

	public Teacher getTeacher(String tid) throws Exception;

	/** teacher */
	public Teacher getTeacherRoleByTid(String tid) throws Exception;

	public Teacher getTeacherInfoByTid(String tid) throws Exception;

	/** student */
	public List<Teacher> getManageTeachersBySid(String sid) throws Exception;

	/** manager */
	public List<Teacher> getTeacherPageList(PageInfo pageInfo) throws Exception;

	public List<Teacher> getTeacherAllList() throws Exception;

}