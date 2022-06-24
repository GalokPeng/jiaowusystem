package com.qimozuoye.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qimozuoye.pojo.Teacher;
import com.qimozuoye.vo.StudentInfo;
import com.qimozuoye.vo.TeacherInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeacherMapper extends BaseMapper<Teacher> {

    //获取所有教师信息
    List<Teacher> getTeachers();

    //通过id获取所有教师信
    Teacher get(int id);

    //新增一个教师信息
//    void save(Teacher teacher);

    //通过name获取教师信息
    Teacher getByName(String name);

    //通过id删除教师
    int delete(int id);

    //通过id修改教师信息
    void update(Teacher teacher);

    Page<TeacherInfo> getTeacherAndSclass(Page<TeacherInfo> page, @Param("ew") QueryWrapper<TeacherInfo> queryWrapper);

}
