package com.hunt.j1902.service;

import com.hunt.j1902.pojo.Retables;

import java.util.List;

/**
 * Created by asus on 2019/6/2.
 */
public interface RetablesService {
    public boolean addReturn(Retables retables);
    public List<Retables> getRetabesByPid(int pid);
}
