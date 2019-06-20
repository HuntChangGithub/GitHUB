package com.hunt.j1902.pojo;

import lombok.Data;

/**
 * Created by asus on 2019/6/18.
 */
@Data
public class Role {
    private int uid;
    private String uname;
    private int rid;
    private String upower;
    private String rolename;

    public Role() {
    }

    public Role(int uid, String uname, int rid, String upower, String rolename) {
        this.uid = uid;
        this.uname = uname;
        this.rid = rid;
        this.upower = upower;
        this.rolename = rolename;
    }
}
