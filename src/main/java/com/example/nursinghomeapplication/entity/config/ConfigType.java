package com.example.nursinghomeapplication.entity.config;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/15
 * \* 时间: 16:33
 * \* 乌漆嘛黑码神保佑没有bug:
 * \
 */
@Data
@ToString
@EqualsAndHashCode
@TableName(value = " n_config")
public class ConfigType {
    private Integer id;
    private Integer nowRemainNum;
    private Integer maxRemainNum;
}
