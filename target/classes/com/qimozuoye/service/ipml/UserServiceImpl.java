package com.qimozuoye.service.ipml;


import com.qimozuoye.mapper.UserMapper;
import com.qimozuoye.pojo.GuestUser;
import com.qimozuoye.service.UserService;
import com.qimozuoye.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查询所有用户信息
     * @return
     */
    @Override
    public List<GuestUser> findAll() {
        if (redisUtil.hasKey("userList") ){
            List<GuestUser> userList = (List<GuestUser>) redisUtil.lGetIndex("userList",0);
            log.info("通过缓存获取用户所有信息："+userList);
            return userList;
        }
        //通过数据库获取用户所有信息
        List<GuestUser> userList = userMapper.findAll();
        //插入list缓存有效时间为10s
        redisUtil.lSet("userList",userList,30);
        log.info("通过数据库获取用户所有信息："+userList);
        return userList;
    }

    /**
     * 通过名字查询用户信息
     * @param username
     * @return
     */

    @Override
    public GuestUser findByName(String username) {
        return userMapper.findByName(username);
    }

    /**
     * 通过id查询用户信息
     * @param id
     * @return
     */

    @Override
    public GuestUser findById(int id) {
        return userMapper.findById(id);
    }


    /**
     * 注册添加用户
     * @param guestUser
     */

    @Override
    public void save(GuestUser guestUser) {
        userMapper.save(guestUser);
    }

    /**
     * 添加用户
     * @param guestUser
     */

    @Override
    public void adminAdd(GuestUser guestUser) {

        userMapper.adminAdd(guestUser);
    }

    /**
     * 更新用户信息
     * @param guestUser
     */

    @Override
    public void update(GuestUser guestUser) {

        userMapper.update(guestUser);

    }

    /**
     * 删除用户
     * @param id
     */

    @Override
    public void delete(int id) {
        userMapper.delete(id);
    }
}
