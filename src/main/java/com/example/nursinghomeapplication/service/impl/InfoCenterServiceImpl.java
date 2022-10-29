package com.example.nursinghomeapplication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.nursinghomeapplication.entity.BX.ReportForRepair;
import com.example.nursinghomeapplication.entity.Healthpunch;
import com.example.nursinghomeapplication.entity.Leave;
import com.example.nursinghomeapplication.entity.News;
import com.example.nursinghomeapplication.entity.NewsType;
import com.example.nursinghomeapplication.entity.configInfoEntity.ListInfo;
import com.example.nursinghomeapplication.entity.drug.DrugList;
import com.example.nursinghomeapplication.entity.drug.PersonnelDrug;
import com.example.nursinghomeapplication.entity.food.FoodList;
import com.example.nursinghomeapplication.entity.food.PersonnelFoods;
import com.example.nursinghomeapplication.entity.guest.NIndexIntroduce;
import com.example.nursinghomeapplication.entity.home.LeaveAndMessage;
import com.example.nursinghomeapplication.entity.personnel.PersonUser;
import com.example.nursinghomeapplication.entity.user.User;
import com.example.nursinghomeapplication.mapper.*;
import com.example.nursinghomeapplication.mapper.configInfo.ListInfoMapper;
import com.example.nursinghomeapplication.mapper.drug.DrugListMapper;
import com.example.nursinghomeapplication.mapper.drug.PersonnelDrugMapper;
import com.example.nursinghomeapplication.mapper.food.FoodListMapper;
import com.example.nursinghomeapplication.mapper.food.PersonnelFoodsMapper;
import com.example.nursinghomeapplication.mapper.guest.NIndexIntroduceMapper;
import com.example.nursinghomeapplication.mapper.home.LeaveAndMessageMapper;
import com.example.nursinghomeapplication.mapper.personnel.PersonMapper;
import com.example.nursinghomeapplication.mapper.vo.LeaveUser;
import com.example.nursinghomeapplication.service.InfoCenterService;
import com.example.nursinghomeapplication.service.ex.RunServiceException;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/6
 * \* 时间: 15:09
 * \* 乌漆嘛黑码神保佑没有bug:
 * \新闻控制中心
 */
@Service
public class InfoCenterServiceImpl implements InfoCenterService {

    private static final Logger log = LoggerFactory.getLogger(InfoCenterService.class);
    @Resource
    InfoCenterMapper infoCenterMapper;
    @Resource
    NewTypeMapper newTypeMapper;
    @Resource
    BxMapper bxMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    ListInfoMapper listInfoMapper;
    @Resource
    LeaveMapper leaveMapper;
    @Resource
    DrugListMapper drugListMapper;

    @Resource
    NIndexIntroduceMapper nIndexIntroduceMapper;
    @Resource
    HealthpunchMapper healthpunchMapper;
    @Resource
    PersonMapper personMapper;

    @Resource
    FoodListMapper foodListMapper;

    @Resource
    LeaveAndMessageMapper leaveAndMessageMapper;

    @Resource
    PersonnelDrugMapper personnelDrugMapper;

    @Resource
    PersonnelFoodsMapper personnelFoodsMapper;

    @Override
    public List<News> getNewsList() {
        return infoCenterMapper.getNews();
    }

