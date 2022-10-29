package com.example.nursinghomeapplication.entity.food;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.nursinghomeapplication.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/16
 * \* 时间: 10:36
 * \* 乌漆嘛黑码神保佑没有bug:
 * \食品列表
 */
@Data
@ToString
@EqualsAndHashCode
@TableName(value = "n_foods")
public class FoodList extends BaseEntity {
    private String uuid;
    private String name;
    private String dose;
    private String role;
    private String warning;
}
