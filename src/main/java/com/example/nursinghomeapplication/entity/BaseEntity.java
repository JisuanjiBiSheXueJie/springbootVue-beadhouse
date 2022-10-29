package com.example.nursinghomeapplication.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 公共的实体类用于继承
 *
 * @author 28159
 */
@Data
@ToString
@EqualsAndHashCode
public class BaseEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer status;
    private String createUser;
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private String modifiedUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date modifiedTime;

}
