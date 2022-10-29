package com.example.nursinghomeapplication.util;

/**
 * @author 28159
 */

public enum requestStatus {
    //成功
    Request_Success(200, "获取数据Ok"),
    Request_Redirect(300,"服务器接受到数据并将其重定向"),
    Request_Unauthorized(401,"(未授权）请求要求身份验证。"),
    Request_NotFount(404,"服务器找不到请求的网页");
    private Integer code;
    private String message;

    requestStatus(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
