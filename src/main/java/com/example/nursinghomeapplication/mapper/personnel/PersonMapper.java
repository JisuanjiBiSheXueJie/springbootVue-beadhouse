package com.example.nursinghomeapplication.mapper.personnel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nursinghomeapplication.entity.personnel.PersonUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface PersonMapper extends BaseMapper<PersonUser> {
    List<Map<String,Object>> getPersonFoodsById(Integer id);
    List<Map<String,Object>> getPersonDrudsById(Integer id);
    List<Map<String,Object>> getPersonnelUser(Integer id);
}
