package com.qimozuoye.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qimozuoye.mapper.SclassMapper;
import com.qimozuoye.mapper.StudentMapper;
import com.qimozuoye.pojo.Sclass;
import com.qimozuoye.pojo.Student;
import com.qimozuoye.service.StudentService;
import com.qimozuoye.vo.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class StudentController {
    //自动导入依赖的bean
    @Autowired
    StudentMapper studentMapper;

    @Autowired
    StudentService studentService;

    @Autowired
    SclassMapper sclassMapper;
    @RequestMapping("/stus")
    public String list(@RequestParam(value = "pg",defaultValue = "1")Integer pg,Model model){

//        Page<Student> studentPage = new Page<>(pg,5);
//        Page<Student> page = studentService.page(studentPage,null);
//        model.addAttribute("page",page);
//
//        List<Sclass> sclass = sclassMapper.get();
//        model.addAttribute("clas", sclass);


        Page<StudentInfo> page = studentService.getStudentAndSclass(pg,5);
        model.addAttribute("page",page);

        return "stu/stulist";
    }

//    @RequestMapping("/stus")
//    public String list(Model model) {
//        Collection<Student> students = studentService.getStudents();
//        model.addAttribute("stus", students);
//        return "stu/stulist";
//    }


    @GetMapping("/stu")
    public String toAddpage(Model model) {
        Collection<Sclass> sclasses = sclassMapper.getSclasses();
        model.addAttribute("sclasses", sclasses);
        return "stu/add";
    }

    //添加学生
    @PostMapping("/stu")
    public String addStu(Student student) {
        //添加学生
        studentService.save(student);
        return "redirect:/stus";
    }

    @GetMapping("/stu/{id}")
    public String toUpdateStu(@PathVariable("id") int id, Model model) {
        Student student = studentService.get(id);
        model.addAttribute("stu", student);
        Collection<Sclass> sclasses = sclassMapper.getSclasses();
        model.addAttribute("sclasses", sclasses);
        return "stu/update";
    }

    //修改学生
    @PostMapping("/updateStu")
    public String updateStu(@Valid Student student, Errors errors) {
        if (errors.hasErrors()){
            return "/stu";
        }
        System.out.println("update=>" + student);
        studentService.update(student);
        return "redirect:/stus";
    }

    //删除学生
    @GetMapping("/delstu/{id}")
    public String delete(@PathVariable("id") int id){
        studentService.delete(id);
        return "redirect:/stus";
    }


    //查询学生
    @PostMapping("/selestu")
    public String toSeleStu(int id, Model model) {

//        Student student1 =null;
        try{
            Student student = studentService.get(id);
            if (student == null){
                System.out.println(student);
                return "stu/selestu";
            }else {
//                System.out.println(student);
                model.addAttribute("selestu", student);
                Collection<Sclass> sclasses = sclassMapper.getSclasses();
                model.addAttribute("sclasses", sclasses);
                studentService.get(id);
                return "stu/selestu";
            }
        }catch (IllegalStateException e){
            return "stu/selestu";

        }

    }

    //查询学生
//    @PostMapping("/getid")
//    public String getid(int id) {
//        //添加学生
//        studentService.get(id);
//        return "redirect:/stus";
//    }

    /**
    * @Description: 判断毕业
    * @Param:
    * @return:
    * @Author: galok
    * @Date: 2022/1/2
    */

    @GetMapping("/grad")
    public String Grad(int id,Model model){
        Student student = studentMapper.get(id);
        model.addAttribute("grad",student);
        Date nowdate = format(new Date());
        Date todate = format(student.getEntrance());
        if ((Integer.valueOf(nowdate.toString())- Integer.valueOf(todate.toString())) >= 40000){
            return "是";
        }else
            return "否";


    }

    private Date format(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date newdate = format(date);
        return newdate;
    }

}
