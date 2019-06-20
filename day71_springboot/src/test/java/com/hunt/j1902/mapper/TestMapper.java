package com.hunt.j1902.mapper;

import com.hunt.j1902.pojo.Role;
import com.hunt.j1902.pojo.UserPermission;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by asus on 2019/6/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Configuration("com.hunt.j1902.mapper")
public class TestMapper {
    @Resource
    private UserPermissionMapper permissionMapper;
    @Resource
    private  RoleMapper roleMapper;
    @Test
    public void test1(){
        Md5Hash md5Hash = new Md5Hash("admin",null,1024);
        String md5str = md5Hash.toString();
        System.out.println(md5str);
        Md5Hash md5Hash2 = new Md5Hash("123",null,1024);
        String md5str2 = md5Hash2.toString();
        System.out.println(md5str2);

    }
    @Test
    public void test2(){
        List<UserPermission> list = permissionMapper.getPermsByName("admin");
        for ( UserPermission perm: list ) {
            System.out.println(perm);
        }
    }
    @Test
    public void test3(){
        List<Role> list = roleMapper.getRole( );
        for ( Role perm: list ) {
            System.out.println(perm);
        }
    }
}
