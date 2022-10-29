package com.example.nursinghomeapplication.controller;

import cn.hutool.core.date.DateUtil;
import com.example.nursinghomeapplication.entity.user.User;
import com.example.nursinghomeapplication.service.UserService;
import com.example.nursinghomeapplication.service.UtilService;
import com.example.nursinghomeapplication.util.Captcha;
import com.example.nursinghomeapplication.util.JsonResult;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/6
 * \* 时间: 18:29
 * \* 乌漆嘛黑码神保佑没有bug:
 * \
 */
@RestController
@RequestMapping("/util")
public class UtilController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(UtilController.class);
    @Autowired
    Captcha captcha;
    @Autowired
    UtilService utilService;
    @Resource
    UserService userService;

    /**
     * 获取验证码
     *
     * @return 一个map集合里面存储了验证码的uuid值以及图片的imageBase64 数据
     */
    @PostMapping("/getCaptacha")
    public JsonResult<Map<String, Object>> getCaptacha() {
        Map<String, Object> result = captcha.etCaptcha();
        if (result != null) {
            utilService.getCaptacha((String) result.get("code"), (String) result.get("uuid"));
        }
        result.put("code", "不许你偷看验证码");
        return new JsonResult<>(OK, result);
    }

    /**
     * 短信验证码
     *
     * @param request 当前操作用户
     * @return 验证码的唯一uuid
     */
    @PostMapping("/getVerificationCode")
    public JsonResult<Map<String, String>> getVerificationCode(HttpServletRequest request) {
        User user = userService.getUser(request);
        /**
         * 验证码接口暂时没有
         */
        log.info("入参为手机号码为:{},uuid为{}", user.getPhone(), user.getUuid());
        String code = utilService.setVerificationCode();
        Map<String, String> result = new HashMap<>();
        result.put("uuid", code);
        return new JsonResult<>(OK, result);
    }

    /**
     * 根据前台传回的验证码信息，以及getVerificationCode返回的uuid来进行判断
     *
     * @param query 验证码的json
     * @return 状态码
     */
    @PostMapping("/JudgeVerificationCode")
    public JsonResult<Void> JudgeVerificationCode(@RequestBody Map<String, String> query) {
        try {
            if (StringUtil.isNullOrEmpty(query.get("uuid")) && StringUtil.isNullOrEmpty(query.get("salt"))) {
                return new JsonResult<>(notFound, "操作异常");
            }
        } catch (Exception e) {
            return new JsonResult<>(notFound, "操作异常");
        }
        /**
         * 验证码接口暂时没有
         */
        log.info("前台传回入参[uuid:{},验证码:{}]", query.get(0), query.get(1));
        Boolean result = utilService.JudgeVerificationCode(query.get("uuid"), query.get("salt"));
        if (!result) {
            return new JsonResult<>(notFound, "验证码错误");
        }
        return new JsonResult<>(OK);
    }

    @PostMapping("/generateCode/{name}")
    public JsonResult<?> generateCode(@PathVariable("name") String name) {
        //格式化日期
        HashMap<String, String> result = new HashMap<>();
        result.put("code", utilService.generateCode(name));
        return new JsonResult<>(OK,result);
    }

}
