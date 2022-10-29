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
 * \* 时间: 17:42
 * \* 乌漆嘛黑码神保佑没有bug:
 * \客户用药
 */
@Data
@ToString
@EqualsAndHashCode
@TableName(value = "n_personnel_drug")
public class PersonnelDrug extends BaseEntity {
    private Integer personnelId;
    private String drugId;
    private String dose;

}
