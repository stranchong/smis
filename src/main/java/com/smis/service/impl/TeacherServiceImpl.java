package com.smis.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smis.dao.ClassMapper;
import com.smis.dao.CourseMapper;
import com.smis.dao.TeacherMapper;
import com.smis.dto.PageInfo;
import com.smis.exception.BusinessJsonException;
import com.smis.model.Teacher;
import com.smis.service.TeacherService;
import com.smis.utils.PropertiesUtils;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

	@Resource
	private TeacherMapper teacherMapper;
	@Resource
	private CourseMapper courseMapper;
	@Resource
	private ClassMapper classMapper;

	public void addTeacher(Teacher teacher) throws Exception {
		try {
			teacherMapper.insertSelective(teacher);
		} catch (Exception e) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_ADD_ERROR)
					+ e.getMessage());
		}
	}

	public void deleteTeacher(String tid) throws Exception {
		try {
			int result = teacherMapper.deleteByPrimaryKey(tid);
			if (result == 0) {
				throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_RECORD_NOT_EXIST));
			}
		} catch (Exception e) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_DELETE_ERROR)
					+ e.getMessage());
		}
	}

	public void updateTeacher(Teacher teacher) throws Exception {
		try {
			int result = teacherMapper.updateByPrimaryKeySelective(teacher);
			if (result == 0) {
				throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_RECORD_NOT_EXIST));
			}
		} catch (Exception e) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_UPDATE_ERROR)
					+ e.getMessage());
		}
	}

	public Teacher getTeacher(String tid) throws Exception {
		Teacher Teacher = null;

		try {
			Teacher = teacherMapper.selectByPrimaryKey(tid);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_ONE_RECORD_ERROR) + e.getMessage());
		}

		if (Teacher == null) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_RECORD_NOT_EXIST));
		}

		return Teacher;
	}

	/** teacher */
	public Teacher getTeacherRoleByTid(String tid) throws Exception {
		Teacher Teacher = null;

		try {
			Teacher = teacherMapper.selectTeacherRoleByPrimaryKey(tid);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_ONE_RECORD_ERROR) + e.getMessage());
		}

		if (Teacher == null) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_USER_NOT_EXIST));
		}

		return Teacher;
	}

	@SuppressWarnings("unused")
	public Teacher getTeacherInfoByTid(String tid) throws Exception {
		Teacher teacher = null;

		try {
			teacher = teacherMapper.selectTeacherInfoByPrimaryKey(tid);
			// 根据tid获取教师所教的课程集合
			teacher.setCourses(courseMapper.selectAllCoursesByTid(tid));
			// 根据tid获取教师所教的班级集合
			teacher.setClasses(classMapper.selectAllClassesByTid(tid));
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_ONE_RECORD_ERROR) + e.getMessage());
		}

		if (teacher == null) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_RECORD_NOT_EXIST));
		}

		return teacher;
	}

	/** student */
	public List<Teacher> getManageTeachersBySid(String sid) throws Exception {
		List<Teacher> teachers = null;

		try {
			teachers = teacherMapper.selectAllManageTeachersBySid(sid);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_PAGE_RECORD_ERROR) + e.getMessage());
		}

		return teachers;
	}

	/** manager */
	public List<Teacher> getTeacherPageList(PageInfo pageInfo) throws Exception {
		List<Teacher> teachers = null;

		try {
			teachers = teacherMapper.selectPageTeacher(pageInfo);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_PAGE_RECORD_ERROR) + e.getMessage());
		}

		return teachers;
	}

	public List<Teacher> getTeacherAllList() throws Exception {
		List<Teacher> teachers = null;

		try {
			teachers = teacherMapper.selectAllTeacher();
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_ALL_RECORD_ERROR) + e.getMessage());
		}

		return teachers;
	}

}