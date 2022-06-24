package com.qimozuoye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qimozuoye.pojo.Courses;

import java.io.Serializable;

public interface CoursesService extends IService<Courses> {
    // 通过id获得教师信息
    Courses get(Serializable id);

    // 通过id删除教师
    boolean remove(Serializable id);

/**
 * @program: qimozuoye
 *
 * @description: 1
 *
 * @author: Galok
 *
 * @create: 2022-01-02 14:02
 **/


}
