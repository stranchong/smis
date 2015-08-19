package com.smis.service;

import java.util.List;
import java.util.Map;

import com.smis.model.Manager;

public interface ManagerService {

	public void addManager(Manager manager) throws Exception;

	public void deleteManager(Long mid) throws Exception;

	public void updateManager(Manager manager) throws Exception;

	public Manager getManager(Long mid) throws Exception;

	public Manager getManagerRoleByUsername(String username) throws Exception;

	public List<Manager> getPageManagers(Map<String, Object> params) throws Exception;

	public Long getManagerTotalCount(Map<String, Object> params) throws Exception;

}