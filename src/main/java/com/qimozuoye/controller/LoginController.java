package com.qimozuoye.controller;

import com.qimozuoye.pojo.GuestUser;
import com.qimozuoye.pojo.Student;
import com.qimozuoye.pojo.Teacher;
import com.qimozuoye.service.StudentService;
import com.qimozuoye.service.TeacherService;
import com.qimozuoye.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.plugin.liveconnect.SecurityContextHelper;

import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

@Controller
public class LoginController {
//    @Autowired
//    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    /*
    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password ,
                        Model model,
                        HttpSession session){
        if (!StringUtils.isEmpty(username)&&"123456".equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/dashboard.html";
        }else {
            model.addAttribute("msg","用户名或密码错误");
            return "sign";
        }
    }*/
    @RequestMapping("/user/login")
    public String login(String username, String password, Model model,HttpSession session) {
        /**
         * 使用Shiro编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        System.out.println(username + password);
        GuestUser guestUser = userService.findByName(username);
        model.addAttribute("users", guestUser);
        session.setAttribute("user",guestUser);
        //3.执行登录方法
        try {
            subject.login(token);
            //登录成功
            //跳转到dashboard.html
            //redirect代表重定向请求路径
            return "redirect:/dashboard.html";
        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            //登录失败:用户名不存在
            model.addAttribute("msg", "用户不存在");
            return "sign";
        } catch (IncorrectCredentialsException e) {
            //e.printStackTrace();
            //登录失败:密码错误
            model.addAttribute("msg", "密码错误");
            return "sign";
        }
    }

    //注销
    @RequestMapping("/user/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/sign.html";
    }

    //注册页面
    @RequestMapping("/register")
    public String register() {
        return "register.html";
    }

    //登录页面
    @RequestMapping("/login")
    public String login() {
        return "sign.html";
    }
//    public String register( String username,String password , Model model){
//        /**
//         * 使用Shiro编写认证操作
//         */
//        //1.获取Subject
//        Subject subject = SecurityUtils.getSubject();
//        //2.封装用户数据
//        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
////        System.out.println(username+password);
//        GuestUser guestUser = userService.findByName(username);
//        model.addAttribute("users",guestUser);
//        guestUser.getUsername();
//        //3.执行登录方法
//        try {
//            if (guestUser == null){
//
//            }
//            return "redirect:/dashboard.html";
//        } catch (UnknownAccountException e) {
//            //e.printStackTrace();
//            //登录失败:用户名不存在
//
//            return "sign";
//        }catch (IncorrectCredentialsException e) {
//            //e.printStackTrace();
//            //登录失败:密码错误
//            model.addAttribute("msg", "密码错误");
//            return "sign";
//        }
//    }

    //添加用户
    @PostMapping("/register")
//    public String addreg(GuestUser guestUser) {
//
//        System.out.println("save=>" + guestUser);
//        //添加用户
//        userService.save(guestUser);
//        return "redirect:/sign.html";
//    }
    public String addreg(GuestUser guestUser,String username, Model model) {
        GuestUser guestUser1 = userService.findByName(username);
        model.addAttribute("users", guestUser);
//        guestUser.getUsername();
        try {
            if (guestUser1 == null) {
                userService.save(guestUser);
                model.addAttribute("msg", "注册成功");
//                TimeUnit.SECONDS.sleep(3);//秒
//                return "<script language='javascript'>alert('注册成功!');redirect:/sign.html";
            }else {
                model.addAttribute("msg", "账户已经存在！请重新输入！");
            }
        }catch (UnknownAccountException e){
            model.addAttribute("msg", "未知错误！");
            return "register";
        }
        return "register";
    }

    //未授权
    @RequestMapping("/error/noAuth")
    public String noAuth() {
        return "/error/noAuth.html";
    }
}
