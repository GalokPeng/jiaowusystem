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
public class Teacher implements Serializable {
    private int id;
    @NotBlank(message = "请输入名字")
    @TableField(value = "lastname")
    private String lastName;
    private String identity;
    //1 male, 0 female
    private int gender;
    @TableField(value = "sclassid")
    private int sclass;
    private String phone;
    private String education;
    private String position;
    private Date entrance;
    @TableField(exist = false)
    private Sclass sSclass;// 冗余设计

    public Teacher(String lastName, String identity, int gender, int sclass, String phone, String education,String position,Date entrance) {
        this.lastName = lastName;
        this.identity = identity;
        this.gender = gender;
        this.sclass = sclass;
        this.phone = phone;
        this.education = education;
        this.position = position;
        this.entrance = entrance;
    }
}
