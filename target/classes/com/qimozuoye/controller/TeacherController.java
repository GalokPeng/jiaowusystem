package com.qimozuoye.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qimozuoye.mapper.DepartmentMapper;
import com.qimozuoye.mapper.EmployeeMapper;
import com.qimozuoye.mapper.SclassMapper;
import com.qimozuoye.mapper.TeacherMapper;
import com.qimozuoye.pojo.*;
import com.qimozuoye.service.EmployyeeService;
import com.qimozuoye.service.TeacherService;
import com.qimozuoye.vo.StudentInfo;
import com.qimozuoye.vo.TeacherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
public class TeacherController {
    //自动导入依赖的bean
    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    TeacherService teacherService;

    @Autowired
    SclassMapper sclassMapper;

    @RequestMapping("/teacs")
    public String list(@RequestParam(value = "pg",defaultValue = "1")Integer pg, Model model){
//
//        Page<Teacher> teacherPage = new Page<>(pg,5);
//        Page<Teacher> page = teacherService.page(teacherPage,null);
//        model.addAttribute("page",page);

        Page<TeacherInfo> page = teacherService.getTeacherAndSclass(pg,5);
        model.addAttribute("page",page);

        return "teac/teaclist";
    }
//    @RequestMapping("/teacs")
//    public String list(Model model) {
//        Collection<Teacher> teachers = teacherService.getTeachers();
//        model.addAttribute("teacs", teachers);
//        return "teac/teaclist";
//    }


    @GetMapping("/teac")
    public String toAddpage(Model model) {
        Collection<Sclass> sclasses = sclassMapper.getSclasses();
        model.addAttribute("sclasses", sclasses);
        return "teac/add";
    }

    //添加员工
    @PostMapping("/teac")
    public String addTeac(Teacher teacher) {
        //添加员工
        teacherService.save(teacher);
        return "redirect:/teacs";
    }

    @GetMapping("/teac/{id}")
    public String toUpdateTeac(@PathVariable("id") int id, Model model) {
        Teacher teacher = teacherService.get(id);
        model.addAttribute("teac", teacher);
        Collection<Sclass> sclasses = sclassMapper.getSclasses();
        model.addAttribute("sclasses", sclasses);
        return "teac/update";
    }

    //修改员工
    @PostMapping("/updateTeac")
    public String updateTeac(@Valid Teacher teacher, Errors errors) {
        if (errors.hasErrors()){
            return "/teac";
        }
        System.out.println("update=>" + teacher);
        teacherService.update(teacher);
        return "redirect:/teacs";
    }

    //删除员工
    @GetMapping("/delteac/{id}")
    public String delete(@PathVariable("id") int id){
        teacherService.delete(id);
        return "redirect:/teacs";
    }

    //查询学生
    @PostMapping("/seleteac")
    public String toSeleTeac(int id, Model model) {

        Teacher teacher = teacherService.get(id);
        try{
            if (teacher == null){
                System.out.println(teacher);
                return "teac/seleteac";
            }else {
//                System.out.println(teacher);
                model.addAttribute("seleteac", teacher);
                Collection<Sclass> sclasses = sclassMapper.getSclasses();
                model.addAttribute("sclasses", sclasses);
                teacherService.get(id);
                return "teac/seleteac";
            }
        }catch (IllegalStateException e){
            return "teac/seleteac";

        }

    }




}
