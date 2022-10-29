package com.example.nursinghomeapplication.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/5
 * \* 时间: 23:17
 * \* 乌漆嘛黑码神保佑没有bug:
 * \健康打卡表
 */
@SuppressWarnings("SpellCheckingInspection")
@Data
@ToString
@EqualsAndHashCode
@TableName(value = "n_healthpunch")
public class Healthpunch extends BaseEntity {
    private Integer userId;
    private Double temperature;
    private Integer adverse;
    private String personId;
    private Integer pulse;
    private Integer maxBloodPre;
    private Integer minBloodPre;
    private Integer breathingRate;
}
