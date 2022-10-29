package com.example.nursinghomeapplication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.nursinghomeapplication.entity.user.User;
import com.example.nursinghomeapplication.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.HashMap;

@SpringBootTest
class UserServiceImplTest {
    @Resource
    UserMapper userMapper;
    @Test
    void selectPage(){
        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        Page<User> userPage = new Page<>(1 , 10);
        IPage<User> userIPage = userMapper.selectPage(userPage , userLambdaQueryWrapper);
//        userIPage.getRecords()
        System.out.println("总页数： "+userIPage.getPages());
        System.out.println("总记录数： "+userIPage.getTotal());
        userIPage.getRecords().forEach(System.out::println);
    }
    @Test
    void test(){
        HashMap<String, Object> data = new HashMap<>();
        System.out.println(data.get("current").toString().isEmpty());
    }
}
