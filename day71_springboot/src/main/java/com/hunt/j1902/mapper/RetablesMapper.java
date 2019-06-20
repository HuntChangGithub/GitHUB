package com.hunt.j1902.mapper;

import com.hunt.j1902.pojo.Retables;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by asus on 2019/6/2.
 */
@Mapper
public interface RetablesMapper {
    public boolean addReturn(Retables retables);
    public List<Retables> getRetabesByPid(int pid);
}
