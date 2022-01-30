package com.graduation.stringboot;

import com.graduation.stringboot.entity.Result;
import com.graduation.stringboot.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class StringBootApplicationTests {

    UserInfoService userInfoService;

    @Autowired
    StringBootApplicationTests(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Test
    void contextLoads() {
        Result res = userInfoService.getAll();
    }

}
