package com.qimozuoye.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sclasses")
public class Sclass implements Serializable {
    @TableField(value = "sclassid")
    private int sclassid;
//    @NotBlank(message = "请输入用户名！")
    @TableField(value = "sclassname")
    private String sclassName;
    private String college;
//    private Student sstudent;

    public Sclass(String sclassName, String college) {
//        this.sclassid = sclassid;
        this.sclassName = sclassName;
        this.college = college;
    }


}
