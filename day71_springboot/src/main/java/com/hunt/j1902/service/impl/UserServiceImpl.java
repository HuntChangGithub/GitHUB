package com.hunt.j1902.service.impl;

import com.hunt.j1902.mapper.UserMapper;
import com.hunt.j1902.pojo.User;
import com.hunt.j1902.pojo.UserPermission;
import com.hunt.j1902.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by asus on 2019/5/27.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByName(String uname) {
        User user = userMapper.getUserByName(uname);
        return user;
    }

    @Override
    public boolean addUser(User user) {
        boolean isadd = userMapper.addUser(user);
        return false;
    }

   @Override
    public List<User> getUsers() {
        List<User> list = userMapper.getUsers();
        return list;
    }

    @Override
    public boolean addUser1(User user) {
        boolean isadd = userMapper.addUser1(user);
        return isadd;
    }

    @Override
    public int delUserById(int uid) {
        int del = userMapper.delUserById(uid);
        return del;
    }

    @Override
    public User getUserById(int uid) {
        User user = userMapper.getUserById(uid);
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        boolean isUp = userMapper.updateUser(user);
        return isUp;
    }

    @Override
    public List<User> selectUser(String string) {
        List<User> list = userMapper.selectUser(string);
        return list;
    }

    @Override
    public boolean upUser(User user) {
        boolean isup = userMapper.upUser(user);
        return isup;
    }

    @Override
    public List<User> getUserByIsrel(String isrel) {
        List<User> users = userMapper.getUserByIsrel(isrel);
        return users;
    }

    @Override
    public boolean rzUpdate(User user) {
        boolean isup = userMapper.rzUpdate(user);
        return isup;
    }

    @Override
    public int cleanShenPi(User user) {
        int isDelShenpi = userMapper.cleanShenPi(user);
        return isDelShenpi;
    }

    @Override
    public boolean upUpowerByUid(User user) {
        boolean isUp = userMapper.upUpowerByUid(user);
        return isUp;
    }


}
