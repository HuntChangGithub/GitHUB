package com.hunt.j1902.mapper;

import com.hunt.j1902.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by asus on 2019/6/18.
 */
@Mapper
public interface RoleMapper {
    public List<Role> getRole();
    public Role getRoleByUid(int uid);
    public int getRidByRolename(String rolename);
}
