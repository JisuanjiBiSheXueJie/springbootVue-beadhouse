package com.example.nursinghomeapplication.entity.personnel;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.nursinghomeapplication.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/15
 * \* 时间: 14:20
 * \* 乌漆嘛黑码神保佑没有bug:
 * \客户表
 */
@Data
@ToString
@EqualsAndHashCode
@TableName(value = "n_personnel")
public class PersonUser extends BaseEntity {
    private Integer userId;
    @TableField(exist = false)
    private String userName;
    private String name;
    private String uuid;
    private String address;
    private String phone;
    private String familyPhone1;
    private String familyPhone2;
    private String warning;

}
