package com.qimozuoye.service.ipml;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qimozuoye.mapper.DepartmentMapper;
import com.qimozuoye.mapper.EmployeeMapper;
import com.qimozuoye.pojo.Employee;
import com.qimozuoye.service.EmployyeeService;
import com.qimozuoye.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployyeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployyeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private RedisUtil redisUtil;

    // 获取所有员工信息
    @Override
    public List<Employee> getEmployees() {
        if (redisUtil.hasKey("emps")){
            List<Employee> employees = (List) redisUtil.lGet("emps", 0, -1);
            log.info("通过缓存获取员工所有信息:"+employees);
            return employees;
        }
        //通过数据库获取所有员工信息
        List<Employee> employees = employeeMapper.getEmployees();
        //插入缓存
        for (Employee employee : employees){
            redisUtil.lSet("emps",employee);
        }
        log.info("通过数据库获取员工所有信息:"+employees);
        return employees;
    }

    // 新增一个员工
//    @Override
//    public void save(Employee employee) {
////        employee.setEDepartment(departmentMapper.getDepartment(employee.getDepartment()));
//        employeeMapper.save(employee);
//        Employee emp = getByName(employee.getLastName());
//        System.out.println(emp);
//        redisUtil.lSet("emps",emp);
//
//    }

    // 通过id获得员工信息
    @Override
    public Employee get(int id) {
        return employeeMapper.get(id);
    }
    // 通过name获得员工信息
    @Override
    public Employee getByName(String name){
        return employeeMapper.getByName(name);
    }

    // 通过id删除员工
    @Override
    public int delete(int id) {
        Employee employee = get(id);
        System.out.println(employee);
        employeeMapper.delete(id);
        return (int) redisUtil.lRemove("emps",0,employee);
    }

    //通过id修改员工信息
    @Override
    public void update(Employee employee) {


        log.info("更新员工信息"+employee);
        employeeMapper.update(employee);
        Employee emp = get(employee.getId());
        List<Employee> employees = (List) redisUtil.lGet("emps", 0, -1);
        int i = 0;
        for (Employee emps : employees) {
            if (emp.getId()==emps.getId()){
                redisUtil.lUpdateIndex("emps",i,emp);
            }
            i++;
        }


    }
}
