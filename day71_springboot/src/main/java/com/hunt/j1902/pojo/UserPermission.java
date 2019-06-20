package com.hunt.j1902.pojo;

import lombok.Data;

/**
 * Created by asus on 2019/6/13.
 */
@Data
public class UserPermission {
    private int permid;  //权限id
    private String pername;   //权限名称
    private  String menuname;  //权限菜单名
    private String menuurl;  //权限地址

}
