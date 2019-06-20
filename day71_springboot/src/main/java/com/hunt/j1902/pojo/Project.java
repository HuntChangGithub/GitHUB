package com.hunt.j1902.pojo;

import lombok.Data;

/**
 * Created by asus on 2019/5/31.
 */
@Data
public class Project {
    private int pid;
    private int uid;
    private String pname;
    private String ptype;
    private String pinfo;
    private double needmoney;
    private String startime;
    private int needtime;
    private String pimg;
    private String pimgs;
    private String uinfo;
    private String uinfos;
    private String tel1;
    private String tel2;
    private double nowmoney;
    private String zhuangtai;
    private String pucard;
    private String usernumber;
    private String pss;

    public Project() {
    }

    public Project(int uid, String pname) {
        this.uid = uid;
        this.pname = pname;
    }

    public Project(int pid, String pucard, String usernumber) {
        this.pid = pid;
        this.pucard = pucard;
        this.usernumber = usernumber;
    }

    public Project(int pid, String pname, String zhuangtai, String pss) {
        this.pid = pid;
        this.pname = pname;
        this.zhuangtai = zhuangtai;
        this.pss = pss;
    }

    public Project(int uid, String pname, String ptype, String pinfo, double needmoney, int needtime, String pimg, String pimgs, String uinfo, String uinfos, String tel1, String tel2, String zhuangtai) {
        this.uid = uid;
        this.pname = pname;
        this.ptype = ptype;
        this.pinfo = pinfo;
        this.needmoney = needmoney;
        this.needtime = needtime;
        this.pimg = pimg;
        this.pimgs = pimgs;
        this.uinfo = uinfo;
        this.uinfos = uinfos;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.zhuangtai = zhuangtai;
    }

    public Project(int pid, int uid, String pname, String ptype, String pinfo, double needmoney, String startime, int needtime, String pimg, String pimgs, String uinfo, String uinfos, String tel1, String tel2, double nowmoney, String zhuangtai, String pucard, String usernumber, String pss) {
        this.pid = pid;
        this.uid = uid;
        this.pname = pname;
        this.ptype = ptype;
        this.pinfo = pinfo;
        this.needmoney = needmoney;
        this.startime = startime;
        this.needtime = needtime;
        this.pimg = pimg;
        this.pimgs = pimgs;
        this.uinfo = uinfo;
        this.uinfos = uinfos;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.nowmoney = nowmoney;
        this.zhuangtai = zhuangtai;
        this.pucard = pucard;
        this.usernumber = usernumber;
        this.pss = pss;
    }
}
