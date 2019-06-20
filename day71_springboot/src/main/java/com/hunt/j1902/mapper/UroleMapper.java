package com.hunt.j1902.mapper;

import com.hunt.j1902.pojo.Urole;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by asus on 2019/6/18.
 */
@Mapper
public interface UroleMapper {
    public boolean addUrole(Urole urole);
    public boolean upUroleById(Urole urole);
}
