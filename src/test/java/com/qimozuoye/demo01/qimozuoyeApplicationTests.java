package com.qimozuoye.demo01;

import com.qimozuoye.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class qimozuoyeApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {

    }

}
