package com.smis.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smis.dao.RoleMapper;
import com.smis.dao.StudentMapper;
import com.smis.exception.BusinessException;
import com.smis.exception.BusinessJsonException;
import com.smis.model.Student;
import com.smis.service.StudentService;
import com.smis.utils.CustomConstant;
import com.smis.utils.PropertiesUtils;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentMapper studentMapper;
	@Resource
	private RoleMapper roleMapper;

	public void addStudent(Student student) throws Exception {
		try {
			if (studentMapper.selectByPrimaryKey(student.getSid()) != null) {
				throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.STUDENT_SID_EXIST));
			}
			student.setRid(roleMapper.selectByPermission(CustomConstant.ROLE_PERMISSION_STUDENT).getRid());
			studentMapper.insertSelective(student);
		} catch (Exception e) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_ADD_ERROR)
					+ e.getMessage());
		}
	}

	public void deleteStudent(String sid) throws Exception {
		try {
			int result = studentMapper.deleteByPrimaryKey(sid);
			if (result == 0) {
				throw new BusinessException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_RECORD_NOT_EXIST));
			}
		} catch (Exception e) {
			throw new BusinessException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_DELETE_ERROR)
					+ e.getMessage());
		}
	}

	public void updateStudent(Student student) throws Exception {
		try {
			int result = studentMapper.updateByPrimaryKeySelective(student);
			if (result == 0) {
				throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_RECORD_NOT_EXIST));
			}
		} catch (Exception e) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_UPDATE_ERROR)
					+ e.getMessage());
		}
	}

	public Student getStudentRoleBySid(String sid) throws Exception {
		Student student = null;
		try {
			student = studentMapper.selectStudentRoleByPrimaryKey(sid);
			if (student == null) {
				throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_USER_NOT_EXIST));
			}
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_ONE_RECORD_ERROR) + e.getMessage());
		}
		return student;
	}

	public Student getStudentInfoBySid(String sid) throws Exception {
		Student student = null;
		try {
			student = studentMapper.selectStudentInfoByPrimaryKey(sid);
			if (student == null) {
				throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_RECORD_NOT_EXIST));
			}
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_ONE_RECORD_ERROR) + e.getMessage());
		}
		return student;
	}

	/** teacher */
	public List<Student> getAllStudentsByClid(String clid) throws Exception {
		List<Student> students = null;
		try {
			students = studentMapper.selectAllStudentsByClid(clid);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_ALL_RECORD_ERROR) + e.getMessage());
		}
		return students;
	}

	/** manager */
	public List<Student> getPageStudents(Map<String, Object> params) throws Exception {
		List<Student> students = null;
		try {
			students = studentMapper.selectPageStudents(params);
		} catch (Exception e) {
			throw new BusinessException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_PAGE_RECORD_ERROR)
					+ e.getMessage());
		}
		return students;
	}

	public Long getStudentTotalCount(Map<String, Object> params) throws Exception {
		Long totalCount = 0l;
		try {
			totalCount = studentMapper.getStudentTotalCount(params);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_TOTAL_COUNT_ERROR) + e.getMessage());
		}
		return totalCount;
	}

}