package com.example.nursinghomeapplication.controller;

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
import com.example.nursinghomeapplication.service.InfoCenterService;
import com.example.nursinghomeapplication.service.UserService;
import com.example.nursinghomeapplication.service.UtilService;
import com.example.nursinghomeapplication.service.ex.RunServiceException;
import com.example.nursinghomeapplication.util.JsonResult;
import com.example.nursinghomeapplication.util.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/10
 * \* 时间: 10:58
 * \* 乌漆嘛黑码神保佑没有bug:
 * \获取信息控制层
 */
@RestController
@RequestMapping("/info")
public class InfoCenterController extends BaseController {
    /**
     * log4j
     */
    private static final Logger log = LoggerFactory.getLogger(InfoCenterController.class);
    @Resource
    TokenService tokenService;
    @Resource
    InfoCenterService centerService;
    @Resource
    UtilService utilService;
    @Resource
    UserService userService;

    /**
     * 获取全部通知信息
     *
     * @return 返回所有的信息不包括已删除
     */
    @GetMapping("/getNews")
    public JsonResult<?> getNews(HttpServletRequest request) {
        log.info("用户申请接口获取信息");
        List<News> newsList = centerService.getNewsList();
        return new JsonResult<>(OK, newsList);
    }

    /**
     * 获取新闻类型
     *
     * @return 新闻类型列表
     */
    @GetMapping("/getNewsType")
    public JsonResult<List<NewsType>> getNewsType() {
        log.info("正在获取新闻种类中...");
        return new JsonResult<>(OK, centerService.getNewsType());
    }

    /**
     * 维修申请
     *
     * @param reportForRepair 表单对象
     * @param request         请求头
     * @return 返回状态码
     */
    @PostMapping("/ReportToMaintenance")
    public JsonResult<?> ReportToMaintenance(@RequestBody ReportForRepair reportForRepair, HttpServletRequest request) {
        log.info("用户申请了报修");
        //根据request获取用户信息
        User user = userService.getUser(request);
        if (user == null) {
            throw new RunServiceException("为空的异常");
        }
        //将报修表的userid设置为user的id
        reportForRepair.setUserId(user.getId());
        centerService.uploadBx(reportForRepair);
        return new JsonResult<>(OK);
    }

    /**
     * 获取楼层信息
     *
     * @return 返回一个json所有的楼信息
     */
    @GetMapping("/getSSListInfo")
    public JsonResult<List<ListInfo>> getSSListInfo() {
        return new JsonResult<>(OK, centerService.getSSListInfo());
    }

    /**
     * 获取报修状态
     *
     * @param request 包含token
     * @return 根据token获取id然后根据user_id获取内容
     */
    @GetMapping("/getBxapply")
    public JsonResult<List<ReportForRepair>> getBxapply(HttpServletRequest request) {
        log.info("用户获取了报修状态");
        return new JsonResult<>(OK, centerService.getBxApply(userService.getUser(request)));
    }

    /**
     * 获取报修详细根据报修单据id
     *
     * @param id 单据id
     * @return 返回详细对象
     */
    @GetMapping("/findDetailedById/{id}")
    public JsonResult<ReportForRepair> findDetailedById(@PathVariable String id) {
        return new JsonResult<>(OK, centerService.findDetailedById(id));
    }

    /**
     * userhome初始化页面加载页面信息
     *
     * @param request
     * @return
     */
    @PostMapping("/initView")
    public JsonResult<?> save(HttpServletRequest request) {
        User serviceUser = userService.getUser(request);
        if (serviceUser == null) {
            throw new RunServiceException("为空的异常");
        }
        User user = new User();
        user.setId(serviceUser.getId());
        User result = userService.initView(user);
        return new JsonResult<>(OK, result);
    }

    /**
     * 更新报修表数据根据request中的信息
     *
     * @param report 报修信息
     * @return 状态码
     */
    @PostMapping("/updateBx")
    public JsonResult<Void> updateBx(@RequestBody ReportForRepair report, HttpServletRequest request) {
        report.setModifiedUser(userService.getUser(request).getName());
        centerService.updateBx(report);
        return new JsonResult<>(OK);
    }

