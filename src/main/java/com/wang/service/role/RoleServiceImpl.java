package com.wang.service.role;/*
@author carl
@date 2022/3/24 - 8:31
*/

import com.wang.dao.role.RoleMapper;
import com.wang.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public List<Role> getRoleList() {
        return roleMapper.getUserRole();
    }
}
