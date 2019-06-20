package com.hunt.j1902.pojo;

import lombok.Data;

/**
 * Created by asus on 2019/5/27.
 */
@Data
public class User {
    private int uid;
    private String uname;
    private String upw;
    private String upower;
    private String email;
    private String unumber;
    private String uimg;
    private String utype;
    private String relname;
    private String tel;
    private String isrel;
    private String ps;
    public User() {
    }
    public User(int uid, String isrel) {
        this.uid = uid;
        this.isrel = isrel;
    }

    public User(String uname, String upw, String upower) {
        this.uname = uname;
        this.upw = upw;
        this.upower = upower;
    }
    public User(int uid, String isrel, String ps) {
        this.uid = uid;
        this.isrel = isrel;
        this.ps = ps;
    }
    public User(String uname, String upw, String upower, String email) {
        this.uname = uname;
        this.upw = upw;
        this.upower = upower;
        this.email = email;
    }
    public User( int uid ,String uname,  String relname, String email) {
        this.uid = uid;
        this.uname = uname;
        this.relname = relname;
        this.email = email;
    }
//uname , upw , upower , email ,isre
    //uname , upw , upower , email , relname , isrel
    public User(String uname, String upw, String upower, String email, String isrel) {
        this.uname = uname;
        this.upw = upw;
        this.upower = upower;
        this.email = email;
        this.isrel = isrel;
    }

    public User(String uname, String upw, String upower, String email, String relname, String isrel) {
        this.uname = uname;
        this.upw = upw;
        this.upower = upower;
        this.email = email;
        this.relname = relname;
        this.isrel = isrel;
    }

    public User(int uid, String unumber, String uimg, String utype, String relname, String tel, String isrel) {
        this.uid = uid;
        this.unumber = unumber;
        this.uimg = uimg;
        this.utype = utype;
        this.relname = relname;
        this.tel = tel;
        this.isrel = isrel;
    }

    public User(int uid, String uname, String upw, String upower, String email, String unumber, String uimg, String utype, String relname, String tel, String reltype) {
        this.uid = uid;
        this.uname = uname;
        this.upw = upw;
        this.upower = upower;
        this.email = email;
        this.unumber = unumber;
        this.uimg = uimg;
        this.utype = utype;
        this.relname = relname;
        this.tel = tel;
        this.isrel = reltype;
    }

    public User(int uid, String uname, String upw, String upower, String email, String unumber, String uimg, String utype, String relname, String tel, String isrel, String ps) {
        this.uid = uid;
        this.uname = uname;
        this.upw = upw;
        this.upower = upower;
        this.email = email;
        this.unumber = unumber;
        this.uimg = uimg;
        this.utype = utype;
        this.relname = relname;
        this.tel = tel;
        this.isrel = isrel;
        this.ps = ps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        return getUname().equals(user.getUname());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getUname().hashCode();
        return result;
    }

}
