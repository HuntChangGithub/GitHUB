package com.hunt.j1902.controller;

import com.hunt.j1902.pojo.Role;
import com.hunt.j1902.pojo.Urole;
import com.hunt.j1902.pojo.User;
import com.hunt.j1902.service.RoleService;
import com.hunt.j1902.service.UroleService;
import com.hunt.j1902.service.UserService;
import com.hunt.j1902.vo.UserInfo;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by asus on 2019/6/18.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UroleService uroleService;
    @Autowired
    private RoleService roleService;
    @GetMapping("/index")
    public String index(){
        return "index";
    }
    //前往注册页面
    @GetMapping("/reg")
    public String add(){
        return "reg";
    }
    //前往登录页面
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    //用户注册
    @PostMapping("/adduser")
    public String addUser(@RequestParam("uname") String uname ,
                          @RequestParam("upw") String upw ,
                          @RequestParam("email") String email ,
                          @RequestParam("upower") String upower ,
                          Model model){
        String isrel = "未实名认证";
        Md5Hash md5Hash = new Md5Hash(upw,null,1024);
        String md5str = md5Hash.toString();
        User user = new User(uname , md5str , upower , email ,isrel);
        User user1 = userService.getUserByName(uname);
        if (user1 == null){
            boolean isAddUser = userService.addUser(user);
            User user2 = userService.getUserByName(uname);
            int uid = user2.getUid();
            int rid = 5;
            Urole urole = new Urole(uid,rid);
            boolean isAddUrole = uroleService.addUrole(urole);
            String string = "注册成功,请登录!";
            model.addAttribute("string",string);
        }else {
            String string = "该用户名已存在,请重新输入!";
            model.addAttribute("string",string);
        }
        return "login";
    }
    //用户登录
    @RequestMapping(value = "/dologin" , method = RequestMethod.POST)
    public String login(UserInfo userInfo , Model model , HttpSession session) {
        User user = userService.getUserByName(userInfo.getUname());
        System.out.println(user);
        System.out.println(userInfo);
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUname(), userInfo.getUpw());
            subject.login(token);
            if (user.getUpower().equals(userInfo.getUpower())) {
                if (subject.isAuthenticated()) {
                    if (userInfo.getUpower().equals("管理")) {
                        session.setAttribute("uname", userInfo.getUname());
                        return "main";
                    } else if (userInfo.getUpower().equals("会员")) {
                        session.setAttribute("uname", userInfo.getUname());
                        model.addAttribute("user", user);
                        return "member";
                    }
                }
            }else {
                String string = "请使用正确的用户信息登录";
                model.addAttribute("string", string);
            }

        } catch (AuthenticationException e) {
            String string = "请使用正确的用户信息登录";
            model.addAttribute("string", string);
        }
        return "login";
    }
    //@RequiresPermissions(value={"authc"})
    @GetMapping("/main")
    public String user( ){

        return "main";
    }
    //@RequiresPermissions(value={"authc"})
    @GetMapping("/member")
    public String member(HttpSession session , Model model){
        String uname = (String) session.getAttribute("uname");
        User user = userService.getUserByName(uname);
        model.addAttribute("user",user);
        return "member";
    }
    //后台用户维护
    @RequiresPermissions(value={"用户维护"})
    @GetMapping("/user")
    public String user(Model model){
        List<User> users = (List<User>) userService.getUsers();

        model.addAttribute("users",users);
        return "user";
    }
    //后台用户维护---修改页面
    @RequiresPermissions(value={"用户维护-修改"})
    @GetMapping("/edit")
    public String edit(@RequestParam("uid") int uid ,
                       Model model , HttpSession session){
        User user = userService.getUserById(uid);
        session.setAttribute("uid",uid);
        model.addAttribute("user",user);
        return "edit";
    }
    //后台用户维护---修改
    @RequiresPermissions(value={"用户维护"})
    @PostMapping("/update")
    public String update(@RequestParam("uname")String uname ,
                         @RequestParam("relname")String relname ,
                         @RequestParam("email")String email ,
                         Model model , HttpSession session ){
        int uid =(int)session.getAttribute("uid");
        User user = new User(uid,uname,relname,email);
        boolean isUp = userService.updateUser(user);

        return "redirect:user";
    }
    //后台用户维护---删除
    @RequiresPermissions(value={"用户维护"})
    @GetMapping("/delete")
    public String delete(@RequestParam("uid") int uid){
        int del = userService.delUserById(uid);
        return "redirect:user";
    }
    //后台用户维护---模糊查询
    @RequiresPermissions(value={"用户维护"})
    @RequestMapping(value = "/select" , method = RequestMethod.POST)
    public String select(@RequestParam("string") String string , Model model){

        List<User> users = userService.selectUser(string);
        model.addAttribute("users",users);
        return "user";
    }
    //后台用户维护---前往添加页面
    @RequiresPermissions(value={"用户维护-新增"})
    @RequestMapping(value = "/add" , method = RequestMethod.GET)
    public String login4(){
        return "add";
    }
    //后台用户维护---添加
    @RequiresPermissions(value={"用户维护"})
    @RequestMapping(value = "/adduser1" , method = RequestMethod.POST)
    public String addUser1(@RequestParam("uname")String uname ,
                           @RequestParam("relname")String relname ,
                           @RequestParam("email")String email ,
                           Model model){
        String isrel = "未实名认证";
        String upw = "123";
        Md5Hash md5Hash = new Md5Hash(upw,null,1024);
        String md5str = md5Hash.toString();
        String upower = "会员";
        User user = new User(uname , md5str , upower , email , relname , isrel);
        User user1 = userService.getUserByName(uname);
        if (uname != "" && email!=""){
            if (user1 == null){
                boolean isAddUser = userService.addUser1(user);
                User user2 = userService.getUserByName(uname);
                int uid = user2.getUid();
                int rid = 5;
                Urole urole = new Urole(uid,rid);
                boolean isAddUrole = uroleService.addUrole(urole);
                String string = "新增用户成功!";
                model.addAttribute("string",string);
            }else {
                String string = "新增用户失败!";
                model.addAttribute("string",string);
            }
        }else {
            String string = "请输入正确格式注册信息!";
            model.addAttribute("string",string);
        }
        return "redirect:user";
    }
    //后台用户维护---批量删除
    @RequestMapping(value = "/delUseritems" , method = RequestMethod.POST)
    public void delNum(@RequestParam("delitems") String delitems ,
                       HttpServletResponse response) throws IOException {
        String[] strings = delitems.split(",");
        List<Integer> list = new ArrayList<>();
        for (String s:strings ) {

            int uid = Integer.parseInt(s);
            int del = userService.delUserById(uid);
            list.add(del);
        }
        String string = null;
        PrintWriter writer = response.getWriter();
        for (Integer i: list ) {
            if (i != 1) {
                string = "批量删除不成功!";

            } else {
                string = "批量删除不成功!";
            }
        }
        writer.write(string);
        writer.close();
    }
    //前台用户实名注册
    @RequiresPermissions(value={"实名认证-账户类型"})
    @RequestMapping(value = "/accttype" , method = RequestMethod.GET)
    public String goAccttype(){
        return "accttype";
    }
    //前台用户实名注册
    @RequiresPermissions(value={"实名认证-实名信息"})
    @RequestMapping(value = "/apply" , method = RequestMethod.GET)
    public String toApply(@RequestParam("utype") String utype , Model model){

        model.addAttribute("utype",utype);
        return "apply";
    }
    //前台用户实名注册
    @RequiresPermissions(value={"实名认证-照片上传"})
    @RequestMapping(value = "/apply-1" , method = RequestMethod.POST)
    public String toApply1(@RequestParam("utype") String utype ,
                           @RequestParam("relname") String relname ,
                           @RequestParam("unumber") String unumber ,
                           @RequestParam("tel") String tel ,
                           Model model){
        model.addAttribute("utype",utype);
        model.addAttribute("relname",relname);
        model.addAttribute("unumber",unumber);
        model.addAttribute("tel",tel);
        return "apply-1";
    }
    //前台用户实名注册
    @RequiresPermissions(value={"实名认证-发送验证码"})
    @RequestMapping(value = "/upload.do" , method = RequestMethod.POST)
    public String toApply2(@RequestParam("file")MultipartFile mf ,
                           HttpServletRequest request ,
                           @RequestParam("utype") String utype ,
                           @RequestParam("relname") String relname ,
                           @RequestParam("unumber") String unumber ,
                           @RequestParam("tel") String tel ,
                           Model model){
        String path = new String("E:\\IdeaProjects\\day71_springboot\\src\\main\\resources\\static\\upload");
        String fileName = mf.getOriginalFilename();
        String location = path + "/"+fileName;

        File f = new File(location);
        try {
            f.createNewFile();
            mf.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("uimg",fileName);
        model.addAttribute("utype",utype);
        model.addAttribute("relname",relname);
        model.addAttribute("unumber",unumber);
        model.addAttribute("tel",tel);
        return "apply-2";
    }
    //前台用户实名注册----发送验证码
    @RequestMapping(value = "/sendyzm",method = RequestMethod.POST)
    @ResponseBody
    public int yxyz(HttpServletRequest request,@RequestParam(defaultValue ="a" ) String exam){
        String regEx1 ="^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";

        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(exam);
        if(m.matches()){
            try {
                HtmlEmail htmlEmail=new HtmlEmail();
                htmlEmail.setHostName("smtp.qq.com");
                htmlEmail.setCharset("utf-8");
                htmlEmail.addTo(exam);
                htmlEmail.setFrom("525022859@qq.com","众筹系统");
                htmlEmail.setAuthentication("525022859@qq.com","rcawqlszsvdkcade");
                htmlEmail.setSubject("实名认证验证码");
                int a=(int)((Math.random()*9+1)*100000);
                String aa=String.valueOf(a);
                HttpSession session=request.getSession();
                session.setAttribute("key",aa);
                htmlEmail.setMsg("尊贵的会员：您的验证码为"+"<h3>"+aa+"</h3>");
                htmlEmail.send();
            } catch (EmailException e) {
                e.printStackTrace();
            }
            return 200;
        }else {
            return 400;
        }
    }
    //前台用户实名注册
    @RequiresPermissions(value={"实名认证-校验验证码"})
    @RequestMapping(value = "/apply-3" , method = RequestMethod.POST)
    public String toApply3(@RequestParam("utype") String utype ,
                           @RequestParam("relname") String relname ,
                           @RequestParam("unumber") String unumber ,
                           @RequestParam("tel") String tel ,
                           @RequestParam("uimg") String uimg ,
                           @RequestParam("email") String email ,
                           Model model){

        model.addAttribute("utype",utype);
        model.addAttribute("relname",relname);
        model.addAttribute("unumber",unumber);
        model.addAttribute("tel",tel);
        model.addAttribute("uimg",uimg);
        model.addAttribute("email",email);
        return "apply-3";
    }
    //前台用户实名注册----验证码校验
    @RequestMapping(value = "/jiaoyan" , method = RequestMethod.POST)
    public String jiaoyan(@RequestParam("utype") String utype ,
                          @RequestParam("relname") String relname ,
                          @RequestParam("unumber") String unumber ,
                          @RequestParam("tel") String tel ,
                          @RequestParam("uimg") String uimg ,
                          @RequestParam("email") String email ,
                          @RequestParam("yzm") String yzm ,
                          @RequestParam("inputyzm") String inputyzm ,
                          HttpServletRequest request , Model model){
        int inputyz = Integer.parseInt(inputyzm);
        int yz = Integer.parseInt(yzm);
        String uname = (String)request.getSession().getAttribute("uname");
        User user = userService.getUserByName(uname);
        String isrel = "实名认证审核中";
        int uid = user.getUid();
        User user1 = new User(uid,unumber,uimg,utype,relname,tel,isrel);
        if ( inputyz == yz){
            boolean smrz = userService.upUser(user1);
            int rid = 6;
            Urole urole = new Urole(uid,rid);
            boolean isUpUrole = uroleService.upUroleById(urole);

            System.out.println(smrz);
            model.addAttribute("user",user);
            return "member";
        }else {
            model.addAttribute("utype",utype);
            model.addAttribute("relname",relname);
            model.addAttribute("unumber",unumber);
            model.addAttribute("tel",tel);
            model.addAttribute("uimg",uimg);
            model.addAttribute("email",email);
            return "apply-3";
        }
    }
    //后台业务审批----实名认证审核
    @RequiresPermissions(value={"实名认证审核"})
    @RequestMapping(value = "/auth_cert" , method = RequestMethod.GET)
    public String login9(Model model){
        String isrel = "实名认证审核中";
        List<User> users = userService.getUserByIsrel(isrel);
        model.addAttribute("users",users);

        return "auth_cert";
    }
    //后台业务审批----实名认证审核
    @RequiresPermissions(value={"实名认证审核-审批"})
    @RequestMapping(value = "/shenpi" , method = RequestMethod.GET)
    public String shenpi(@RequestParam("uid") int uid , Model model){
        User user = userService.getUserById(uid);
        model.addAttribute("user",user);
        return "shenpi";
    }
    //后台业务审批----实名认证审核详情
    @RequiresPermissions(value={"实名认证审核"})
    @RequestMapping(value = "/shenpiresult" , method = RequestMethod.POST)
    public String spResult(@RequestParam("uid") int uid ,
                           @RequestParam("isrel")String isrel ,
                           @RequestParam("ps")String ps ,
                           Model model){
        System.out.println(isrel);
        User user;
        int rid;
        if (isrel.equals("通过")){
            rid = 7;
            isrel = "已实名认证";

        }else {
            rid = 5;
            isrel = "未实名认证";
        }
        user = new User(uid,isrel,ps);
        boolean isrenzheng = userService.rzUpdate(user);
        Urole urole = new Urole(uid,rid);
        boolean isUpUrole = uroleService.upUroleById(urole);
        isrel = "实名认证审核中";
        List<User> users = userService.getUserByIsrel(isrel);
        model.addAttribute("users",users);
        return "auth_cert";
    }
    //后台业务审批----实名认证审核删除审核申请
    @RequestMapping(value = "/delShenpi" , method = RequestMethod.GET)
    public ResponseEntity<String> delShenpi(@RequestParam("uid") int uid){
        String isrel = "未实名认证";
        User user = new User(uid,isrel);
        //int uid, String isrel
        int isDelShenpi = userService.cleanShenPi(user);
        ResponseEntity<String> entity = null;
        if ( isDelShenpi == 1 ){
            entity = new ResponseEntity<String>(HttpStatus.OK);
        }else {
            entity = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        return entity;
    }

    @RequiresPermissions(value={"角色维护"})
    @RequestMapping(value = "/role" , method = RequestMethod.GET)
    public String login12( Model model){
        List<Role> roles = roleService.getRole();
        model.addAttribute("roles",roles);
        return "role";
    }


    @RequiresPermissions(value={"/assignPermission"})
    @RequestMapping(value = "assignPermission" , method = RequestMethod.GET)
    public String login11(@RequestParam("uid") int uid , Model model){
        Role role = roleService.getRoleByUid(uid);
        model.addAttribute("role",role);
        return "assignPermission";
    }
    @RequiresPermissions(value={"角色维护-分配许可"})
    @RequestMapping(value = "shenpirole" , method = RequestMethod.POST)
    public String login12(@RequestParam("uid") int uid ,
                          @RequestParam("upower")String upower ,
                          @RequestParam("rolename")String rolename ,
                          Model model){
        String isrel = null;
        if (rolename.equals("管理员-权限管理")){
            isrel = "管理员-权限管理";
        }else if (rolename.equals("管理员-业务审核")){
            isrel = "管理员-业务审核";
        }else if (rolename.equals("管理员-业务管理")){
            isrel = "管理员-业务管理";
        }else if (rolename.equals("会员未实名认证")){
            isrel = "未实名认证";
        }
        else if (rolename.equals("会员实名认证审核中")){
            isrel = "实名认证审核中";
        }
        else if (rolename.equals("会员已实名认证")){
            isrel = "已实名认证";
        }
        User user = new User();
        user.setUid(uid);
        user.setUpower(upower);
        user.setIsrel(isrel);
        boolean isUpUpower = userService.upUpowerByUid(user);
        int rid = roleService.getRidByRolename(rolename);
        Urole urole = new Urole(uid , rid);
        boolean isUpRidByUid = uroleService.upUroleById(urole);


        return "redirect:role";
    }

    @RequiresPermissions(value={"维护许可"})
    @RequestMapping(value = "/permission" , method = RequestMethod.GET)
    public String login8(){
        return "permission";
    }
    @RequiresPermissions(value={"广告审核"})
    @RequestMapping(value = "/auth_adv" , method = RequestMethod.GET)
    public String login10(){
        return "auth_adv";
    }


}
