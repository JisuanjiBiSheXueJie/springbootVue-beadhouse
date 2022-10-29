package com.example.nursinghomeapplication.service.impl;

import com.aliyun.tea.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.nursinghomeapplication.entity.Healthpunch;
import com.example.nursinghomeapplication.entity.user.User;
import com.example.nursinghomeapplication.mapper.HealthpunchMapper;
import com.example.nursinghomeapplication.mapper.UserMapper;
import com.example.nursinghomeapplication.service.UserService;
import com.example.nursinghomeapplication.service.UtilService;
import com.example.nursinghomeapplication.util.GenerateUuid;
import com.example.nursinghomeapplication.util.JsonResult;
import com.example.nursinghomeapplication.util.Md5Password;
import com.example.nursinghomeapplication.util.requestStatus;
import com.example.nursinghomeapplication.util.service.TokenService;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/6
 * \* 时间: 15:09
 * \* 乌漆嘛黑码神保佑没有bug:
 * \用户控制层
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UtilService utilService;
    @Resource
    TokenService tokenService;
    @Resource
    HealthpunchMapper healthpunchMapper;
    @Value("${user.adminUser}")
    private Integer adminUserId;

    @Override
    public JsonResult<User> login(User user) {
        String resultCode = utilService.pdCaptacha(user.getUuid());
        log.info("开始判断验证码入参:{},对比参数{}", user.getSalt(), resultCode);
        if (StringUtils.isEmpty(resultCode)) {
            log.info("没有找到验证码");
            return new JsonResult<>(requestStatus.Request_Unauthorized.getCode(), requestStatus.Request_Unauthorized.getMessage());
        }
        if (!resultCode.equals(user.getSalt())) {
            log.info("两次验证码不一致");
            return new JsonResult<>(requestStatus.Request_Unauthorized.getCode(), "验证码不正确");
        }
        log.info("用户:{}开始登录", user.getAccount());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Md5Password md5Password = new Md5Password();
        wrapper.eq("account", user.getAccount());
        User result = userMapper.selectOne(wrapper);
        if (result.getPassword() != null) {
            String newMd5Password = md5Password.getMD5Password(user.getPassword(), result.getSalt());
            if (!newMd5Password.equals(result.getPassword())) {
                log.info("两次密码不匹配");
                return new JsonResult<>(requestStatus.Request_NotFount.getCode(), "密码错误");
            }
        }
        return new JsonResult<>(requestStatus.Request_Success.getCode(), result);
    }

    @Override
    public void healthpunch(Healthpunch healthpunch) {
        healthpunch.setId(null);
        healthpunch.setModifiedUser(null);
        healthpunch.setModifiedTime(null);
        healthpunch.setStatus(1);
        healthpunchMapper.insert(healthpunch);
    }

    @Override
    public User initView(User user) {
        User result = userMapper.selectById(user.getId());
        if (result.getId() == null) {
            return null;
        }
        result.setPassword(null);
        result.setSalt(null);
        result.setModifiedUser(null);
        result.setModifiedTime(null);
        return result;
    }

    @Override
    public User getUser(HttpServletRequest request) {
        String UserId = utilService.getValueForRedisByKey(tokenService.getTokenByrequest(request));
        return userMapper.selectById(UserId);
    }


    @Override
    public boolean updateUser(User user) {
        if (user.getPassword() != null) {
            Md5Password md5Password = new Md5Password();
            user.setSalt(UUID.randomUUID().toString().toUpperCase());
            user.setPassword(md5Password.getMD5Password(user.getPassword(), user.getSalt()));
        }
        int i = userMapper.updateById(user);
        if (i == 1) {
            return true;
        }
        return false;
    }

    //管理员登录
    @Override
    public JsonResult<User> adminLogin(User user) {
        String resultCode = utilService.pdCaptacha(user.getUuid());
        log.info("开始判断验证码入参:{},对比参数{}", user.getSalt(), resultCode);
        if (StringUtils.isEmpty(resultCode)) {
            log.info("没有找到验证码");
            return new JsonResult<>(requestStatus.Request_Unauthorized.getCode(), requestStatus.Request_Unauthorized.getMessage());
        }
        if (!resultCode.equals(user.getSalt())) {
            log.info("两次验证码不一致");
            return new JsonResult<>(requestStatus.Request_Unauthorized.getCode(), "验证码不正确");
        }
        log.info("用户:{}开始登录", user.getAccount());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Md5Password md5Password = new Md5Password();
        wrapper.eq("account", user.getAccount());
        User result = userMapper.selectOne(wrapper);
        if (result.getPassword() != null) {
            String newMd5Password = md5Password.getMD5Password(user.getPassword(), result.getSalt());
            if (!newMd5Password.equals(result.getPassword())) {
                log.info("两次密码不匹配");
                return new JsonResult<>(requestStatus.Request_NotFount.getCode(), "密码错误");
            }
        }
        if (!result.getAdmin().equals(adminUserId)) {
            log.info("非管理员登录{}强制返回", result);
            return new JsonResult<>(requestStatus.Request_NotFount.getCode(), "非管理员登录");
        }
        return new JsonResult<>(requestStatus.Request_Success.getCode(), result);
    }

    @Override
    public boolean adminAddUser(User user) {
        user.setId(null);
        user.setSalt(null);
        user.setAdmin(0);
        Md5Password md5Password = new Md5Password();
        user.setSalt(UUID.randomUUID().toString().toUpperCase());
        user.setPassword(md5Password.getMD5Password(user.getPassword(), user.getSalt()));
//        GenerateUuid uuid = new GenerateUuid();
//        user.setSalt(uuid.getUuid());
//        Md5Password md5Password = new Md5Password();
//        user.setPassword(md5Password.getMD5Password(user.getPassword(), uuid.getUuid()));
        int insert = userMapper.insert(user);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setUserNotUse(Integer id, String name) {
        User user = new User();
        user.setStatus(0);
        user.setModifiedUser(name);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        int result = userMapper.update(user, wrapper);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> selectPage(Map<String, Object> data) {
        Integer current = 1;
        Integer size = 10;
        try {
            current = data.get("current") == null ? current : (Integer) data.get("current");
            size = data.get("size") == null ? size : (Integer) data.get("size");
        } catch (Exception e) {
            log.error("分页参数转换异常:{}", e.getMessage());
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        wrapper.eq("admin", 0);
        //select * form user where status = 1 and admin = 0
        try {
            if (!StringUtil.isNullOrEmpty(data.get("like").toString())) {
                wrapper.like("name", data.get("like"));
            }
        } catch (Exception e) {
            log.info("没有like条件，现在为初始化页面时间");
        }
        Page<User> userPage = new Page<>(current, size);
        IPage<User> userIPage = userMapper.selectPage(userPage, wrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("list", userIPage.getRecords());
        map.put("pages", userIPage.getPages());
        map.put("total", userIPage.getTotal());
        return map;
    }

    @Override
    public User findUserById(Integer id) {
        User user = userMapper.selectById(id);
        user.setPassword(null);
        user.setSalt(null);
        return user;
    }

    @Override
    public List<User> getOnlineUser() {
        String valueForRedisByKey = utilService.getValueForRedisByKey("*");
        List<?> list = utilService.getList(null);
        System.out.println(list);
        return null;
    }

    @Override
    public int getUserByAcc(User user) {
        return userMapper.selectCount(new QueryWrapper<User>().eq("account", user.getAccount()))>0?1:0;
    }
}
