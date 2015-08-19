package com.smis.dao;

import com.smis.model.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Long rid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long rid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    Role selectByPermission(Integer permission);
}
