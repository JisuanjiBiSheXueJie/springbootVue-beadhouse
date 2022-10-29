package com.example.nursinghomeapplication.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.example.nursinghomeapplication.SEnum.ShortMessage;
import com.example.nursinghomeapplication.service.UtilService;
import com.example.nursinghomeapplication.util.GenerateCode;
import com.example.nursinghomeapplication.util.GenerateUuid;
import com.example.nursinghomeapplication.util.oter.Constants;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/6
 * \* 时间: 15:09
 * \* 乌漆嘛黑码神保佑没有bug:
 * \用户控制层
 */
@Service
public class UtilServiceImpl implements UtilService {

    private static final Logger log = LoggerFactory.getLogger(UtilService.class);
    @Resource
    RedisTemplate redisTemplate;
    @Value("${user.nodeIp}")
    private String port;

    @Value("${user.ip}")
    private String ip;
    @Value("${user.codePath}")
    private String codePath;

    @Override
    public void getCaptacha(String result, String ip) {
        redisTemplate.opsForValue().set(ip, result, 120L, TimeUnit.SECONDS);
    }

    /**
     * 获取验证码的uuid从redis里面查找，获取成功后并将其kay从数据中删除
     *
     * @param uuid 验证码的key
     * @return 验证码
     */
    @Override
    public String pdCaptacha(String uuid) {
        String code = getValueForRedisByKey(uuid);
        log.info("成功获取到验证码{},删除key{}", code, uuid);
        redisTemplate.delete(uuid);
        return code;
    }

    @Override
    public boolean setRedisKeyValue(String key, String vlaue, Long LengthTime) {
        if (StringUtil.isNullOrEmpty(key) && StringUtil.isNullOrEmpty(vlaue)) {
            return false;
        }
        redisTemplate.opsForValue().set(key, vlaue, LengthTime, TimeUnit.SECONDS);
        return true;
    }

    @Override
    public String getValueForRedisByKey(String key) {
        if (StringUtil.isNullOrEmpty(key)) {
            return null;
        }
        return (String) redisTemplate.opsForValue().get(key);
    }

    @Override
    public String setVerificationCode() {
        GenerateCode generateCode = new GenerateCode();
        String code = generateCode.getVerificationCode();
        log.info("生成验证码:[{}]", code);
        String uuid = new GenerateUuid().getUuid();
        log.info("存入redis key为:{},value为:{}", uuid, code);
        /**
         * 阿里云短信接口
         */
        this.setRedisKeyValue(uuid, code, 120L);
        ShortMessage shortMessage = new ShortMessage();
        ShortMessage.ShortMessageEnum.SM01.setCode(code);
        log.info("{}", ShortMessage.ShortMessageEnum.SM01);
        return uuid;
    }

    @Override
    public boolean JudgeVerificationCode(String uuid, String code) {
        String key = this.getValueForRedisByKey(uuid);
        if (!StringUtil.isNullOrEmpty(key)) {
            if (key.equals(code)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<?> getList(String key) {
        //获取redis中所有的key的值
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object o = valueOperations.get("*");
        return redisTemplate.getClientList();
    }

    @Override
    public String generateCode(String name) {
        String url = "http://" + ip + ":" + port +codePath+"?name=" + name;
        // 定义文件的唯一标识（前缀）
        String uuid = IdUtil.fastSimpleUUID();
        String rootFilePath = System.getProperty("user.dir") + "/files/" + uuid + "_" + name;  // 获取上传的路径
        // 生成指定url对应的二维码到文件，宽和高都是300像素
        QrCodeUtil.generate(url, 300, 300, FileUtil.file(rootFilePath));
        return uuid;
    }
}
