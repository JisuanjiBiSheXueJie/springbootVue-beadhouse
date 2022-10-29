package com.example.nursinghomeapplication.mapper.food;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nursinghomeapplication.entity.food.PersonnelFoods;

import java.util.List;
import java.util.Map;

public interface PersonnelFoodsMapper extends BaseMapper<PersonnelFoods> {
    /**
     * 根据客户id获取食品信息
     * @param id
     * @return
     */
    public List<Map<String,Object>> getFoodList(Integer id);
}
