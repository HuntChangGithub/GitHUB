package com.hunt.j1902.shiro;

import com.hunt.j1902.pojo.User;
import com.hunt.j1902.pojo.UserPermission;
import com.hunt.j1902.service.UserPermissionService;
import com.hunt.j1902.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by asus on 2019/6/18.
 */
public class MyRealm extends AuthorizingRealm {
    //    注入用户业务接口
    @Autowired
    private UserService userService;
    @Autowired
    private UserPermissionService userPermissionService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String principal = (String)principals.getPrimaryPrincipal();
//        根据用户名查询用户权限
        Set<UserPermission> permissionSet = userPermissionService.getPermsByName(principal);
        Set<String> perms = new HashSet<>();
        if(permissionSet!=null){
            for (UserPermission perm: permissionSet ) {
                perms.add(perm.getPername());//获取权限名称组成权限集合
            }
        }
        System.out.println(perms);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(perms);//设置权限
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户名
        String principal = (String)token.getPrincipal();
        User user = userService.getUserByName(principal);
        SimpleAuthenticationInfo authenticationInfo = null;
        if (user != null){
            authenticationInfo = new SimpleAuthenticationInfo(principal,user.getUpw(),null,this.getName());

        }
        return authenticationInfo;
    }
}
