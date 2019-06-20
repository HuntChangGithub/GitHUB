package com.hunt.j1902.service;

import com.hunt.j1902.pojo.Project;

import java.util.List;

/**
 * Created by asus on 2019/5/31.
 */
public interface ProjectService {
    public boolean addProject(Project project);
    public int getPid(Project project);
    public boolean upCard(Project project);
    public List<Project> getPro(String zhuangtai);
    public Project getProById(int pid);
    public boolean shenHePro(Project project);
}
