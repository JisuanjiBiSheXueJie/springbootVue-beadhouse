package com.example.nursinghomeapplication.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nursinghomeapplication.entity.Leave;
import com.example.nursinghomeapplication.mapper.vo.LeaveUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface LeaveMapper extends BaseMapper<Leave> {
    List<LeaveUser> getAllForUser();
}
