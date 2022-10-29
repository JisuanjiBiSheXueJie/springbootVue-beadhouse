package com.example.nursinghomeapplication.entity.guest;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.nursinghomeapplication.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@TableName(value = "n_index_introduce")
public class NIndexIntroduce extends BaseEntity {
    private String name;
    private String body;
    private String type;
}
