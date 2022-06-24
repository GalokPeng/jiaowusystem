package com.qimozuoye.vo;

import com.qimozuoye.pojo.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeacherInfo extends Teacher {
    private String sclassName;
}
