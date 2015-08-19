package com.smis.service;

import java.util.List;
import java.util.Map;

import com.smis.model.Class;

public interface ClassService {

	public void addClass(Class clazz) throws Exception;

	public void deleteClass(String clid) throws Exception;

	public void updateClass(Class clazz) throws Exception;

	public Class getClass(String clid) throws Exception;

	public List<Class> getPageClasses(Map<String, Object> params) throws Exception;

	public Long getClassTotalCount(Map<String, Object> params) throws Exception;

	public List<Class> getAllClasses(Map<String, Object> params) throws Exception;

	/** teacher */
	public List<Class> getAllClassesByCid(String cid) throws Exception;

}