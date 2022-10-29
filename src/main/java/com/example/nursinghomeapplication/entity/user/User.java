package com.example.nursinghomeapplication.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.nursinghomeapplication.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/5
 * \* 时间: 23:23
 * \* 乌漆嘛黑码神保佑没有bug:
 * \这是一个用户的实体类集成工具实体类免写一些字段
 */
@Data
@ToString
@EqualsAndHashCode
@TableName(value = "n_user")
public class User extends BaseEntity {
    private String account;
    private String password;
    private Integer admin;
    private String salt;
    private String name;
    private String uuid;
    private String phone;
    private String address;

}
