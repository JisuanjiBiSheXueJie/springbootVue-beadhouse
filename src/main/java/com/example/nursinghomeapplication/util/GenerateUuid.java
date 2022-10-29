package com.example.nursinghomeapplication.util;

import cn.hutool.core.util.IdUtil;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/7
 * \* 时间: 21:22
 * \* 乌漆嘛黑码神保佑没有bug:
 * \
 */
public class GenerateUuid {
    public String getUuid(){
        return  IdUtil.randomUUID();
    }
}
