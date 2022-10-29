package com.example.nursinghomeapplication.mapper.drug;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nursinghomeapplication.entity.drug.PersonnelDrug;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface PersonnelDrugMapper extends BaseMapper<PersonnelDrug> {
    public List<Map<String,Object>> getDetails(Integer personId);
}
