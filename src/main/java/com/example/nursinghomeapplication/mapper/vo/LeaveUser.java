package com.example.nursinghomeapplication.mapper.vo;

import com.example.nursinghomeapplication.entity.Leave;
import com.example.nursinghomeapplication.entity.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/17
 * \* 时间: 15:23
 * \* 乌漆嘛黑码神保佑没有bug:
 * \
 */
@Data
public class LeaveUser {
    private Integer id;
    private String account;
    private String name;
    private String phone;
    private String title;
    @JsonFormat(pattern = "yyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private String startTime;
    @JsonFormat(pattern = "yyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private String endTime;
    private String address;
    private String identity;
    private String body;
    private Integer status;
}
