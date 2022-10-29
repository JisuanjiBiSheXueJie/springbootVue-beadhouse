package com.example.nursinghomeapplication.controller;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/4/10
 * \* 时间: 17:37
 * \* 乌漆嘛黑码神保佑没有bug:
 * \
 */

import com.example.nursinghomeapplication.mapper.InfoCenterMapper;
import com.example.nursinghomeapplication.util.DosUtil;
import com.example.nursinghomeapplication.util.JsonResult;
import com.example.nursinghomeapplication.util.StringUtils;
import com.example.nursinghomeapplication.util.service.Server;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * 服务器监控
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/monitor")
public class ServerController extends BaseController {

    /**
     * log4j
     */
    private static final Logger log = LoggerFactory.getLogger(ServerController.class);
    @Value("${spring.datasource.url}")
    private String DatabaseUrl;
    //数据库用户名
    @Value("${spring.datasource.username}")
    private String DatabaseUsername;
    //数据库密码
    @Value("${spring.datasource.password}")
    private String DatabasePassword;
    //获取Redis的url
    @Value("${spring.redis.host}")
    private String RedisHost;
    //获取Redis的端口
    @Value("${spring.redis.port}")
    private String RedisPort;
    //获取Redis的密码
    @Value("${spring.redis.password}")
    private String RedisPassword;

    @Resource
    private InfoCenterMapper infoCenterMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //获取服务器列表
    @GetMapping("/server")
    public JsonResult getServerList() throws Exception {
        Server server = new Server();
        server.copyTo();
        return new JsonResult(OK, server);
    }

    /**
     * 服务器监控
     */
    @GetMapping("/getDatabaseMess")
    public JsonResult getDatabaseMess() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        DosUtil dosUtil = new DosUtil();
        String[] split = DatabaseUrl.split("//")[1].split("/");
        String databaseName = split[1];
        String DatabaseUrl = split[0].split(":")[0];
        String DatabasePort = split[0].split(":")[1];
        log.info("获取数据库信息");
        result.put("database", dosUtil.getIp(DatabaseUrl));
        result.put("databaseUsername", DatabaseUsername);
        result.put("databaseName", databaseName);
        result.put("databasePort", DatabasePort);
        result.put("databasePassword", DatabasePassword);
        result.put("redisHost", dosUtil.getIp(RedisHost));
        result.put("redisPort", RedisPort);
        result.put("redisPassword", RedisPassword);
        //获取数据库信息
        result.put("databaseVersion", infoCenterMapper.getVersion());
        return new JsonResult(OK, result);
    }

    @GetMapping("/cache")
    public JsonResult getInfo() throws Exception {
        Properties info = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.info());
        Properties commandStats = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.info("commandstats"));
        Object dbSize = redisTemplate.execute((RedisCallback<Object>) connection -> connection.dbSize());

        Map<String, Object> result = new HashMap<>(3);
        result.put("info", info);
        result.put("dbSize", dbSize);

        List<Map<String, String>> pieList = new ArrayList<>();
        commandStats.stringPropertyNames().forEach(key -> {
            Map<String, String> data = new HashMap<>(2);
            String property = commandStats.getProperty(key);
            data.put("name", StringUtils.removeStart(key, "cmdstat_"));
            data.put("value", StringUtils.substringBetween(property, "calls=", ",usec"));
            pieList.add(data);
        });
        result.put("commandStats", pieList);
        return new JsonResult(OK, result);
    }

}
