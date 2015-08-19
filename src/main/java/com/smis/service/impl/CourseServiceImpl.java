package com.smis.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smis.dao.CourseMapper;
import com.smis.exception.BusinessJsonException;
import com.smis.model.Course;
import com.smis.service.CourseService;
import com.smis.utils.PropertiesUtils;

@Service("courseService")
public class CourseServiceImpl implements CourseService {

	@Resource
	private CourseMapper courseMapper;

	public void addCourse(Course course) throws Exception {
		try {
			if (courseMapper.selectByPrimaryKey(course.getCid()) != null) {
				throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.COURSE_CID_EXIST));
			}
			courseMapper.insertSelective(course);
		} catch (Exception e) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_ADD_ERROR)
					+ e.getMessage());
		}
	}

	public void deleteCourse(String cid) throws Exception {
		try {
			int result = courseMapper.deleteByPrimaryKey(cid);
			if (result == 0) {
				throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_RECORD_NOT_EXIST));
			}
		} catch (Exception e) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_DELETE_ERROR)
					+ e.getMessage());
		}
	}

	public void updateCourse(Course course) throws Exception {
		try {
			int result = courseMapper.updateByPrimaryKeySelective(course);
			if (result == 0) {
				throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_RECORD_NOT_EXIST));
			}
		} catch (Exception e) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_UPDATE_ERROR)
					+ e.getMessage());
		}
	}

	public Course getCourse(String cid) throws Exception {
		Course course = null;
		try {
			course = courseMapper.selectByPrimaryKey(cid);
			if (course == null) {
				throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_USER_NOT_EXIST));
			}
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_ONE_RECORD_ERROR) + e.getMessage());
		}
		return course;
	}

	public List<Course> getPageCourses(Map<String, Object> params) throws Exception {
		List<Course> courses = null;
		try {
			courses = courseMapper.selectPageCourses(params);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_PAGE_RECORD_ERROR) + e.getMessage());
		}
		return courses;
	}

	public Long getCourseTotalCount(Map<String, Object> params) throws Exception {
		Long totalCount = 0l;
		try {
			totalCount = courseMapper.getCourseTotalCount(params);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_TOTAL_COUNT_ERROR) + e.getMessage());
		}
		return totalCount;
	}

	public List<Course> getAllCourses(Map<String, Object> params) throws Exception {
		List<Course> courses = null;
		try {
			courses = courseMapper.selectAllCourses(params);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_ALL_RECORD_ERROR) + e.getMessage());
		}
		return courses;
	}

	/** teacher */
	public List<Course> getAllCoursesByTid(String tid) throws Exception {
		List<Course> courses = null;
		try {
			courses = courseMapper.selectAllCoursesByTid(tid);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_PAGE_RECORD_ERROR) + e.getMessage());
		}
		return courses;
	}

}