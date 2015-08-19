package com.smis.service;

import java.util.List;
import java.util.Map;

import com.smis.model.Application;

public interface ApplicationService {

	public Application getApplicationByAid(Long aid) throws Exception;

	/** student */
	public void addApplicationByStudent(Application application) throws Exception;

	public void deleteApplicationByStudent(Long aid) throws Exception;

	public void updateApplicationByStudent(Application application) throws Exception;

	public List<Application> getPageApplicationsBySid(Map<String, Object> params) throws Exception;

	public Long getApplicationTotalCountBySid(Map<String, Object> params) throws Exception;

	/** teacher */
	public List<Application> getPageStudentApplicationsByTid(Map<String, Object> params) throws Exception;

	public Long getStudentApplicationTotalCountByTid(Map<String, Object> params) throws Exception;

	public void updateApplicationByTeacher(Application application) throws Exception;

	/** manager */
	// public List<Application> getPageApplications(PageInfo pageInfo) throws
	// Exception;
	//
	// public List<Application> getAllApplications() throws Exception;

}