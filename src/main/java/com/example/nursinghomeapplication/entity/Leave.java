package com.example.nursinghomeapplication.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/5
 * \* 时间: 23:20
 * \* 乌漆嘛黑码神保佑没有bug:
 * \请假表
 */
@Data
@ToString
@EqualsAndHashCode
@TableName(value = "n_leave")
public class Leave extends BaseEntity {
    private Integer userId;
    private String title;
    private String addresses;
    private String identity;
    private String body;
    private String errorReason;
    @JsonFormat(pattern = "yyy-MM-dd", timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyy-MM-dd", timezone = "GMT+8")
    private Date endTime;

}
