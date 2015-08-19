package com.smis.dao;

import java.util.List;
import java.util.Map;

import com.smis.model.Application;

public interface ApplicationMapper {

	int deleteByPrimaryKey(Long aid);

	int insert(Application record);

	int insertSelective(Application record);

	Application selectByPrimaryKey(Long aid);

	int updateByPrimaryKeySelective(Application record);

	int updateByPrimaryKey(Application record);

	/** student */
	List<Application> selectPageApplicationsBySid(Map<String, Object> params);

	Long getApplicationTotalCountBySid(Map<String, Object> params);

	Application selectLastApplicationBySid(String sid);

	/** teacher */
	List<Application> selectPageStudentApplicationsByTid(Map<String, Object> params);

	Long getStudentApplicationTotalCountByTid(Map<String, Object> params);

	/** manager */
	List<Application> selectAllApplications();

	List<Application> selectPageApplications(Map<String, Object> params);

}