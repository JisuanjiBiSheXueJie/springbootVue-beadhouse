package com.example.nursinghomeapplication;

import com.alibaba.fastjson.JSONArray;
import com.example.nursinghomeapplication.util.Md5Password;

import java.util.UUID;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/4/12
 * \* 时间: 12:04
 * \* 乌漆嘛黑码神保佑没有bug:
 * \
 */
public class MainTest {
    //来一个主方法
    public static void main(String[] args) {
        Md5Password md5Password = new Md5Password();
        String s = UUID.randomUUID().toString().toUpperCase();
        System.out.println(s);
        String md5Password1 = md5Password.getMD5Password("admin222", s);
        System.out.println(md5Password1);
//        for (int i = 1; i < 97; i++) {
//            System.out.println("sum (p"+i+" * MULTIPLY_POWER)  p"+i+",");
//        }
    }
}
