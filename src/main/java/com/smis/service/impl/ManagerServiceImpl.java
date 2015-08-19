package com.smis.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smis.dao.ManagerMapper;
import com.smis.dao.RoleMapper;
import com.smis.dto.PageInfo;
import com.smis.exception.BusinessException;
import com.smis.exception.BusinessJsonException;
import com.smis.model.Manager;
import com.smis.service.ManagerService;
import com.smis.utils.CustomConstant;
import com.smis.utils.PropertiesUtils;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService {

	@Resource
	private ManagerMapper managerMapper;
	@Resource
	private RoleMapper roleMapper;

	public void addManager(Manager manager) throws Exception {
		try {
			if (managerMapper.selectManagerRoleByUsername(manager.getUsername()) != null) {
				throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.MANAGER_USERNAME_EXIST));
			}
			manager.setRid(roleMapper.selectByPermission(CustomConstant.ROLE_PERMISSION_ADMIN).getRid());
			managerMapper.insertSelective(manager);
		} catch (Exception e) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_ADD_ERROR)
					+ e.getMessage());
		}
	}

	public void deleteManager(Long mid) throws Exception {
		try {
			int result = managerMapper.deleteByPrimaryKey(mid);
			if (result == 0) {
				throw new BusinessException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_RECORD_NOT_EXIST));
			}
		} catch (Exception e) {
			throw new BusinessException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_DELETE_ERROR)
					+ e.getMessage());
		}
	}

	public void updateManager(Manager manager) throws Exception {
		try {
			int result = managerMapper.updateByPrimaryKeySelective(manager);
			if (result == 0) {
				throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_RECORD_NOT_EXIST));
			}
		} catch (Exception e) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_UPDATE_ERROR)
					+ e.getMessage());
		}
	}

	public Manager getManager(Long mid) throws Exception {
		Manager manager = null;
		try {
			manager = managerMapper.selectByPrimaryKey(mid);
			if (manager == null) {
				throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_USER_NOT_EXIST));
			}
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_ONE_RECORD_ERROR) + e.getMessage());
		}
		return manager;
	}

	public Manager getManagerRoleByUsername(String username) throws Exception {
		Manager manager = null;
		try {
			manager = managerMapper.selectManagerRoleByUsername(username);
			if (manager == null) {
				throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_USER_NOT_EXIST));
			}
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_ONE_RECORD_ERROR) + e.getMessage());
		}
		return manager;
	}

	@Override
	public List<Manager> getPageManagers(Map<String, Object> params) throws Exception {
		List<Manager> managers = null;
		try {
			managers = managerMapper.selectPageManagers(params);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_PAGE_RECORD_ERROR) + e.getMessage());
		}
		return managers;
	}

	@Override
	public Long getManagerTotalCount(Map<String, Object> params) throws Exception {
		Long totalCount = 0l;
		try {
			totalCount = managerMapper.getManagerTotalCount(params);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_TOTAL_COUNT_ERROR) + e.getMessage());
		}
		return totalCount;
	}

}