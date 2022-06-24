package com.qimozuoye.vo;

import com.qimozuoye.pojo.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentInfo extends Student {
    private String sclassName;
}
