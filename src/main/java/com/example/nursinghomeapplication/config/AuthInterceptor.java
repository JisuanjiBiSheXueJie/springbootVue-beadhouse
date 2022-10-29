package com.example.nursinghomeapplication.config;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.example.nursinghomeapplication.config.ex.CustomException;
import com.example.nursinghomeapplication.entity.user.User;
import com.example.nursinghomeapplication.mapper.UserMapper;
import com.example.nursinghomeapplication.service.impl.UtilServiceImpl;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    @Resource
    private UserMapper userMapper;
    @Resource
    UtilServiceImpl utilService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            throw new CustomException( "未获取到token, 请重新登录");
        }
        String JWTresultId ="";
        try {
            JWTresultId = JWT.decode(token).getClaim("account").asString();
        } catch (Exception e) {
            throw new CustomException( "token不合法");
        }
        String userId = utilService.getValueForRedisByKey(token);
        User result = userMapper.selectById(userId);
        if (result == null || !result.getAccount().equals(JWTresultId)) {
            throw new CustomException( "token不合法");
        }
        return true;
    }
}
