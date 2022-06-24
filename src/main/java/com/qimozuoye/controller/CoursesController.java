package com.qimozuoye.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qimozuoye.mapper.CoursesMapper;
import com.qimozuoye.pojo.Courses;
import com.qimozuoye.pojo.Sclass;
import com.qimozuoye.service.CoursesService;
import com.qimozuoye.service.ipml.CoursesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class CoursesController {

/**
 * @program: qimozuoye
 *
 * @description: 1
 *
 * @author: Galok
 *
 * @create: 2022-01-02 13:48
 **/

    @Autowired
    private CoursesService coursesService;

    @RequestMapping("/cours")
    public String list(@RequestParam(value = "pg",defaultValue = "1")Integer pg, Model model){

        Page<Courses> coursesPage = new Page<>(pg,5);
        Page<Courses> page = coursesService.page(coursesPage,null);
        model.addAttribute("page",page);
        return "cour/courlist";
    }

//    @GetMapping("/cours")
//    public String toClas(Model model){
////        Collection<GuestUser> guestUserList = userMapper.findAll();
//        Collection<Courses> courses = coursesService.list();
//        model.addAttribute("cours",courses);
//        return "cour/courlist";
//    }

    @GetMapping("/cour")
    public String toAdd(Model model){
        return "cour/add";
    }
    //添加用户
    @PostMapping("/cour")
    public String addCla(Courses courses){
        System.out.println("add=>"+courses);
        coursesService.save(courses);
        return"redirect:/cours";
    }

    @GetMapping("/cour/{id}")
    public String toUpdateCour(@PathVariable("id") int id ,Model model){
        Courses courses = coursesService.getById(id);
        model.addAttribute("cour",courses);
        return "cour/update";
    }
    //修改用户
    @PostMapping("/updateCour")
    public String updateCour(Courses courses){
        System.out.println("updatecla=>"+courses);
        coursesService.updateById(courses);
        return "redirect:/cours";
    }

    //删除用户
    @GetMapping("/delecour/{id}")
    public String toDelete(@PathVariable("id") int id){
        coursesService.removeById(id);
        return "redirect:/cours";
    }


    //查询班级
    @PostMapping("/seleCour")
    public String toSeleCla(int id, Model model) {


//        Student student1 =null;
        try{
            Courses courses = coursesService.getById(id);
            if (courses == null){
                System.out.println(courses);
                return "cour/selecour";
            }else {
//                System.out.println(student);
                model.addAttribute("selecour", courses);
                coursesService.getById(id);
                return "cour/selecour";
            }
        }catch (IllegalStateException e){
            return "cour/selecour";

        }

    }

}
