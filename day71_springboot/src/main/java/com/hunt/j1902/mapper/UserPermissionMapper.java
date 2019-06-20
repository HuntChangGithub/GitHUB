package com.hunt.j1902.mapper;

import com.hunt.j1902.pojo.UserPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by asus on 2019/6/13.
 */
@Mapper
public interface UserPermissionMapper {
    public List<UserPermission> getPermsByName(String uname);
}
