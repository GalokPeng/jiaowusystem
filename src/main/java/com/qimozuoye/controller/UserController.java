package com.qimozuoye.controller;

import com.qimozuoye.pojo.GuestUser;
import com.qimozuoye.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.Collection;

@Controller
public class UserController {
//    @Autowired
//    UserMapper userMapper;

    @Resource
    private UserService userService;


    @GetMapping("/users/personalcenter")
    public String personalcenter(){
        return "/users/personalcenter";
    }

    @GetMapping("/users/usermanagement")
    public String toUsermanagement(Model model){
//        Collection<GuestUser> guestUserList = userMapper.findAll();
        Collection<GuestUser> guestUserList = userService.findAll();
        model.addAttribute("guser",guestUserList);
        return "/users/usermanagement";
    }
    @GetMapping("/user")
    public String toAdd(Model model){
        return "/users/adduser";
    }
    //添加用户
    @PostMapping("/user")
    public String addUser(GuestUser guestUser){
        System.out.println("add=>"+guestUser);
        userService.adminAdd(guestUser);
        return"redirect:/users/usermanagement";
    }

    @GetMapping("/user/{id}")
    public String toUpdateUser(@PathVariable("id") int id ,Model model){
        GuestUser user = userService.findById(id);
        model.addAttribute("guser",user);
        return "/users/updateuser";
    }
    //修改用户
    @PostMapping("/updateuser")
    public String updateUser(GuestUser guestUser){
        System.out.println("updateuser=>"+guestUser);
        userService.update(guestUser);
        return "redirect:/users/usermanagement";
    }

    //删除用户
    @GetMapping("/deleuser/{id}")
    public String toDelete(@PathVariable("id") int id){
        userService.delete(id);
        return "redirect:/users/usermanagement";
    }
}