    @Override
    public boolean uploadBx(ReportForRepair reportForRepair) {
        //根据token获取到的值来查询名字
        reportForRepair.setCreateUser(userMapper.selectById(reportForRepair.getUserId()).getName());
        int result = bxMapper.insert(reportForRepair);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<ListInfo> getSSListInfo() {
        return listInfoMapper.selectList(null);
    }

    @Override
    public List<ReportForRepair> getBxApply(User user) {
        QueryWrapper<ReportForRepair> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", user.getId());
        List<ReportForRepair> reportForRepairs = bxMapper.selectList(wrapper);
        //精简文本减低传输延迟
        for (ReportForRepair report : reportForRepairs) {
            report.setBxBody(report.getBxBody().length() > 10 ? report.getBxBody().substring(0, 10) + "..." : report.getBxBody());
        }
        return reportForRepairs;
    }

    @Override
    public ReportForRepair findDetailedById(String id) {
        return bxMapper.selectById(id);
    }

    @Override
    public void updateBx(ReportForRepair report) {
        ReportForRepair repair = new ReportForRepair();
        repair.setId(report.getId());
        if (report.getBxTitle() != null && report.getBxBody() != null) {
            repair.setBxTitle(report.getBxTitle());
            repair.setBxBody(report.getBxBody());
        }
        if (report.getErrorReason() != null) {
            repair.setErrorReason(report.getErrorReason());
        }
        if (report.getStatus() != null) {
            repair.setStatus(report.getStatus());
        }
        repair.setModifiedUser(report.getModifiedUser());
        bxMapper.updateById(repair);
    }

    //提交请假信息
    @Override
    public void AskForLeave(Leave leave) {
        log.info("提交请假信息入参:{}", leave);
        //防呆设计防止有叼毛
        leave.setId(null);
        leave.setModifiedUser(null);
        leave.setStatus(1);
        int insert = leaveMapper.insert(leave);
        if (insert == 1) {
            log.info("插入数据成功");
        } else {
            log.info("影响行数不正常{}", insert);
            throw new RunServiceException("非法操作的异常");
        }
    }

    @Override
    public News getNewsById(Integer id) {
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        wrapper.ne("status", -1);
        wrapper.eq("id", id);
        return infoCenterMapper.selectOne(wrapper);
    }

    @Override
    public boolean setNewsToRead(Integer id) {
        News news = new News();
        news.setId(id);
        news.setStatus(2);
        int i = infoCenterMapper.updateById(news);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<DrugList> getDrugList() {
        QueryWrapper<DrugList> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        return drugListMapper.selectList(wrapper);
    }

    @Override
    public List<User> getAllUser() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("admin", 0);
        wrapper.eq("status", 1);
        List<User> list = userMapper.selectList(wrapper);
        list.forEach(item -> {
            item.setPassword(null);
            item.setSalt(null);
        });
        return list;
    }

    @Override
    public List<ReportForRepair> getBxList() {
        QueryWrapper<ReportForRepair> wrapper = new QueryWrapper<>();
        wrapper.ne("status", 0);
        List<ReportForRepair> list = bxMapper.selectList(wrapper);
        list.forEach(item -> {
            String[] split = item.getBxAddresses().toString().split("&");
            QueryWrapper<ListInfo> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("ss_name_id", split[0]);
            ListInfo info = listInfoMapper.selectOne(wrapper1);
            item.setBxAddresses(info.getSsName() + split[1] + "层" + split[2] + "室");
        });
        return list;
    }

    @Override
    public List<User> getListByName(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name", name);
        wrapper.eq("admin", 0);
        wrapper.eq("status", 1);
        return userMapper.selectList(wrapper);
    }

    @Override
    public ReportForRepair getErrorReasonById(Integer id) {
        return bxMapper.selectById(id);
    }

    @Override
    public List<LeaveUser> getAllLeaveForUser() {
        List<LeaveUser> list = leaveMapper.getAllForUser();
        for (LeaveUser item : list) {
            try {
                String[] split = item.getAddress().split("&");
                QueryWrapper<ListInfo> wrapper1 = new QueryWrapper<>();
                wrapper1.eq("ss_name_id", split[0]);
                ListInfo info = listInfoMapper.selectOne(wrapper1);
                item.setAddress(info.getSsName() + split[1] + "层" + split[2] + "室");
            }catch (Exception e){
                log.info("获取地址异常");
                continue;
            }
        }
//        list.forEach(item -> {
//            String[] split = item.getAddress().split("&");
//            QueryWrapper<ListInfo> wrapper1 = new QueryWrapper<>();
//            wrapper1.eq("ss_name_id", split[0]);
//            ListInfo info = listInfoMapper.selectOne(wrapper1);
//            item.setAddress(info.getSsName() + split[1] + "层" + split[2] + "室");
//        });
        return list;
    }

    @Override
    public boolean setLeaveStatusById(Leave leave) {
        return leaveMapper.updateById(leave) > 0 ? true : false;
    }

    @Override
    public Leave getLeaveErrorReasonById(Integer id) {
        return leaveMapper.selectById(id);
    }

    @Override
    public List<NewsType> getNewsType() {
        return newTypeMapper.selectList(null);
    }

    @Override
    public boolean insertNews(News news) {
        return infoCenterMapper.insert(news) > 0;
    }

    @Override
    public Map<String, Object> getNewsIsAdmin(Map<String, Object> data) {
        Integer current = 1;
        Integer size = 10;
        try {
            current = data.get("current") == null ? current : (Integer) data.get("current");
            size = data.get("size") == null ? size : (Integer) data.get("size");
        } catch (Exception e) {
            e.printStackTrace();
        }
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        try {
            if (!StringUtil.isNullOrEmpty(data.get("like").toString())) {
                wrapper.like("title", data.get("like"));
            }
        } catch (Exception e) {
            log.info("没有like条件，现在为初始化页面时间");
        }
        wrapper.ne("status", -1);
        Page<News> userPage = new Page<>(current, size);
        IPage<News> userIPage = infoCenterMapper.selectPage(userPage, wrapper);
        Map<String, Object> map = new HashMap<>();
        List<News> list = userIPage.getRecords();
        map.put("list", userIPage.getRecords());
        map.put("pages", userIPage.getPages());
        map.put("total", userIPage.getTotal());
        return map;
    }

    @Override
    public boolean changeNews(News news) {
        return infoCenterMapper.updateById(news) > 0;
    }

    @Override
    public boolean insertnIndexIntroduce(NIndexIntroduce nIndexIntroduce) {
        if (nIndexIntroduce.getId() != null) {
            return nIndexIntroduceMapper.updateById(nIndexIntroduce) > 0;
        }
        return nIndexIntroduceMapper.insert(nIndexIntroduce) > 0;
    }

    @Override
    public Map<String, Object> getIndexIntroduce(Map<String, Object> data) {
        Integer current = 1;
        Integer size = 10;
        try {
            current = data.get("current") == null ? current : (Integer) data.get("current");
            size = data.get("size") == null ? size : (Integer) data.get("size");
        } catch (Exception e) {
            e.printStackTrace();
        }
        QueryWrapper<NIndexIntroduce> wrapper = new QueryWrapper<>();
        try {
            if (!StringUtil.isNullOrEmpty(data.get("like").toString())) {
                wrapper.like("name", data.get("like"));
            }
        } catch (Exception e) {
            log.info("没有like条件，现在为初始化页面时间");
        }
        wrapper.ne("status", -1);
        Page<NIndexIntroduce> userPage = new Page<>(current, size);
        IPage<NIndexIntroduce> userIPage = nIndexIntroduceMapper.selectPage(userPage, wrapper);
        Map<String, Object> map = new HashMap<>();
        List<NIndexIntroduce> list = userIPage.getRecords();
        map.put("list", userIPage.getRecords());
        map.put("pages", userIPage.getPages());
        map.put("total", userIPage.getTotal());
        return map;
    }

    @Override
    public NIndexIntroduce getIndexIntroduceById(Integer id) {
        return nIndexIntroduceMapper.selectOne(new QueryWrapper<NIndexIntroduce>().eq("id", id));
    }

    @Override
    public NIndexIntroduce getIndexIntroduceByType(String type) {
        return nIndexIntroduceMapper.selectOne(new QueryWrapper<NIndexIntroduce>().eq("type", type).eq("status", 1));
    }

    @Override
    public Map<String, Object> getHealthpunchList(Map<String, Object> data) {
//        return healthpunchMapper.selectList(new QueryWrapper<Healthpunch>().eq("status",1));
        Integer current = 1;
        Integer size = 10;
        try {
            current = data.get("current") == null ? current : (Integer) data.get("current");
            size = data.get("size") == null ? size : (Integer) data.get("size");
        } catch (Exception e) {
            e.printStackTrace();
        }//设置条件
        QueryWrapper<Healthpunch> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        try {
            if (!StringUtil.isNullOrEmpty(data.get("like").toString())) {
                wrapper.like("name", data.get("like"));
            }
        } catch (Exception e) {
            log.info("没有like条件，现在为初始化页面时间");
        }
        Page<Healthpunch> userPage = new Page<>(current, size);
        IPage<Healthpunch> userIPage = healthpunchMapper.selectPage(userPage, wrapper);
        List<Healthpunch> userList = userIPage.getRecords();
        for (Healthpunch item : userList) {
            try {
                QueryWrapper<PersonUser> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("user_id", item.getPersonId());
                queryWrapper.eq("status", 1);
                PersonUser personUser = personMapper.selectList(queryWrapper).get(0);
                item.setPersonId(personUser.getName());
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
    public List<Healthpunch> getHealthpunchById(Integer id, String startTime, String endTime, boolean isUpdate) {
        QueryWrapper<Healthpunch> queryWrapper = new QueryWrapper<Healthpunch>()
                .eq("status", 1).eq("person_id", id);
//
//        if (isUpdate) {
//            queryWrapper.eq("id", id);
//        }else {
//            queryWrapper.eq("person_id", id);
//        }
        if (!StringUtil.isNullOrEmpty(startTime)) {
            queryWrapper.between("create_time", startTime, endTime);
        }
        ArrayList<Healthpunch> healthpunches = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            healthpunches.add(new Healthpunch());
        }
        List<Healthpunch> result = healthpunchMapper.selectList(queryWrapper);
        if (result.size()==0) {
            return result;
        }
        if (isUpdate && result.size()==1) {
                return result;
        }
        result.forEach(item -> {
            Calendar cal = Calendar.getInstance();
            cal.setTime(item.getCreateTime());
            int w = cal.get(Calendar.DAY_OF_WEEK) - 2;
            healthpunches.set(w, item);
        });
        return healthpunches;
    }

    @Override
    public Map<String, Object> getInitViewDrugPage(Map<String, Object> data) {
        Integer current = 1;
        Integer size = 10;
        try {
            current = data.get("current") == null ? current : (Integer) data.get("current");
            size = data.get("size") == null ? size : (Integer) data.get("size");
        } catch (Exception e) {
            e.printStackTrace();
        }//设置条件
        QueryWrapper<DrugList> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        try {
            if (!StringUtil.isNullOrEmpty(data.get("like").toString())) {
                wrapper.like("name", data.get("like"));
            }
        } catch (Exception e) {
            log.info("没有like条件，现在为初始化页面时间");
        }
        Page<DrugList> userPage = new Page<>(current, size);
        IPage<DrugList> userIPage = drugListMapper.selectPage(userPage, wrapper);
        List<DrugList> userList = userIPage.getRecords();
//        for (Healthpunch item : userList) {
//            try {
//                QueryWrapper<PersonUser> queryWrapper = new QueryWrapper<>();
//                queryWrapper.eq("user_id", item.getPersonId());
//                queryWrapper.eq("status", 1);
//                PersonUser personUser = personMapper.selectList(queryWrapper).get(0);
//                item.setPersonId(personUser.getName());
//            } catch (Exception e) {
//                log.info("未找到数据");
//            }
//        }
        Map<String, Object> map = new HashMap<>(3);
        map.put("list", userList);
        map.put("pages", userIPage.getPages());
        map.put("total", userIPage.getTotal());
        return map;
    }

    @Override
    public boolean addDrug(DrugList drugList) {
        drugList.setId(null);
        return drugListMapper.insert(drugList) > 0;
    }

    @Override
    public DrugList findMedlicineById(Integer id) {
        return drugListMapper.selectById(id);
    }

    @Override
    public boolean updateMedlicine(DrugList drugList) {
        return drugListMapper.updateById(drugList) > 0;
    }

    @Override
    public Map<String, Object> getInitViewFoodListPage(Map<String, Object> data) {
        Integer current = 1;
        Integer size = 10;
        try {
            current = data.get("current") == null ? current : (Integer) data.get("current");
            size = data.get("size") == null ? size : (Integer) data.get("size");
        } catch (Exception e) {
            e.printStackTrace();
        }//设置条件
        QueryWrapper<FoodList> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        try {
            if (!StringUtil.isNullOrEmpty(data.get("like").toString())) {
                wrapper.like("name", data.get("like"));
            }
        } catch (Exception e) {
            log.info("没有like条件，现在为初始化页面时间");
        }
        Page<FoodList> userPage = new Page<>(current, size);
        IPage<FoodList> userIPage = foodListMapper.selectPage(userPage, wrapper);
        List<FoodList> userList = userIPage.getRecords();
//        for (Healthpunch item : userList) {
//            try {
//                QueryWrapper<PersonUser> queryWrapper = new QueryWrapper<>();
//                queryWrapper.eq("user_id", item.getPersonId());
//                queryWrapper.eq("status", 1);
//                PersonUser personUser = personMapper.selectList(queryWrapper).get(0);
//                item.setPersonId(personUser.getName());
//            } catch (Exception e) {
//                log.info("未找到数据");
//            }
//        }
        Map<String, Object> map = new HashMap<>(3);
        map.put("list", userList);
        map.put("pages", userIPage.getPages());
        map.put("total", userIPage.getTotal());
        return map;
    }

    @Override
    public boolean addFood(FoodList foodList) {
        foodList.setId(null);
        return foodListMapper.insert(foodList) > 0;
    }

    @Override
    public boolean updateFood(FoodList foodList) {
        return foodListMapper.updateById(foodList) > 0;
    }

    @Override
    public FoodList findFoodById(Integer id) {
        return foodListMapper.selectById(id);
    }

    @Override
    public boolean updateHealthpunch(Healthpunch healthpunch) {
        return healthpunchMapper.updateById(healthpunch) > 0;
    }

    @Override
    public boolean changeIndexIntroduce(NIndexIntroduce nIndexIntroduce) {
        return nIndexIntroduceMapper.updateById(nIndexIntroduce) > 0;
    }

    @Override
    public Map<String, Object> getMessageList(Map<String, Object> data) {
        Integer current = 1;
        Integer size = 10;
        try {
            current = data.get("current") == null ? current : (Integer) data.get("current");
            size = data.get("size") == null ? size : (Integer) data.get("size");
        } catch (Exception e) {
            e.printStackTrace();
        }//设置条件
        QueryWrapper<LeaveAndMessage> wrapper = new QueryWrapper<>();
        wrapper.ne("status", 0);
        try {
            if (!StringUtil.isNullOrEmpty(data.get("like").toString())) {
                wrapper.like("name", data.get("like"));
            }
        } catch (Exception e) {
            log.info("没有like条件，现在为初始化页面时间");
        }
        Page<LeaveAndMessage> userPage = new Page<>(current, size);
        IPage<LeaveAndMessage> userIPage = leaveAndMessageMapper.selectPage(userPage, wrapper);
        List<LeaveAndMessage> userList = userIPage.getRecords();
        Map<String, Object> map = new HashMap<>(3);
        map.put("list", userList);
        map.put("pages", userIPage.getPages());
        map.put("total", userIPage.getTotal());
        return map;
    }

    @Override
    public boolean updateMessage(LeaveAndMessage leaveAndMessage) {
        return leaveAndMessageMapper.updateById(leaveAndMessage) > 0;
    }

    @Override
    public List<Leave> getLeaveList(Leave leave) {
        return leaveMapper.selectList(new QueryWrapper<>(leave).ne("status", -1).eq("user_id", leave.getUserId()));
    }

    @Override
    public List<DrugList> getDrugTypeList() {
        return drugListMapper.selectList(new QueryWrapper<DrugList>().eq("status", 1));
    }

    @Override
    public boolean updatePersonnelDrug(PersonnelDrug personnelDrug) {
        if (personnelDrug.getId() != null) {
            return personnelDrugMapper.updateById(personnelDrug) > 0;
        } else {
            return personnelDrugMapper.insert(personnelDrug) > 0;
        }
    }

    @Override
    public List<FoodList> getFoodTypeList() {
        return foodListMapper.selectList(new QueryWrapper<FoodList>().eq("status", 1));
    }

    @Override
    public boolean updatePersonnelFood(PersonnelFoods personnelFoods) {
        if (personnelFoods.getId() != null) {
            return personnelFoodsMapper.updateById(personnelFoods) > 0;
        } else {
            return personnelFoodsMapper.insert(personnelFoods) > 0;
        }
    }
}
