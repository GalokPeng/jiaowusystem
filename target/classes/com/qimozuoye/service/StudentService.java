package com.qimozuoye.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qimozuoye.pojo.Student;
import com.qimozuoye.vo.StudentInfo;

import java.util.List;

public interface StudentService extends IService<Student> {


    // 获取所有员工信息
    List<Student> getStudents();

    // 新增一个员工
//    void save(Student student);

    // 通过id获得员工信息
    Student get(int id);

    // 通过name获得员工信息
    Student getByName(String name);

    // 通过id删除员工
    int delete(int id);

    //通过id修改员工信息
    void update (Student student);

    Page<StudentInfo> getStudentAndSclass(Integer start,Integer size);

}
