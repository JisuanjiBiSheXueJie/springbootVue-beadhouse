package com.example.nursinghomeapplication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.nursinghomeapplication.entity.config.ConfigType;
import com.example.nursinghomeapplication.entity.drug.DrugList;
import com.example.nursinghomeapplication.entity.personnel.PersonUser;
import com.example.nursinghomeapplication.entity.user.User;
import com.example.nursinghomeapplication.mapper.UserMapper;
import com.example.nursinghomeapplication.mapper.configInfo.ConfigTypeMapper;
import com.example.nursinghomeapplication.mapper.drug.DrugListMapper;
import com.example.nursinghomeapplication.mapper.drug.PersonnelDrugMapper;
import com.example.nursinghomeapplication.mapper.food.PersonnelFoodsMapper;
import com.example.nursinghomeapplication.mapper.personnel.PersonMapper;
import com.example.nursinghomeapplication.service.PersonService;
import com.example.nursinghomeapplication.service.ex.RunServiceException;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/15
 * \* 时间: 14:36
 * \* 乌漆嘛黑码神保佑没有bug:
 * \
 */
@Service
public class PersonServiceImpl implements PersonService {
    @Resource
    PersonMapper personMapper;
    @Resource
    ConfigTypeMapper configTypeMapper;
    @Resource
    DrugListMapper drugListMapper;
    @Resource
    PersonnelDrugMapper personnelDrugMapper;
    @Resource
    PersonnelFoodsMapper personnelFoodsMapper;
    @Resource
    UserMapper userMapper;

    private static final Logger log = LoggerFactory.getLogger(PersonService.class);

    /**
     * 根据id获取客户信息
     *
     * @param id 客户id
     * @return
     */
    @Override
    public List<PersonUser> getPersonList(Integer id) {
        QueryWrapper<PersonUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);
        wrapper.eq("status", 1);
        List<PersonUser> result = personMapper.selectList(wrapper);
        result.forEach(item -> {
            item.setModifiedUser(null);
            item.setModifiedTime(null);
        });
        return result;
    }

    /**
     * 根据配置信息
     *
     * @return
     */
    @Override
    public ConfigType getAddRemaining() {
        ConfigType result = configTypeMapper.selectOne(null);
        return result;
    }

    @Override
    public ConfigType getUserPersonnel(Integer id) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getPersonDrug(Integer userId) {
        List<Map<String, Object>> list = personnelDrugMapper.getDetails(userId);
        return list;
    }

    @Override
    public DrugList getDrugByDrugId(String drugId) {
        QueryWrapper<DrugList> wrapper = new QueryWrapper<>();
        wrapper.eq("uuid", drugId).eq("status", 1);
        return drugListMapper.selectOne(wrapper);
    }

    @Override
    public List<Map<String, Object>> getPerFoodsById(Integer drugId) {
        return personnelFoodsMapper.getFoodList(drugId);
    }

    @Override
    public List<PersonUser> getPersonnelUser(Integer id) {
        List<Map<String, Object>> list = personMapper.getPersonnelUser(id);
        List<PersonUser> users = new ArrayList<>();
        for (Map<String, Object> map : list) {
            QueryWrapper<PersonUser> wrapper = new QueryWrapper<>();
            wrapper.eq("id", map.get("id"))
                    .eq("status", 1);
            PersonUser user = personMapper.selectOne(wrapper);
            users.add(user);
        }
        return users;
    }

    @Override
    public Map<String, Object> getPersonPage(Map<String, Object> data) {
        Integer current = 1;
        Integer size = 10;
        try {
            current = data.get("current") == null ? current : (Integer) data.get("current");
            size = data.get("size") == null ? size : (Integer) data.get("size");
        } catch (Exception e) {
            e.printStackTrace();
        }//设置条件
        QueryWrapper<PersonUser> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        try {
            if (!StringUtil.isNullOrEmpty(data.get("like").toString())) {
                wrapper.like("name", data.get("like"));
            }
        } catch (Exception e) {
            log.info("没有like条件，现在为初始化页面时间");
        }
        Page<PersonUser> userPage = new Page<>(current, size);
        IPage<PersonUser> userIPage = personMapper.selectPage(userPage, wrapper);
        List<PersonUser> userList = userIPage.getRecords();
        for (PersonUser item : userList) {
            try {
                String name = userMapper
                        .selectOne(new QueryWrapper<User>()
                                .eq("id", item.getUserId())
                                .eq("status", 1)).getName();
                item.setUserName(name);
            } catch (Exception e) {
                log.info("未找到数据");
            }
        }
        Map<String, Object> map = new HashMap<>(3);
        map.put("list", userList);
        map.put("pages", userIPage.getPages());
        map.put("total", userIPage.getTotal());
        return map;

    }

    @Override
    public Map<String, List<Map<String, Object>>> getPersonMessById(Integer id) {
        List<Map<String, Object>> foodsById = personMapper.getPersonFoodsById(id);
        List<Map<String, Object>> drugsById = personMapper.getPersonDrudsById(id);
        Map<String, List<Map<String, Object>>> listMap = new HashMap<>();
        listMap.put("foods", foodsById);
        listMap.put("drugs", drugsById);
        return listMap;
    }

    @Override
    public boolean delPersonById(PersonUser user) {
        user.setStatus(0);
        personMapper.updateById(user);
        return false;
    }

    @Override
    public boolean addPerson(PersonUser user) {
        if (user.getId() == null) {
            return personMapper.insert(user) > 0;
        } else {
            return personMapper.updateById(user) > 0;
        }
    }

    @Override
    public PersonUser getPersonById(Integer id) {
        return personMapper.selectById(id);
    }
}
