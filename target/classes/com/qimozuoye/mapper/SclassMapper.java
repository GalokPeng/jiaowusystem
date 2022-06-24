package com.qimozuoye.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qimozuoye.pojo.Sclass;
import com.qimozuoye.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Mapper
@Repository
public interface SclassMapper extends BaseMapper<Sclass> {
    // 获取所有班级信息
    List<Sclass> getSclasses();

    // 通过id获得班级
    Sclass get(int id);

    //新增一个班级信息
//    void save(Sclass sclass);

    //通过name获取班级信息
    Sclass getByName(String name);

    //通过id班级
    int delete(int id);

    //通过id修改班级信息
    void update(Sclass sclass);

}
