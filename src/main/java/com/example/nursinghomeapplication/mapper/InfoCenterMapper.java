package com.example.nursinghomeapplication.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nursinghomeapplication.entity.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 28159
 */
@Mapper
public interface InfoCenterMapper extends BaseMapper<News> {
    /**
     * 获取全部新闻非停用状态
     *
     * @return
     */
    public List<News> getNews();

    /**
     *  获取全部新闻非删除状态
     * @return
     */
    public List<Map<String, Object>> getNewsIsAdmin();

    //获取数据库版本号
    public String getVersion();
}
