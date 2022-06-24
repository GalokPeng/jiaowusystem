package com.qimozuoye.controller;

import com.qimozuoye.mapper.DepartmentMapper;
import com.qimozuoye.mapper.EmployeeMapper;
import com.qimozuoye.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeMapper employeeMapper;

    // 查询全部部门
    @GetMapping("/getDepartments")
    public List<Department> getDepartments(){
        return (List<Department>) departmentMapper.getDepartments();
    }

    // 查询全部部门
    @GetMapping("/getDepartment/{id}")
    public Department getDepartment(@PathVariable("id") Integer id){
        return departmentMapper.getDepartment(id);
    }
    //通过id删除部门
    @GetMapping("/delete/{id}")
    public int delete(@PathVariable("id") int id){
        return employeeMapper.delete(id);
    }

}