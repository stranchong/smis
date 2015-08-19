package com.smis.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smis.dao.StudentCourseMapper;
import com.smis.exception.BusinessJsonException;
import com.smis.model.StudentCourse;
import com.smis.model.StudentCourseKey;
import com.smis.service.StudentCourseService;
import com.smis.utils.PropertiesUtils;

@Service("studentCourseService")
public class StudentCourseServiceImpl implements StudentCourseService {

	@Resource
	private StudentCourseMapper studentCourseMapper;

	public void addStudentCourse(StudentCourse studentCourse) throws Exception {
		try {
			studentCourseMapper.insertSelective(studentCourse);
		} catch (Exception e) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_ADD_ERROR)
					+ e.getMessage());
		}
	}

	public void deleteStudentCourse(StudentCourseKey key) throws Exception {
		int result = 0;
		try {
			result = studentCourseMapper.deleteByPrimaryKey(key);
		} catch (Exception e) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_DELETE_ERROR)
					+ e.getMessage());
		}
		if (result == 0) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_RECORD_NOT_EXIST));
		}
	}

	public void updateStudentCourse(StudentCourse studentCourse) throws Exception {
		int result = 0;
		try {
			result = studentCourseMapper.updateByPrimaryKeySelective(studentCourse);
		} catch (Exception e) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_UPDATE_ERROR)
					+ e.getMessage());
		}
		if (result == 0) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_RECORD_NOT_EXIST));
		}
	}

	public StudentCourse getStudentCourse(StudentCourseKey key) throws Exception {
		StudentCourse StudentCourse = null;
		try {
			StudentCourse = studentCourseMapper.selectByPrimaryKey(key);

		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_ONE_RECORD_ERROR) + e.getMessage());
		}

		if (StudentCourse == null) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_RECORD_NOT_EXIST));
		}
		return StudentCourse;
	}

	/** student */
	public List<StudentCourse> getPageStudentScoresBySid(Map<String, Object> params) throws Exception {
		List<StudentCourse> studentCourses = null;

		try {
			studentCourses = studentCourseMapper.selectPageStudentScoresBySid(params);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_PAGE_RECORD_ERROR) + e.getMessage());
		}

		return studentCourses;
	}

	public Long getStudentScoreTotalCountBySid(String sid) throws Exception {
		Long totalCount = null;

		try {
			totalCount = studentCourseMapper.selectStudentScoreTotalCountBySid(sid);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_TOTAL_COUNT_ERROR) + e.getMessage());
		}

		return totalCount;
	}

	/** teacher */
	public List<StudentCourse> getPageStudentScoresByTid(Map<String, Object> params) throws Exception {
		List<StudentCourse> studentCourses = null;

		try {
			studentCourses = studentCourseMapper.selectPageStudentScoresByTid(params);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_PAGE_RECORD_ERROR) + e.getMessage());
		}

		return studentCourses;
	}

	public Long getStudentScoreTotalCountByTid(String tid) throws Exception {
		Long totalCount = null;

		try {
			totalCount = studentCourseMapper.selectStudentScoreTotalCountByTid(tid);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_TOTAL_COUNT_ERROR) + e.getMessage());
		}

		return totalCount;
	}
}