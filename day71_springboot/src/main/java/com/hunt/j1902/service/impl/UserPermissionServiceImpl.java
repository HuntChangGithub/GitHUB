package com.hunt.j1902.service.impl;

import com.hunt.j1902.mapper.UserPermissionMapper;
import com.hunt.j1902.pojo.UserPermission;
import com.hunt.j1902.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by asus on 2019/6/13.
 */
@Service
public class UserPermissionServiceImpl implements UserPermissionService {
    @Autowired
    private UserPermissionMapper userPermissionMapper;

    @Override
    public Set<UserPermission> getPermsByName(String uname) {
        Set<UserPermission> perms = new HashSet<>();
        List<UserPermission> sysPermissionList = userPermissionMapper.getPermsByName(uname);
//      将list集合转为Set集合（提出重复的权限对象，便于权限验证）
        for (UserPermission perm: sysPermissionList  ) {
            perms.add(perm);
        }
        return perms;
    }
}
