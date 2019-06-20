package com.hunt.j1902.service.impl;

import com.hunt.j1902.mapper.ProjectMapper;
import com.hunt.j1902.pojo.Project;
import com.hunt.j1902.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by asus on 2019/5/31.
 */
@Service
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    private ProjectMapper projectMapper;
    @Override
    public boolean addProject(Project project) {
        boolean isadd = projectMapper.addProject(project);
        return isadd;
    }

    @Override
    public int getPid(Project project) {
        int pid = projectMapper.getPid(project);
        return pid;
    }

    @Override
    public boolean upCard(Project project) {
        boolean isup = projectMapper.upCard(project);
        return isup;
    }

    @Override
    public List<Project> getPro(String zhuangtai) {
        List<Project> list = projectMapper.getPro(zhuangtai);
        return list;
    }

    @Override
    public Project getProById(int pid) {
        Project project = projectMapper.getProById(pid);
        return project;
    }

    @Override
    public boolean shenHePro(Project project) {
        boolean isup = projectMapper.shenHePro(project);
        return isup;
    }

}
