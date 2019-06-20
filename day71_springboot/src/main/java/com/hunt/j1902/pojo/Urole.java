package com.hunt.j1902.pojo;

import lombok.Data;

/**
 * Created by asus on 2019/6/18.
 */
@Data
public class Urole {
    private int uid;
    private int rid;

    public Urole() {
    }

    public Urole(int uid, int rid) {
        this.uid = uid;
        this.rid = rid;
    }
}
