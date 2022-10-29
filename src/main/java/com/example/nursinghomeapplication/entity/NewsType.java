package com.example.nursinghomeapplication.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/10
 * \* 时间: 11:31
 * \* 乌漆嘛黑码神保佑没有bug:
 * \新闻类型
 */
@Data
@ToString
@EqualsAndHashCode
@TableName(value = "n_news_type")
public class NewsType extends BaseEntity {
    private Integer typeId;
    private String typeName;
}
