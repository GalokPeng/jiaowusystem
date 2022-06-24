package com.qimozuoye.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qimozuoye.pojo.Student;
import com.qimozuoye.vo.StudentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper extends BaseMapper<Student> {

    // 获取所有学生信息
    List<Student> getStudents();

    // 新增一个学生
//    void save(Student student);

    // 通过id获得学生信息
    Student get(int id);

    // 通过name获得学生信息
    Student getByName(String name);

    // 通过id删除学生
    int delete(int id);

    //通过id修改学生信息
    void update (Student student);

    Page<StudentInfo> getStudentAndSclass(Page<StudentInfo> page, @Param("ew") QueryWrapper<StudentInfo> queryWrapper);
}
