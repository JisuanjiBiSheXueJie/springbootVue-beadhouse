package com.example.nursinghomeapplication.util;

import cn.hutool.core.util.RuntimeUtil;
import com.example.nursinghomeapplication.mapper.InfoCenterMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/4/10
 * \* 时间: 19:06
 * \* 乌漆嘛黑码神保佑没有bug:
 * \
 */
public class DosUtil {
    public String getIp(String data) {
        String dos = "ping " + data + " -l 1 -n 1";
        String str = RuntimeUtil.execForStr(dos);
        return splitCmdReturn(str);
    }
    public String splitCmdReturn(String data) {
        // 从"正在 Ping redisServer [127.0.0.1] 具有 1 字节的数据:\r\n来自 127.0.0.1 的回复: 字节=1 时间<1ms TTL=64\r\n\r\n127.0.0.1 的 Ping 统计信息:\r\n    数据包: 已发送 = 1，已接收 = 1，丢失 = 0 (0% 丢失)，\r\n往返行程的估计时间(以毫秒为单位):\r\n    最短 = 0ms，最长 = 0ms，平均 = 0ms\r\n"
        //正则表达式获取ip
        String[] split = data.split("\\[");
        String[] split1 = split[1].split("]");
        String[] split2 = split1[0].split(" ");
        return split2[0];
    }

}
