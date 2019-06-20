package com.hunt.j1902.service.impl;

import com.hunt.j1902.mapper.UroleMapper;
import com.hunt.j1902.pojo.Urole;
import com.hunt.j1902.service.UroleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by asus on 2019/6/18.
 */
@Service
public class UroleServiceImpl implements UroleService {
    @Autowired
    private UroleMapper uroleMapper;
    @Override
    public boolean addUrole(Urole urole) {
        boolean isadd = uroleMapper.addUrole(urole);
        return isadd;
    }

    @Override
    public boolean upUroleById(Urole urole) {
        boolean isUp = uroleMapper.upUroleById(urole);
        return isUp;
    }

}
