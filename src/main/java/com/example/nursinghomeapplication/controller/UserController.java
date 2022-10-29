package com.example.nursinghomeapplication.controller;

import com.example.nursinghomeapplication.entity.user.User;
import com.example.nursinghomeapplication.service.UserService;
import com.example.nursinghomeapplication.service.UtilService;
import com.example.nursinghomeapplication.util.JsonResult;
import com.example.nursinghomeapplication.util.requestStatus;
import com.example.nursinghomeapplication.util.service.TokenService;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/6
 * \* 时间: 15:10
 * \* 乌漆嘛黑码神保佑没有bug:
 * \
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Resource
    UserService userService;
    @Resource
    TokenService tokenService;
    @Resource
    UtilService utilService;
    /**
     * 从yml中获取值
     */
    @Value("${token.expireTime}")
    private String LengthTime;
    /**
     * log4j配置文件
     */
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    /**
     * 用户登录接口
     *
     * @param user 基本用户登录信息
     * @return
     */
    @PostMapping("/login")
    public JsonResult<?> login(@RequestBody User user) {
        /**
         * 判空
         */
        if (user.getAccount() == null || user.getPassword() == null || user.getSalt() == null || user.getUuid() == null) {
            log.info("异常信息账户信息为空");
            return new JsonResult<>(notFound, "账户信息不能为空");
        }
        /**
         * 根据登录信息到service层检索返回一个json类型
         */
        JsonResult<User> result = userService.login(user);
        String token = "";
        /**
         * 如果请求成功了
         */
        if (result.getStatus().equals(requestStatus.Request_Success.getCode())) {
            /**
             * 创建token并将其返回给前端
             */
            token = tokenService.createToken(result.getData());
            utilService.setRedisKeyValue(token, result.getData().getId().toString(), Long.valueOf(LengthTime));
            HashMap<String, String> json = new HashMap<>();
            json.put("token", token);
            return new JsonResult<>(OK, json);
        }
        return result;
    }

    /**
     * 用户退出登录
     *
     * @param
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public JsonResult<Void> logout(HttpServletRequest request) {
        //从request获取token
        String token = tokenService.getTokenByrequest(request);
        //从request获取用户信息
        User user = userService.getUser(request);
        log.info("用户{}退出登录", user.getName());
        //根据token删除用户登录状态
        ServletContext context = request.getServletContext();
        context.removeAttribute(token);
        return new JsonResult<>(OK);
    }

    //支付功能接口
    @PostMapping("/pay/{value}")
    public JsonResult<Void> pay(@PathVariable Integer value) {
        return new JsonResult<>(OK, "支付成功");
    }

    /**
     * 用户个人信息更改
     *
     * @param user    新信息
     * @param request 请求信息
     * @return
     */
    @PostMapping("/update")
    public JsonResult<User> update(@RequestBody User user, HttpServletRequest request) {
        User updateUser = new User();
        //从token中获取当前用户信息
        User serviceUser = userService.getUser(request);
        log.info("用户{}，进行更新操作", serviceUser.getName());
        //防止一些奇奇怪怪的操作对表主键影响，以及其他字段影响
        updateUser.setId(user.getId());
        updateUser.setName(user.getName());
        updateUser.setModifiedUser(serviceUser.getName());
        updateUser.setPhone(user.getPhone());
        //如果没有更改密码或者密码为空的话不修改密码
        if (!StringUtil.isNullOrEmpty(user.getPassword())) {
            updateUser.setPassword(user.getPassword());
        }
        //判断是否更新成功
        if (userService.updateUser(updateUser)) {
            log.info("用户更新成功");
            return new JsonResult<>(OK);
        }
        return new JsonResult<>(notFound, "操作异常");
    }

    /**
     * 管理员登录
     *
     * @param user 痛login一样都是基础的登录信息
     * @return
     */
    @PostMapping("/adminLogin")
    public JsonResult<?> adminLogin(@RequestBody User user) {
        //非空判断
        if (user.getAccount() == null || user.getPassword() == null || user.getSalt() == null || user.getUuid() == null) {
            log.info("异常信息账户信息为空");
            return new JsonResult<>(notFound, "账户信息不能为空");
        }
        log.info("管理员登录{}", user);
        JsonResult<User> result = userService.adminLogin(user);
        String token = "";
        //判断是否登录成功
        if (result.getStatus().equals(requestStatus.Request_Success.getCode())) {
            token = tokenService.createToken(result.getData());
            utilService.setRedisKeyValue(token, result.getData().getId().toString(), Long.valueOf(LengthTime));
            HashMap<String, String> json = new HashMap<>();
            json.put("token", token);
            return new JsonResult<>(OK, json);
        }
        return result;
    }

    @PostMapping("/adminAddUser")
    public JsonResult<?> User(@RequestBody User user, HttpServletRequest request) {
        User result = userService.getUser(request);
        int userByAcc = userService.getUserByAcc(user);
        if (userByAcc > 0) {
            return new JsonResult<>(notFound, "账户已存在");
        }
        user.setCreateUser(result.getName());
        if (userService.adminAddUser(user)) {
            return new JsonResult<>(OK);
        }
        return new JsonResult<>(notFound, "创建用户失败");
    }

    @PostMapping("/setUserNotUse/{id}")
    public JsonResult<?> setUserNotUse(@PathVariable Integer id, HttpServletRequest request) {
        User result = userService.getUser(request);
        String name = result.getName();
        if (userService.setUserNotUse(id, name)) {
            return new JsonResult<>(OK, "操作成功");
        } else {
            return new JsonResult<>(notFound, "操作失败");
        }
    }

    @PostMapping("/selectPage")
    public JsonResult<?> selectPage(@RequestBody Map<String, Object> data) {
        return new JsonResult<>(OK, userService.selectPage(data));
    }
    @GetMapping("/findUserById/{id}")
    public JsonResult<User> findUserById(@PathVariable Integer id){
        return new JsonResult<>(OK,userService.findUserById(id));
    }
    //获取在线用户
    @GetMapping("/getOnlineUser")
    public JsonResult<List<User>> getOnlineUser( ){
        return new JsonResult<>(OK,userService.getOnlineUser());
    }
}
