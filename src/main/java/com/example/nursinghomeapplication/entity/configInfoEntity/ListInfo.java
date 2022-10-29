package com.example.nursinghomeapplication.entity.configInfoEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/11
 * \* 时间: 9:33
 * \* 乌漆嘛黑码神保佑没有bug:
 * \宿舍信息表
 */
@Data
@ToString
@EqualsAndHashCode
@TableName(value = "n_listinfo")
public class ListInfo {
    private Integer id;
    private Integer ssNameId;
    private String ssName;
    private Integer ssFloorNum;
    private Integer ssHouses;

}