    /**
     * 请假申请
     *
     * @param leave   表单
     * @param request 根据它获取用户信息
     * @return 状态码
     */
    @PostMapping("/AskForLeave")
    public JsonResult<Void> AskForLeave(@RequestBody Leave leave, HttpServletRequest request) {
        User user = userService.getUser(request);
        leave.setCreateUser(user.getName());
        leave.setUserId(user.getId());
        centerService.AskForLeave(leave);
        return new JsonResult<>(OK);
    }

    /**
     * 健康打卡
     *
     * @param healthpunch 表单
     * @param request     个人信息
     * @return 状态
     */
    @PostMapping("/Healthpunch")
    public JsonResult<Void> Healthpunch(@RequestBody Healthpunch healthpunch, HttpServletRequest request) {
        User user = userService.getUser(request);
        healthpunch.setCreateUser(user.getName());
        userService.healthpunch(healthpunch);
        return new JsonResult<>(OK);
    }

    /**
     * 获取详细的通知信息
     *
     * @param id 根据通知信息id
     * @return 详细的信息
     */
    @GetMapping("/getNewsById/{id}")
    public JsonResult<News> getNewsById(@PathVariable Integer id) {
        News result = centerService.getNewsById(id);
        centerService.setNewsToRead(result.getId());
        return new JsonResult<>(OK, result);
    }

    @GetMapping("/getInitViewDrug")
    public JsonResult<?> getDrugList() {
        return new JsonResult<>(OK, centerService.getDrugList());
    }

    /**
     * 获取所有的普通用户
     *
     * @return
     */
    @GetMapping("/getAllUser")
    public JsonResult<?> getAllUser() {
        return new JsonResult<>(OK, centerService.getAllUser());
    }

    /**
     * 获取报修列表
     *
     * @return
     */
    @GetMapping("/getBxList")
    public JsonResult<?> getBxList() {
        return new JsonResult<>(OK, centerService.getBxList());
    }

    /**
     * 根据名字查找
     */
    @GetMapping("/getListByName/{name}")
    public JsonResult<?> getListByName(@PathVariable String name) {
        return new JsonResult<>(OK, centerService.getListByName(name));
    }

    @PostMapping("/setBxStatus")
    public JsonResult<?> setStatusIsBackTo(@RequestBody ReportForRepair report, HttpServletRequest request) {
        User user = userService.getUser(request);
        report.setModifiedUser(user.getName());
        centerService.updateBx(report);
        return new JsonResult<>(OK);
    }

    /**
     * 根据id获取打回信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getErrorReasonById/{id}")
    public JsonResult<?> getErrorReasonById(@PathVariable Integer id) {
        return new JsonResult<>(OK, centerService.getErrorReasonById(id));
    }

    /**
     * 霍去病全部外出信息
     *
     * @return
     */
    @GetMapping("/getAllLeaveForUser")
    public JsonResult<?> getAllLeaveForUser() {
        return new JsonResult<>(OK, centerService.getAllLeaveForUser());
    }

    /**
     * 根据id来修改请假状态信息
     *
     * @param leave
     * @param request
     * @return
     */
    @PostMapping("/setLeaveStatusById")
    public JsonResult<?> setLeaveStatus(@RequestBody Leave leave, HttpServletRequest request) {
        User user = userService.getUser(request);
        Leave update = new Leave();
        update.setStatus(leave.getStatus());
        update.setId(leave.getId());
        update.setModifiedUser(user.getName());
        update.setErrorReason(leave.getErrorReason());
        return new JsonResult<>(OK, centerService.setLeaveStatusById(update));
    }

    @GetMapping("/getLeaveErrorReasonById/{id}")
    public JsonResult<?> getLeaveErrorReasonById(@PathVariable Integer id) {
        return new JsonResult<>(OK, centerService.getLeaveErrorReasonById(id));
    }

