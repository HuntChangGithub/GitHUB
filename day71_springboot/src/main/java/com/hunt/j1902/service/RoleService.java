package com.hunt.j1902.service;

import com.hunt.j1902.pojo.Role;

import java.util.List;

/**
 * Created by asus on 2019/6/18.
 */
public interface RoleService {
    public List<Role> getRole();
    public Role getRoleByUid(int uid);
    public int getRidByRolename(String rolename);
}
