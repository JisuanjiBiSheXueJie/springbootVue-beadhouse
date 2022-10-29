package com.example.nursinghomeapplication.entity.guest;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.nursinghomeapplication.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@TableName(value = "n_content")
@Data
@ToString
@EqualsAndHashCode
public class Content extends BaseEntity {
    private String title;
    private String content;
    private String body;
    @JsonFormat(pattern = "yyy-MM-dd", timezone = "GMT+8")
    private Integer hotNum;
}
