package com.example.nursinghomeapplication.util;

import org.springframework.util.DigestUtils;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/6
 * \* 时间: 16:46
 * \* 乌漆嘛黑码神保佑没有bug:
 * \
 */
public class Md5Password {
    public String getMD5Password(String password, String salt) {
        for (int i = 0; i < 3; i++) {

            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