    /**
     * 插入新闻
     *
     * @param news    前端富文本编辑框装换好成为String格式
     * @param request quest请求信息
     * @return 是否成功
     */
    @PostMapping("/insertNews")
    public JsonResult<?> insertNews(@RequestBody News news, HttpServletRequest request) {
        //防止数据异常
        News result = new News();
        result.setTitle(news.getTitle());
        result.setBody(news.getBody());
        result.setContent(news.getContent());
        result.setFileName(news.getFileName());
        result.setSpecies(news.getSpecies());
        result.setHotNum(news.getHotNum());
        result.setCreateUser(userService.getUser(request).getName());
        result.setType(news.getType());
        result.setStatus(news.getStatus() == null ? 0 : news.getStatus());
        if (centerService.insertNews(result)) {
            return new JsonResult<>(OK, "数据插入成功!");
        }
        return new JsonResult<>(notFound, "数据插入失败!");
    }

    /**
     * admin获取全部信息
     * @param request
     * @return
     */
    @PostMapping("/getNewsIsAdmin")
    public JsonResult<?> getNewsIsAdmin(@RequestBody Map<String, Object> data,HttpServletRequest request) {
        return new JsonResult<>(OK, centerService.getNewsIsAdmin(data));
    }

    /**
     * 获取全部新闻信息
     * @param news
     * @param request
     * @return
     */
    @PostMapping("/changeNews")
    public JsonResult<?> changeNews(@RequestBody News news,HttpServletRequest request) {
        news.setModifiedUser(userService.getUser(request).getName());
        return new JsonResult<>(OK, centerService.changeNews(news));
    }
    /**
     * 院系发布功能
     */
    @PostMapping("/insertIndexIntroduce")
    public JsonResult<?> insertIndexIntroduce(@RequestBody NIndexIntroduce nIndexIntroduce, HttpServletRequest request) {
        nIndexIntroduce.setCreateUser(userService.getUser(request).getName());
        return new JsonResult<>(OK, centerService.insertnIndexIntroduce(nIndexIntroduce));
    }
    //获取院系发布信息
    @PostMapping("/getIndexIntroduce")
    public JsonResult<?> getIndexIntroduce(@RequestBody Map<String, Object> data,HttpServletRequest request) {
        return new JsonResult<>(OK, centerService.getIndexIntroduce(data));
    }//获取院系发布信息
    @GetMapping("/getIndexIntroduceById/{id}")
    public JsonResult<?> getIndexIntroduceById(@PathVariable Integer id,HttpServletRequest request) {
        return new JsonResult<>(OK, centerService.getIndexIntroduceById(id));
    }
    @GetMapping("/getIndexIntroduceByType/{type}")
    public JsonResult<?> getIndexIntroduceByType(@PathVariable String type) {
        return new JsonResult<>(OK, centerService.getIndexIntroduceByType(type));
    }
    //获取健康信息
    @PostMapping("/getHealthpunch")
    public JsonResult<?> getHealthpunch(@RequestBody Map<String, Object> data) {
        return new JsonResult<>(OK, centerService.getHealthpunchList(data));
    }
    //更新健康信息
    @PostMapping("/updateHealthpunch")
    public JsonResult<?> updateHealthpunch(@RequestBody Healthpunch healthpunch) {
        return new JsonResult<>(OK, centerService.updateHealthpunch(healthpunch));
    }
    @PostMapping("/getHealthpunchById")
    public JsonResult<?> getHealthpunchById(@RequestBody Map<String, Object> data) {
        Integer id = (Integer) data.get("id");
        String startTime =null;
        String endTime = null;
        boolean isUpdate= false;
        try {
             startTime = String.valueOf(data.get("startTime"));
             endTime = String.valueOf(data.get("endTime"));
             isUpdate = (boolean) data.get("isUpdate");
        }catch (Exception e){
            log.info("没有找到时间");
        }
        return new JsonResult<>(OK, centerService.getHealthpunchById(id,startTime,endTime,isUpdate));
    }
    @PostMapping("/getInitViewDrugPage")
    public JsonResult<?> getInitViewDrugPage(@RequestBody Map<String, Object> data) {
        return new JsonResult<>(OK, centerService.getInitViewDrugPage(data));
    }
    @PostMapping("/addDrug")
    public JsonResult<?> addDrug(@RequestBody DrugList drugList,HttpServletRequest request) {
        drugList.setCreateUser(userService.getUser(request).getName());
        return new JsonResult<>(OK, centerService.addDrug(drugList));
    }
    @PostMapping("/findMedlicineById/{id}")
    public JsonResult<?> findMedlicineById(@PathVariable Integer id) {
        return new JsonResult<>(OK, centerService.findMedlicineById(id));
    }
    @PostMapping("/updateMedlicine")
    public JsonResult<?> updateMedlicine(@RequestBody DrugList drugList,HttpServletRequest request) {
        drugList.setModifiedUser(userService.getUser(request).getName());
        return new JsonResult<>(OK, centerService.updateMedlicine(drugList));
    }
    @PostMapping("/getInitViewFoodPage")
    public JsonResult<?> getInitViewFoodListPage(@RequestBody Map<String, Object> data) {
        return new JsonResult<>(OK, centerService.getInitViewFoodListPage(data));
    }
    @PostMapping("/addFood")
    public JsonResult<?> addFood(@RequestBody FoodList foodList, HttpServletRequest request) {
        foodList.setCreateUser(userService.getUser(request).getName());
        return new JsonResult<>(OK, centerService.addFood(foodList));
    }
    @PostMapping("/findFoodById/{id}")
    public JsonResult<?> findFoodById(@PathVariable Integer id) {
        return new JsonResult<>(OK, centerService.findFoodById(id));
    }
    @PostMapping("/updateFood")
    public JsonResult<?> updateFood(@RequestBody FoodList foodList,HttpServletRequest request) {
        foodList.setModifiedUser(userService.getUser(request).getName());
        return new JsonResult<>(OK, centerService.updateFood(foodList));
    }
    //changeIndexIntroduce修改主页介绍
    @PostMapping("/changeIndexIntroduce")
    public JsonResult<?> changeIndexIntroduce(@RequestBody NIndexIntroduce nIndexIntroduce) {
        return new JsonResult<>(OK, centerService.changeIndexIntroduce(nIndexIntroduce));
    }
    //获取留言板列表
    @PostMapping("/getMessageList")
    public JsonResult<?> getMessageList(@RequestBody Map<String, Object> data) {
        return new JsonResult<>(OK, centerService.getMessageList(data));
    }
    //修改留言板
    @PostMapping("/updateMessage")
    public JsonResult<?> updateMessage(@RequestBody LeaveAndMessage leaveAndMessage) {
        return new JsonResult<>(OK, centerService.updateMessage(leaveAndMessage));
    }
    //获取请假列表
    @PostMapping("/getLeaveList")
    public JsonResult<?> getLeaveList(HttpServletRequest request) {
        Leave leave = new Leave();
        leave.setUserId(userService.getUser(request).getId());
        return new JsonResult<>(OK, centerService.getLeaveList(leave));
    }
    //获取药品种类列表
    @GetMapping("/getDrugTypeList")
    public JsonResult<?> getDrugTypeList() {
        return new JsonResult<>(OK, centerService.getDrugTypeList());
    }
    //更新n_personnel_drug信息
    @PostMapping("/updatePersonnelDrug")
    public JsonResult<?> updatePersonnelDrug(@RequestBody PersonnelDrug personnelDrug) {
        return new JsonResult<>(OK, centerService.updatePersonnelDrug(personnelDrug));
    }
    //获取n_food信息
    @GetMapping("/getFoodTypeList")
    public JsonResult<?> getFoodTypeList() {
        return new JsonResult<>(OK, centerService.getFoodTypeList());
    }
    //更新n_personnel_food信息
    @PostMapping("/updatePersonnelFood")
    public JsonResult<?> updatePersonnelFood(@RequestBody PersonnelFoods personnelFoods) {
        return new JsonResult<>(OK, centerService.updatePersonnelFood(personnelFoods));
    }
}
