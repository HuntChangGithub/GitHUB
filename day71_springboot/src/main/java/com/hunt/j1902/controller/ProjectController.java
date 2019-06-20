package com.hunt.j1902.controller;

import com.hunt.j1902.pojo.Project;
import com.hunt.j1902.pojo.Retables;
import com.hunt.j1902.pojo.User;
import com.hunt.j1902.service.ProjectService;
import com.hunt.j1902.service.RetablesService;
import com.hunt.j1902.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by asus on 2019/5/31.
 */
@Controller
public class ProjectController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private RetablesService retablesService;
    //前台我的众筹页面

    @RequiresPermissions(value={"我的众筹"})
    @RequestMapping(value = "/minecrowdfunding" , method = RequestMethod.GET)
    public String myZhongchou(HttpServletRequest request , Model model){
        String uname = (String) request.getSession().getAttribute("uname");
        User user = userService.getUserByName(uname);
        model.addAttribute("user" ,user);
        return "minecrowdfunding";
    }
    //前台发起众筹页面
    @RequiresPermissions(value={"发起众筹-协议"})
    @RequestMapping(value = "/start" , method = RequestMethod.GET)
    public String add(HttpServletRequest request , Model model){
        String uname = (String) request.getSession().getAttribute("uname");
        User user = userService.getUserByName(uname);
        String isrel = user.getIsrel();
        String string;
        if (isrel.equals("已实名认证")){
            return "start";
        }else {
            model.addAttribute("user",user);
            string = "您需要进行实名验证才可发起申请";
            model.addAttribute("string",string);
            return "minecrowdfunding";
        }
    }
    //前台发起众筹页面
    @RequiresPermissions(value={"发起众筹-项目信息"})
    @RequestMapping(value = "/start-step-1" , method = RequestMethod.GET)
    public String step1(){

        return "start-step-1";
    }
    //前台发起众筹页面----项目信息
    @RequiresPermissions(value={"发起众筹-添加回报"})
    @RequestMapping(value = "/upload1.do" ,  method = RequestMethod.POST)
    public String upload(@RequestParam("pimg")MultipartFile pimg ,
                         @RequestParam("pimgs")MultipartFile pimgs ,
                         HttpServletRequest request ,
                         @RequestParam("ptype") String ptype ,
                         @RequestParam("pname") String pname ,
                         @RequestParam("pinfo") String pinfo ,
                         @RequestParam("needmoney") double needmoney ,
                         @RequestParam("needtime") int needtime ,
                         @RequestParam("uinfo") String uinfo ,
                         @RequestParam("uinfos") String uinfos ,
                         @RequestParam("tel1") String tel1 ,
                         @RequestParam("tel2") String tel2 ,
                         Model model) {

        String uname = (String)request.getSession().getAttribute("uname");
        User user = userService.getUserByName(uname);
        int uid = user.getUid();
        System.out.println(uid);
        String path = new String("E:\\IdeaProjects\\day71_springboot\\src\\main\\resources\\static\\upload");
        String fileName1 = pimg.getOriginalFilename();
        String fileName2 = pimgs.getOriginalFilename();
        String location1 = path + "/" + fileName1;
        String location2 = path + "/" + fileName2;
        File f1 = new File(location1);
        File f2 = new File(location2);

        if (pname != "" &&  ptype != "" &&  pinfo != "" &&  needmoney != 0 &&  needtime != 0 &&  fileName1 != "" &&
                fileName2 != "" &&  uinfo != "" &&  uinfos != "" &&  tel1 != "" &&  tel2 != "" ){
            try {
                f1.createNewFile();
                f2.createNewFile();
                pimg.transferTo(f1);
                pimgs.transferTo(f2);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String zhuangtai = "审核中" ;
            Project project = new Project(uid, pname, ptype, pinfo, needmoney, needtime, fileName1, fileName2, uinfo, uinfos, tel1, tel2, zhuangtai);
            boolean isadd = projectService.addProject(project);
            System.out.println(isadd);
            model.addAttribute("pname",pname);

            List<Retables> retables1 = null;
            model.addAttribute("retables1",retables1);
            return "start-step-2";
        }else {
            String string = "请填写完整的信息！";
            model.addAttribute("string",string);
            return "start-step-1";
        }
    }
    //前台发起众筹页面----回报信息
    @RequiresPermissions(value={"发起众筹-添加回报"})
    @RequestMapping(value = "/upload2.do" , method = RequestMethod.POST)
    public String upload(@RequestParam("pname") String pname ,
                         @RequestParam("retype")String retype ,
                         @RequestParam("paymoney")double paymoney ,
                         @RequestParam("rewhat")String rewhat ,
                         @RequestParam("reimg")MultipartFile reimg ,
                         @RequestParam("renumber")int renumber ,
                         @RequestParam("canbuy")String canbuy ,
                         @RequestParam("youfei")double youfei ,
                         @RequestParam("fapiao")String fapiao ,
                         @RequestParam("backtime")String backtime ,
                         HttpServletRequest request , Model model){
        //double paymoney, String retype, String minge, String canbuy, String rewhat, int renumber, String reimg, String backtime, String fapiao, String youfei
        String minge = "不限购";
        String uname = (String) request.getSession().getAttribute("uname");
        int uid = userService.getUserByName(uname).getUid();
        Project project = new Project(uid ,pname);
        int pid = projectService.getPid(project);

        String path = new String("E:\\IdeaProjects\\day71_springboot\\src\\main\\resources\\static\\upload");
        String fileName = reimg.getOriginalFilename();
        String location1 = path + "/" + fileName;
        File f1 = new File(location1);
        try {
            f1.createNewFile();
            reimg.transferTo(f1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Retables retables = new Retables(pid, pname, paymoney, retype, minge, canbuy, rewhat, renumber, fileName, backtime, fapiao, youfei);
        boolean isadd = retablesService.addReturn(retables);
        System.out.println(isadd);
        List<Retables> retables1 = retablesService.getRetabesByPid(pid);
        model.addAttribute("retables1",retables1);
        model.addAttribute("pname",pname);
        System.out.println(pname);
        return "start-step-2";
    }
    //前台发起众筹页面
    @RequiresPermissions(value={"发起众筹-法人信息"})
    @RequestMapping(value = "/start-step-3" , method = RequestMethod.POST)
    public String tostep3( @RequestParam("pname") String pname , Model  model){
        model.addAttribute("pname",pname);
        System.out.println(pname);
        return "start-step-3";
    }
    //前台发起众筹页面----提交
    @RequiresPermissions(value={"发起众筹-完成"})
    @RequestMapping(value = "/tijiao" , method = RequestMethod.POST)
    public String tostep4(@RequestParam("pname")String pname,
                          @RequestParam("pucard")String pucard ,
                          @RequestParam("usernumber") String usernumber ,
                          HttpServletRequest request , Model model){
        String uname = (String) request.getSession().getAttribute("uname");
        int uid = userService.getUserByName(uname).getUid();
        Project project = new Project(uid ,pname);
        int pid = projectService.getPid(project);
        Project project1 = new Project(pid,pucard,usernumber);
        boolean isup = projectService.upCard(project1);
        System.out.println(isup);
        return "start-step-4";
    }
    //后台众筹审批页面
    @RequiresPermissions(value={"项目审核"})
    @RequestMapping(value = "/auth_project" , method = RequestMethod.GET)
    public String authproject(Model model){
        String zhuangtai = "审核中";
        List<Project>  projects= projectService.getPro(zhuangtai);
        model.addAttribute("projects",projects);
        return "auth_project";
    }
    //后台众筹----前往审批页面
    @RequiresPermissions(value={"项目审核-审批"})
    @RequestMapping(value = "/shenpipro" , method = RequestMethod.GET)
    public String shenpipro(@RequestParam("pid") int pid , Model model){
        Project project = projectService.getProById(pid);
        model.addAttribute("project",project);
        return "shenpipro";
    }
    //后台众筹----审批结果页面
    @RequiresPermissions(value={"项目审核"})
    @RequestMapping(value = "/shenpiproresult" , method = RequestMethod.POST)
    public String spResult(@RequestParam("pid") int pid ,
                           @RequestParam("zhuangtai")String zhuangtai ,
                           @RequestParam("pss")String pss ,
                           Model model){
        System.out.println(zhuangtai);
        if (zhuangtai.equals("通过")){
            zhuangtai = "众筹中";

        }else {
            zhuangtai = "审核未通过";
        }
        //添加当前时间作为项目开始时间
        Date date = new Date();

        String pname = projectService.getProById(pid).getPname();
        Project project = new Project(pid,pname,zhuangtai,pss);
        System.out.println(project);
        boolean isshenhe = projectService.shenHePro(project);
        System.out.println(isshenhe);
        zhuangtai = "审核中";
        List<Project>  projects= projectService.getPro(zhuangtai);
        model.addAttribute("projects",projects);
        return "auth_project";
    }
    @RequiresPermissions(value={"资质维护"})
    @RequestMapping(value = "/cert" , method = RequestMethod.GET)
    public String login1(){
        return "cert";
    }
    @RequiresPermissions(value={"分类管理"})
    @RequestMapping(value = "/type" , method = RequestMethod.GET)
    public String login2(){
        return "type";
    }
    @RequiresPermissions(value={"流程管理"})
    @RequestMapping(value = "/process" , method = RequestMethod.GET)
    public String login3(){
        return "process";
    }
    @RequiresPermissions(value={"广告管理"})
    @RequestMapping(value = "/advertisement" , method = RequestMethod.GET)
    public String login4(){
        return "advertisement";
    }
    @RequiresPermissions(value={"消息模板"})
    @RequestMapping(value = "/message" , method = RequestMethod.GET)
    public String login5(){
        return "message";
    }
    @RequiresPermissions(value={"项目分类"})
    @RequestMapping(value = "/project_type" , method = RequestMethod.GET)
    public String login6(){
        return "project_type";
    }
    @RequiresPermissions(value={"项目标签"})
    @RequestMapping(value = "/tag" , method = RequestMethod.GET)
    public String login7(){
        return "tag";
    }
    @RequiresPermissions(value={"参数管理"})
    @RequestMapping(value = "/param" , method = RequestMethod.GET)
    public String login8(){
        return "param";
    }
    @RequestMapping(value = "/project" , method = RequestMethod.GET)
    public String login9(){
        return "project";
    }
    @RequestMapping(value = "/projects" , method = RequestMethod.GET)
    public String login10(){
        return "projects";
    }
}
