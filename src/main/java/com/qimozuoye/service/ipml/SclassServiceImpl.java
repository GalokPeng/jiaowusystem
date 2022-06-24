package com.qimozuoye.service.ipml;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qimozuoye.mapper.SclassMapper;

import com.qimozuoye.pojo.Sclass;
import com.qimozuoye.pojo.Teacher;
import com.qimozuoye.service.SclassService;

import com.qimozuoye.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class SclassServiceImpl extends ServiceImpl<SclassMapper,Sclass> implements SclassService {

    @Autowired
    private SclassMapper sclassMapper;

    @Autowired
    private RedisUtil redisUtil;

    // 获取所有班级信息
    @Override
    public List<Sclass> getSclasses() {
        if (redisUtil.hasKey("clas")){
            List<Sclass> sclasses = (List) redisUtil.lGet("clas", 0, -1);
            log.info("通过缓存获取班级所有信息:"+sclasses);
            return sclasses;
        }
        //通过数据库获取所有教师信息
        List<Sclass> sclasses = sclassMapper.getSclasses();
        //插入缓存
        for (Sclass sclass : sclasses){
            redisUtil.lSet("clas",sclass);
        }
        log.info("通过数据库获取班级所有信息:"+sclasses);
        return sclasses;
    }

    // 新增一个教师
//    @Override
//    public void save(Sclass sclass) {
////        employee.setEDepartment(departmentMapper.getDepartment(employee.getDepartment()));
//        sclassMapper.save(sclass);
//        Sclass cla = getByName(sclass.getSclassName());
//        System.out.println(cla);
//        redisUtil.lSet("teacs",cla);
//
//    }

    // 通过id获得教师信息
    @Override
    public Sclass get(int id) {
        return sclassMapper.get(id);
    }
    // 通过name获得教师信息
    @Override
    public Sclass getByName(String name){
        return sclassMapper.getByName(name);
    }

    // 通过id删除教师
    @Override
    public int delete(int id) {
        Sclass sclass = get(id);
        System.out.println(sclass);
        sclassMapper.delete(id);
        return (int) redisUtil.lRemove("clas",0,sclass);
    }

    //通过id修改教师信息
    @Override
    public void update(Sclass sclass) {

        log.info("更新班级信息"+sclass);
        sclassMapper.update(sclass);
        Sclass cla = get(sclass.getSclassid());
        List<Sclass> sclasses = (List) redisUtil.lGet("clas", 0, -1);
        int i = 0;
        for (Sclass clas : sclasses) {
            if (cla.getSclassid()==clas.getSclassid()){
                redisUtil.lUpdateIndex("clas",i,cla);
            }
            i++;
        }


    }
}
