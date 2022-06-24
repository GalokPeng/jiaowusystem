package com.qimozuoye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qimozuoye.pojo.Sclass;
import com.qimozuoye.pojo.Teacher;

import java.util.Collection;
import java.util.List;

public interface SclassService extends IService<Sclass> {

    //获取全部班级信息
    List<Sclass> getSclasses();

    //通过ID获取班级信息
    Sclass get(int id);


    //新增一个班级信息
//    void save(Sclass sclass);

    //通过name获取班级信息
    Sclass getByName(String name);

    //通过id班级教师
    int delete(int id);

    //通过id修改班级信息
    void update(Sclass sclass);
}
