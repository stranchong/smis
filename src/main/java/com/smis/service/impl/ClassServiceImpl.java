package com.smis.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smis.dao.ClassMapper;
import com.smis.exception.BusinessJsonException;
import com.smis.model.Class;
import com.smis.service.ClassService;
import com.smis.utils.PropertiesUtils;

@Service("classService")
public class ClassServiceImpl implements ClassService {

	@Resource
	private ClassMapper classMapper;

	public void addClass(Class clazz) throws Exception {
		try {
			if (classMapper.selectByPrimaryKey(clazz.getClid()) != null) {
				throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.CLASS_CLID_EXIST));
			}
			classMapper.insertSelective(clazz);
		} catch (Exception e) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_ADD_ERROR)
					+ e.getMessage());
		}
	}

	public void deleteClass(String clid) throws Exception {
		try {
			int result = classMapper.deleteByPrimaryKey(clid);
			if (result == 0) {
				throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_RECORD_NOT_EXIST));
			}
		} catch (Exception e) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_DELETE_ERROR)
					+ e.getMessage());
		}
	}

	public void updateClass(Class clazz) throws Exception {
		try {
			int result = classMapper.updateByPrimaryKeySelective(clazz);
			if (result == 0) {
				throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_RECORD_NOT_EXIST));
			}
		} catch (Exception e) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_UPDATE_ERROR)
					+ e.getMessage());
		}
	}

	public Class getClass(String clid) throws Exception {
		Class clazz = null;
		try {
			clazz = classMapper.selectByPrimaryKey(clid);
			if (clazz == null) {
				throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_USER_NOT_EXIST));
			}
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_ONE_RECORD_ERROR) + e.getMessage());
		}
		return clazz;
	}

	public List<Class> getPageClasses(Map<String, Object> params) throws Exception {
		List<Class> classes = null;
		try {
			classes = classMapper.selectPageClasses(params);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_PAGE_RECORD_ERROR) + e.getMessage());
		}
		return classes;
	}

	public Long getClassTotalCount(Map<String, Object> params) throws Exception {
		Long totalCount = 0l;
		try {
			totalCount = classMapper.getClassTotalCount(params);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_TOTAL_COUNT_ERROR) + e.getMessage());
		}
		return totalCount;
	}

	public List<Class> getAllClasses(Map<String, Object> params) throws Exception {
		List<Class> classes = null;
		try {
			classes = classMapper.selectAllClasses(params);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_ALL_RECORD_ERROR) + e.getMessage());
		}
		return classes;
	}

	/** teacher */
	public List<Class> getAllClassesByCid(String cid) throws Exception {
		List<Class> classes = null;
		try {
			classes = classMapper.selectAllClassesByCid(cid);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_ALL_RECORD_ERROR) + e.getMessage());
		}
		return classes;
	}

}