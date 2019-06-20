package com.hunt.j1902.service.impl;

import com.hunt.j1902.mapper.RetablesMapper;
import com.hunt.j1902.pojo.Retables;
import com.hunt.j1902.service.RetablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by asus on 2019/6/2.
 */
@Service
public class RetablesServiceImpl implements RetablesService {
    @Autowired
    private RetablesMapper retablesMapper;
    @Override
    public boolean addReturn(Retables retables) {
        boolean isadd = retablesMapper.addReturn(retables);
        return isadd;
    }

    @Override
    public List<Retables> getRetabesByPid(int pid) {
        List<Retables> list = retablesMapper.getRetabesByPid(pid);
        return list;
    }
}
