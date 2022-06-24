package com.qimozuoye.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {
    private int id;
    @NotBlank(message = "请输入名字")
    @TableField(value = "lastname")
    private String lastName;
    private String email;
    //1 male, 0 female
    private int gender;
    @TableField(value = "departmentid")
    private int department;
    private Date birth;

    @TableField(exist = false)
    private Department eDepartment;// 冗余设计

    public Employee(String lastName, String email, int gender, int department, Date birth) {
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.department = department;
        this.birth = birth;
    }
}
