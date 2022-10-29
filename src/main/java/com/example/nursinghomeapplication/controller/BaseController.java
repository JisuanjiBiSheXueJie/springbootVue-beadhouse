package com.example.nursinghomeapplication.controller;


import com.example.nursinghomeapplication.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.sql.rowset.serial.SerialException;

/**
 * @author 28159
 */ //控制层的基类
public class BaseController {
    //操作成功的状态码
    public static final int OK = 200;
    //找不到的状态码
    public static final int notFound = 404;
    @ExceptionHandler(SerialException.class)     //统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        return result;
    }
}
