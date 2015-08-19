package com.smis.dao;

import java.util.List;
import java.util.Map;

import com.smis.model.Class;

public interface ClassMapper {

	int deleteByPrimaryKey(String clid);

	int insert(Class record);

	int insertSelective(Class record);

	Class selectByPrimaryKey(String clid);

	int updateByPrimaryKeySelective(Class record);

	int updateByPrimaryKey(Class record);

	List<Class> selectPageClasses(Map<String, Object> params);

	Long getClassTotalCount(Map<String, Object> params);

	List<Class> selectAllClasses(Map<String, Object> params);

	/** teacher */
	List<Class> selectAllClassesByTid(String tid);

	List<Class> selectAllClassesByCid(String cid);

}