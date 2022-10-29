package com.example.nursinghomeapplication.entity.drug;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.nursinghomeapplication.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/15
 * \* 时间: 17:40
 * \* 乌漆嘛黑码神保佑没有bug:
 * \  药物列表
 */
@Data
@ToString
@EqualsAndHashCode
@TableName(value = "n_drug")
public class DrugList extends BaseEntity {
    private String uuid;
    private String name;
    private String dose;
    private String role;
    private String warning;

}
