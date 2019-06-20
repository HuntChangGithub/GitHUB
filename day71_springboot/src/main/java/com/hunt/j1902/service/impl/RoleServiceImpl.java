package com.hunt.j1902.service.impl;

import com.hunt.j1902.mapper.RoleMapper;
import com.hunt.j1902.pojo.Role;
import com.hunt.j1902.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by asus on 2019/6/18.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> getRole() {
        List<Role> list = roleMapper.getRole();
        return list;
    }

    @Override
    public Role getRoleByUid(int uid) {
        Role role = roleMapper.getRoleByUid(uid);
        return role;
    }

    @Override
    public int getRidByRolename(String rolename) {
        int rid = roleMapper.getRidByRolename(rolename);
        return rid;
    }
}

