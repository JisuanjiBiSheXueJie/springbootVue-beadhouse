package com.example.nursinghomeapplication.entity.home;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.nursinghomeapplication.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName(value = "n_leave_message")
public class LeaveAndMessage extends BaseEntity {
    private String name;
    private String phone;
    private String mess;
    private Integer isContact;

}
