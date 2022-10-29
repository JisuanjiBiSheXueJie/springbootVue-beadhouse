package com.example.nursinghomeapplication.pojo;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/9
 * \* 时间: 17:00
 * \* 乌漆嘛黑码神保佑没有bug:
 * \
 */
public class JsonEntity {
    private Integer code;
    /**
     * 返回内容
     */
    public String msg;

    /**
     * 数据对象
     */
    public String data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
