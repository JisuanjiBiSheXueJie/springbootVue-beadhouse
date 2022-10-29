package com.example.nursinghomeapplication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.nursinghomeapplication.entity.News;
import com.example.nursinghomeapplication.entity.food.FoodList;
import com.example.nursinghomeapplication.entity.guest.Content;
import com.example.nursinghomeapplication.entity.guest.Visitors;
import com.example.nursinghomeapplication.entity.home.LeaveAndMessage;
import com.example.nursinghomeapplication.mapper.InfoCenterMapper;
import com.example.nursinghomeapplication.mapper.food.FoodListMapper;
import com.example.nursinghomeapplication.mapper.guest.ContentMapper;
import com.example.nursinghomeapplication.mapper.guest.VisitorsMapper;
import com.example.nursinghomeapplication.mapper.home.LeaveAndMessageMapper;
import com.example.nursinghomeapplication.service.IContentService;
import com.example.nursinghomeapplication.service.ex.CartNotFoundException;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author guofan
 * @since 2021-12-02
 */
@Service // 注册为服务
public class ContentServiceImpl implements IContentService {

    private static final Logger log = LoggerFactory.getLogger(IContentService.class);
    @Resource
    ContentMapper contentMapper;
    @Resource
    FoodListMapper foodListMapper;

    @Resource
    LeaveAndMessageMapper leaveAndMessageMapper;
    @Resource
    InfoCenterMapper infoCenterMapper;

    @Resource
    VisitorsMapper visitorsMapper;

    @Override
    public List<News> message(Integer type) {
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        if (type == 1) {
            wrapper.eq("hot_num", type);
        } else {
            wrapper.ne("hot_num", 0);
        }
        wrapper.eq("status", 1);
        wrapper.eq("is_delete", 1);
        wrapper.eq("species", 0);
        List<News> list = infoCenterMapper.selectList(wrapper);
        if (list == null) {
            log.info("未找到任何值");
            throw new CartNotFoundException("未找到任何值");
        }
        return list;
    }

    @Override
    public List<FoodList> getFoodList() {
        QueryWrapper<FoodList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        return foodListMapper.selectList(queryWrapper);
    }

    @Override
    public boolean saveLeaveAndMessage(LeaveAndMessage leaveAndMessage) {
        return leaveAndMessageMapper.insert(leaveAndMessage) > 0;
    }

    @Override
    public boolean saveVisitor(Visitors visitors) {
        return visitorsMapper.insert(visitors) > 0;
    }

    @Override
    public Map<String, Object> getVisitor(Map<String, Object> data) {
//        return visitorsMapper.selectList(new QueryWrapper<Visitors>().eq("status",1));
        Integer current = 1;
        Integer size = 10;
        try {
            current = data.get("current") == null ? current : (Integer) data.get("current");
            size = data.get("size") == null ? size : (Integer) data.get("size");
        } catch (Exception e) {
            e.printStackTrace();
        }
        QueryWrapper<Visitors> wrapper = new QueryWrapper<>();
        try {
            if (!StringUtil.isNullOrEmpty(data.get("like").toString())) {
                wrapper.like("name", data.get("like"));
            }
        } catch (Exception e) {
            log.info("没有like条件，现在为初始化页面时间");
        }
        wrapper.eq("status", 1);
        Page<Visitors> userPage = new Page<>(current, size);
        IPage<Visitors> userIPage = visitorsMapper.selectPage(userPage, wrapper);
        Map<String, Object> map = new HashMap<>();
        List<Visitors> list = userIPage.getRecords();
        map.put("list", userIPage.getRecords());
        map.put("pages", userIPage.getPages());
        map.put("total", userIPage.getTotal());
        return map;
    }

    @Override
    public boolean updateVisitor(Visitors visitors) {
        return visitorsMapper.updateById(visitors) > 0;
    }
}
