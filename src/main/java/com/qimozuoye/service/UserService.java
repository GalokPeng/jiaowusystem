package com.qimozuoye.service;

import com.qimozuoye.pojo.GuestUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    //查询所以用户信息
    List<GuestUser> findAll();
    //通过用户名查询
    GuestUser findByName(@Param("username") String username);
    //通过id查询
    GuestUser findById(@Param("id") int id);
    //注册添加用户
    void save(GuestUser guestUser);
    //admin添加用户
    void adminAdd(GuestUser guestUser);
    //通过id修改用户
    void update(GuestUser guestUser);
    //通过id删除用户
    void delete(int id);


}
