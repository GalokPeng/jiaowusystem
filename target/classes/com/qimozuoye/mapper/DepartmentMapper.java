package com.qimozuoye.mapper;


import com.qimozuoye.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Mapper
@Repository
public interface DepartmentMapper {
    // 获取所有部门信息
    Collection<Department> getDepartments();

    // 通过id获得部门
    Department getDepartment(Integer id);

}
