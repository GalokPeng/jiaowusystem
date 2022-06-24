package com.qimozuoye.service.ipml;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qimozuoye.mapper.SclassMapper;
import com.qimozuoye.mapper.StudentMapper;
import com.qimozuoye.mapper.TeacherMapper;
import com.qimozuoye.pojo.Student;
import com.qimozuoye.pojo.Teacher;
import com.qimozuoye.service.StudentService;
import com.qimozuoye.service.TeacherService;
import com.qimozuoye.utils.RedisUtil;
import com.qimozuoye.vo.StudentInfo;
import com.qimozuoye.vo.TeacherInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper,Teacher> implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private SclassMapper sclassMapper;

    @Autowired
    private RedisUtil redisUtil;

    // 获取所有教师信息
    @Override
    public List<Teacher> getTeachers() {
        if (redisUtil.hasKey("teacs")){
            List<Teacher> teachers = (List) redisUtil.lGet("teacs", 0, -1);
            log.info("通过缓存获取教师所有信息:"+teachers);
            return teachers;
        }
        //通过数据库获取所有教师信息
        List<Teacher> teachers = teacherMapper.getTeachers();
        //插入缓存
        for (Teacher teacher : teachers){
            redisUtil.lSet("teacs",teacher);
        }
        log.info("通过数据库获取教师所有信息:"+teachers);
        return teachers;
    }

    // 新增一个教师
//    @Override
//    public void save(Teacher teacher) {
////        employee.setEDepartment(departmentMapper.getDepartment(employee.getDepartment()));
//        teacherMapper.save(teacher);
//        Teacher teac = getByName(teacher.getLastName());
//        System.out.println(teac);
//        redisUtil.lSet("teacs",teac);
//
//    }

    // 通过id获得教师信息
    @Override
    public Teacher get(int id) {
        return teacherMapper.get(id);
    }
    // 通过name获得教师信息
    @Override
    public Teacher getByName(String name){
        return teacherMapper.getByName(name);
    }

    // 通过id删除教师
    @Override
    public int delete(int id) {
        Teacher teacher = get(id);
        System.out.println(teacher);
        teacherMapper.delete(id);
        return (int) redisUtil.lRemove("teacs",0,teacher);
    }

    //通过id修改教师信息
    @Override
    public void update(Teacher teacher) {

        log.info("更新教师信息"+teacher);
        teacherMapper.update(teacher);
        Teacher teac = get(teacher.getId());
        List<Teacher> teachers = (List) redisUtil.lGet("teacs", 0, -1);
        int i = 0;
        for (Teacher teacs : teachers) {
            if (teac.getId()==teacs.getId()){
                redisUtil.lUpdateIndex("teacs",i,teac);
            }
            i++;
        }


    }

    @Override
    public Page<TeacherInfo> getTeacherAndSclass(Integer start, Integer size) {
        Page<TeacherInfo> page = new Page<>(start,size);
        QueryWrapper<TeacherInfo> wrapper = new QueryWrapper<>();
        teacherMapper.getTeacherAndSclass(page,wrapper);
        return page;
    }
}
