package com.example.nursinghomeapplication.entity.guest;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.nursinghomeapplication.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@EqualsAndHashCode
@TableName(value = "n_visitors")
public class Visitors extends BaseEntity {
    private String name;
    private String phone;
    private String email;

    @JsonFormat(pattern = "yyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date endTime;

}
