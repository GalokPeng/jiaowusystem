package com.qimozuoye.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Courses implements Serializable {

/**
 * @program: qimozuoye
 *
 * @description: 课程
 *
 * @author: Galok
 *
 * @create: 2022-01-02 13:28
 **/

    private int id;
    private String course;
    private String teacher;
    private String credit;
    private int type;
}
