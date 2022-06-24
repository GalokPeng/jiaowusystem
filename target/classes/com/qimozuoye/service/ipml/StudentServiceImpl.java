package com.qimozuoye.service.ipml;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qimozuoye.mapper.SclassMapper;
import com.qimozuoye.mapper.StudentMapper;
import com.qimozuoye.pojo.Student;
import com.qimozuoye.service.StudentService;
import com.qimozuoye.utils.RedisUtil;
import com.qimozuoye.vo.StudentInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper,Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private SclassMapper sclassMapper;

    @Autowired
    private RedisUtil redisUtil;

    // 获取所有员工信息
    @Override
    public List<Student> getStudents() {
        if (redisUtil.hasKey("stus")){
            List<Student> students = (List) redisUtil.lGet("stus", 0, -1);
            log.info("通过缓存获取员工所有信息:"+students);
            return students;
        }
        //通过数据库获取所有员工信息
        List<Student> students = studentMapper.getStudents();
        //插入缓存
        for (Student student : students){
            redisUtil.lSet("stus",student);
        }
        log.info("通过数据库获取员工所有信息:"+students);
        return students;
    }

//    // 新增一个员工
//    @Override
//    public void save(Student student) {
////        employee.setEDepartment(departmentMapper.getDepartment(employee.getDepartment()));
//        studentMapper.save(student);
//        Student stu = getByName(student.getLastName());
//        System.out.println(stu);
//        redisUtil.lSet("stus",stu);
//
//    }

    // 通过id获得员工信息
    @Override
    public Student get(int id) {
        return studentMapper.get(id);
    }
    // 通过name获得员工信息
    @Override
    public Student getByName(String name){
        return studentMapper.getByName(name);
    }

    // 通过id删除员工
    @Override
    public int delete(int id) {
        Student student = get(id);
        System.out.println(student);
        studentMapper.delete(id);
        return (int) redisUtil.lRemove("stus",0,student);
    }

    //通过id修改员工信息
    @Override
    public void update(Student student) {


        log.info("更新员工信息"+student);
        studentMapper.update(student);
        Student stu = get(student.getId());
        List<Student> students = (List) redisUtil.lGet("stus", 0, -1);
        int i = 0;
        for (Student stus : students) {
            if (stu.getId()==stus.getId()){
                redisUtil.lUpdateIndex("stus",i,stu);
            }
            i++;
        }
    }

    @Override
    public Page<StudentInfo> getStudentAndSclass(Integer start, Integer size) {
        Page<StudentInfo> page = new Page<>(start,size);
        QueryWrapper<StudentInfo> wrapper = new QueryWrapper<>();
        studentMapper.getStudentAndSclass(page,wrapper);
        return page;
    }
}
