package com.qimozuoye.service.ipml;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qimozuoye.mapper.CoursesMapper;
import com.qimozuoye.pojo.Courses;
import com.qimozuoye.service.CoursesService;
import com.qimozuoye.utils.RedisUtil;
import com.qimozuoye.vo.StudentInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
@Slf4j
public class CoursesServiceImpl extends ServiceImpl<CoursesMapper,Courses> implements CoursesService {

/**
 * @program: qimozuoye
 *
 * @description: 1
 *
 * @author: Galok
 *
 * @create: 2022-01-02 13:59
 **/

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private CoursesService coursesService;

    // 获取所有班级信息
    @Override
    public List<Courses> list(){
        if (redisUtil.hasKey("cours")){
            List<Courses> courses = (List) redisUtil.lGet("cours", 0, -1);
            log.info("通过缓存获取班级所有信息:"+courses);
            return courses;
        }
        //通过数据库获取所有教师信息
        List<Courses> courses = coursesService.list();
        //插入缓存
        for (Courses courses1 : courses){
            redisUtil.lSet("clas",courses1);
        }
        log.info("通过数据库获取班级所有信息:"+courses);
        return courses;
    }

    // 通过id获得教师信息
    @Override
    public Courses get(Serializable id) {
        return coursesService.getById(id);
    }
    // 通过name获得教师信息

    // 通过id删除教师
    @Override
    public boolean remove(Serializable id) {
        Courses courses = getById(id);
        System.out.println(courses);
        coursesService.removeById(id);
        redisUtil.lRemove("cours",0,courses);
        return false;
    }

    //通过id修改教师信息
    @Override
    public boolean updateById(Courses courses) {

        log.info("更新班级信息"+courses);
        coursesService.updateById(courses);
        Courses cour = getById(courses.getId());
        List<Courses> courses1 = (List) redisUtil.lGet("clas", 0, -1);
        int i = 0;
        for (Courses cours : courses1) {
            if (cour.getId()==cours.getId()){
                redisUtil.lUpdateIndex("cours",i,cour);
            }
            i++;
        }

        return false;
    }

}
