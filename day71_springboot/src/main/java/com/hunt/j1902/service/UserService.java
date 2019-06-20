package com.hunt.j1902.service;

import com.hunt.j1902.pojo.User;
import com.hunt.j1902.pojo.UserPermission;

import java.util.List;
import java.util.Set;

/**
 * Created by asus on 2019/5/27.
 */
public interface UserService {
    public User getUserByName(String uname);
    public boolean addUser(User user);
    public List<User> getUsers();
    public boolean addUser1(User user);
    public int delUserById(int uid);
    public User getUserById(int uid);
    public boolean updateUser(User user);
    public List<User> selectUser(String string);
    public boolean upUser(User user);
    public List<User> getUserByIsrel(String isrel);
    public boolean rzUpdate(User user);
    public int cleanShenPi(User user);
    public boolean upUpowerByUid(User user);
}
