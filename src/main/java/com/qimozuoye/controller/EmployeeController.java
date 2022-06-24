package com.qimozuoye.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qimozuoye.mapper.DepartmentMapper;
import com.qimozuoye.mapper.EmployeeMapper;
import com.qimozuoye.pojo.Department;
import com.qimozuoye.pojo.Employee;
import com.qimozuoye.pojo.Sclass;
import com.qimozuoye.service.EmployyeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
public class EmployeeController {
    //自动导入依赖的bean
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    EmployyeeService employyeeService;

    @Autowired
    DepartmentMapper departmentMapper;
    @RequestMapping("/emps")
    public String list(@RequestParam(value = "pg",defaultValue = "1")Integer pg, Model model) {
//        Collection<Employee> employees = employyeeService.getEmployees();
//        model.addAttribute("emps", employees);

        Page<Employee> employeePage = new Page<>(pg,5);
        Page<Employee> page = employyeeService.page(employeePage,null);
        model.addAttribute("page", page);

        Collection<Department> departments = departmentMapper.getDepartments();
        model.addAttribute("dep", departments);

        return "emp/emplist";
    }


    @GetMapping("/emp")
    public String toAddpage(Model model) {
        Collection<Department> departments = departmentMapper.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/add";
    }

    //添加员工
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        //添加员工
        employyeeService.save(employee);
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") int id, Model model) {
        Employee employee = employyeeService.get(id);
        model.addAttribute("emp", employee);
        Collection<Department> departments = departmentMapper.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/update";
    }

    //修改员工
    @PostMapping("/updateEmp")
    public String updateEmp(@Valid Employee employee, Errors errors) {
        if (errors.hasErrors()){
            return "/emp";
        }
        System.out.println("update=>" + employee);
        employyeeService.update(employee);
        return "redirect:/emps";
    }

    //删除员工
    @GetMapping("/delemp/{id}")
    public String delete(@PathVariable("id") int id){
        employyeeService.delete(id);
        return "redirect:/emps";
    }




}
