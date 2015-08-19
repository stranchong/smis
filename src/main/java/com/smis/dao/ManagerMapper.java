package com.smis.dao;

import java.util.List;
import java.util.Map;

import com.smis.dto.PageInfo;
import com.smis.model.Manager;

public interface ManagerMapper {

	int deleteByPrimaryKey(Long mid);

	int insert(Manager record);

	int insertSelective(Manager record);

	Manager selectByPrimaryKey(Long mid);

	int updateByPrimaryKeySelective(Manager record);

	int updateByPrimaryKey(Manager record);

	Manager selectManagerRoleByUsername(String username);

	List<Manager> selectPageManagers(Map<String, Object> params);

	Long getManagerTotalCount(Map<String, Object> params);

}