package com.example.nursinghomeapplication.entity.BX;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.nursinghomeapplication.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * \* Created with IntelliJ IDEA.
 * \* 用户: 28159
 * \* Date: 2022/3/5
 * \* Time: 23:08
 * \* Description:
 * \报修表集成工具类
 */
@Data
@ToString
@EqualsAndHashCode
@TableName(value = "n_bx")
public class ReportForRepair extends BaseEntity {
    private Integer userId;
    private String bxTitle;
    private String bxAddresses;
    private String bxBody;
    private String errorReason;
}
