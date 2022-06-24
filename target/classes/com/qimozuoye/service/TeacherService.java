package com.qimozuoye.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qimozuoye.pojo.Teacher;
import com.qimozuoye.vo.TeacherInfo;

import java.util.List;

public interface TeacherService extends IService<Teacher> {

    // 获取所有员工信息
    List<Teacher> getTeachers();

    // 新增一个员工
//    void save(Teacher teacher);

    // 通过id获得员工信息
    Teacher get(int id);

    // 通过name获得员工信息
    Teacher getByName(String name);

    // 通过id删除员工
    int delete(int id);

    //通过id修改员工信息
    void update (Teacher teacher);

    Page<TeacherInfo> getTeacherAndSclass(Integer start,Integer size);
}
