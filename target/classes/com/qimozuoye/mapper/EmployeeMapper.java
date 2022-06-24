package com.qimozuoye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qimozuoye.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {

    // 获取所有员工信息
    List<Employee> getEmployees();

    // 新增一个员工
//    void save(Employee employee);

    // 通过id获得员工信息
    Employee get(int id);

    // 通过name获得员工信息
    Employee getByName(String name);

    // 通过id删除员工
    int delete(int id);

    //通过id修改员工信息
    void update (Employee employee);



}
