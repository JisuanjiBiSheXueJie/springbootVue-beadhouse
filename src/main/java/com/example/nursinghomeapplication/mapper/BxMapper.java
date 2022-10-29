package com.example.nursinghomeapplication.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nursinghomeapplication.entity.BX.ReportForRepair;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface BxMapper extends BaseMapper<ReportForRepair> {
     List<Map<String,Object>> getBxList();
}
