package com.hunt.j1902.service;

import com.hunt.j1902.pojo.UserPermission;

import java.util.Set;

/**
 * Created by asus on 2019/6/13.
 */
public interface UserPermissionService {
    public Set<UserPermission> getPermsByName(String uname);
}
