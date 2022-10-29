package com.example.nursinghomeapplication.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/10
 * \* 时间: 11:29
 * \* 乌漆嘛黑码神保佑没有bug:
 * \新闻表
 */
@Data
@ToString
@EqualsAndHashCode
@TableName(value = "n_news")
public class News extends BaseEntity {
    private String title;
    private String body;
    private String type;
    private Integer hotNum;
    private String content;
    private String fileName;
    private String isDelete;
    private Integer species;

}
