package com.example.nursinghomeapplication;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import io.netty.util.internal.StringUtil;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/21
 * \* 时间: 12:56
 * \* 乌漆嘛黑码神保佑没有bug:
 * \
 */
public class test {
    Integer x=0;
    Integer y=0;
    @Test
    void main() {
        File input = FileUtil.file("D:/io/input/main.jpg");
        Font font = new Font("黑体", Font.BOLD, 100);
        String str="你好这个世界";
        ArrayList<Integer> listx = new ArrayList<>();
        ArrayList<Integer> listy = new ArrayList<>();
        String[] split = str.split("");
        for (String s : split) {
            Integer x = getWx();
            Integer y = getY();
            listx.add(x);
            listy.add(y);
            ImgUtil.pressText(input,input,s,Color.BLACK,font,x,y,1);
        }
        System.out.println(listx);
        System.out.println(listy);
    }
    Integer getWx(){
        return x=RandomUtil.randomInt(x+100, 200+x);
    }
    Integer getY(){
        return y=RandomUtil.randomInt(10,100);
    }
    @Test
    void aa(){
        Map<String, Object> map = new HashMap<>();
        map.put("like","aa");
        System.out.println(StringUtil.isNullOrEmpty(map.get("like").toString()));
    }
}
