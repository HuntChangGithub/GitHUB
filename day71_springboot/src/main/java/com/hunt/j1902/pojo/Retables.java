package com.hunt.j1902.pojo;

import lombok.Data;

/**
 * Created by asus on 2019/6/1.
 */
@Data
public class Retables {
    private int rid;
    private int pid;
    private String pname;
    private double paymoney;
    private String retype;
    private String minge;
    private String canbuy;
    private String rewhat;
    private int renumber;
    private String reimg;
    private String backtime;
    private String fapiao;
    private double youfei;
    //pname, paymoney, minge, canBuy, rewhat, renumber, reimg, backtime, fapiao, youfei


    public Retables() {
    }

    public Retables(int pid, String pname, double paymoney, String retype, String minge, String canbuy, String rewhat, int renumber, String reimg, String backtime, String fapiao, double youfei) {
        this.pid = pid;
        this.pname = pname;
        this.paymoney = paymoney;
        this.retype = retype;
        this.minge = minge;
        this.canbuy = canbuy;
        this.rewhat = rewhat;
        this.renumber = renumber;
        this.reimg = reimg;
        this.backtime = backtime;
        this.fapiao = fapiao;
        this.youfei = youfei;
    }

    public Retables(int rid, int pid, String pname, double paymoney, String retype, String minge, String canbuy, String rewhat, int renumber, String reimg, String backtime, String fapiao, double youfei) {
        this.rid = rid;
        this.pid = pid;
        this.pname = pname;
        this.paymoney = paymoney;
        this.retype = retype;
        this.minge = minge;
        this.canbuy = canbuy;
        this.rewhat = rewhat;
        this.renumber = renumber;
        this.reimg = reimg;
        this.backtime = backtime;
        this.fapiao = fapiao;
        this.youfei = youfei;
    }
}
