package com.example.nursinghomeapplication.service;


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
import com.example.nursinghomeapplication.entity.user.User;
import com.example.nursinghomeapplication.mapper.vo.LeaveUser;

import java.util.List;
import java.util.Map;

public interface InfoCenterService {
    List<News> getNewsList();

    boolean uploadBx(ReportForRepair reportForRepair);

    List<ListInfo> getSSListInfo();

    List<ReportForRepair> getBxApply(User user);

    ReportForRepair findDetailedById(String id);

    void updateBx(ReportForRepair report);

    //请假申请
    void AskForLeave(Leave leave);

    //根据id获取内容
    News getNewsById(Integer id);

    boolean setNewsToRead(Integer id);

    List<DrugList> getDrugList();

    List<User> getAllUser();

    List<ReportForRepair> getBxList();

    List<User> getListByName(String name);

    ReportForRepair getErrorReasonById(Integer id);

    List<LeaveUser> getAllLeaveForUser();

    boolean setLeaveStatusById(Leave leave);

    Leave getLeaveErrorReasonById(Integer id);

    List<NewsType> getNewsType();

    boolean insertNews(News news);

    Map<String,Object> getNewsIsAdmin(Map<String, Object> data);

    boolean changeNews(News news);

    //插入分院发布信息
    boolean insertnIndexIntroduce(NIndexIntroduce nIndexIntroduce);

    //获取分院发布信息
    Map<String, Object>getIndexIntroduce(Map<String, Object> data);

    NIndexIntroduce getIndexIntroduceById(Integer id);

    NIndexIntroduce getIndexIntroduceByType(String type);

    Map<String, Object> getHealthpunchList(Map<String, Object> data);

    List<Healthpunch> getHealthpunchById(Integer id,String startTime,String endTime,boolean isUpdate);

    Map<String, Object> getInitViewDrugPage(Map<String, Object> data);

    boolean addDrug(DrugList drugList);
    boolean updateMedlicine(DrugList drugList);

    DrugList findMedlicineById(Integer id);

    Map<String, Object> getInitViewFoodListPage(Map<String, Object> data);

    boolean addFood(FoodList foodList);
    boolean updateFood(FoodList foodList);

    FoodList findFoodById(Integer id);

    boolean updateHealthpunch(Healthpunch healthpunch);

    boolean changeIndexIntroduce(NIndexIntroduce nIndexIntroduce);

    Map<String, Object>  getMessageList(Map<String, Object> data);

    boolean updateMessage(LeaveAndMessage leaveAndMessage);

    List<Leave> getLeaveList(Leave leave);

    List<DrugList> getDrugTypeList();

    boolean updatePersonnelDrug(PersonnelDrug personnelDrug);

    List<FoodList> getFoodTypeList();

    boolean updatePersonnelFood(PersonnelFoods personnelFoods);
}
