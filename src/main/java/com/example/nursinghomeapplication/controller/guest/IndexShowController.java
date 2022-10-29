package com.example.nursinghomeapplication.controller.guest;


import com.example.nursinghomeapplication.controller.BaseController;
import com.example.nursinghomeapplication.entity.News;
import com.example.nursinghomeapplication.entity.food.FoodList;
import com.example.nursinghomeapplication.entity.guest.Content;
import com.example.nursinghomeapplication.entity.guest.Visitors;
import com.example.nursinghomeapplication.entity.home.LeaveAndMessage;
import com.example.nursinghomeapplication.service.IContentService;
import com.example.nursinghomeapplication.util.JsonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author guofan
 * @since 2021-12-02
 */
@RestController
@RequestMapping("/guest")
public class IndexShowController extends BaseController {
    @Resource
    private IContentService contentService;

    /**
     * 获取主页新闻内容
     *
     * @param type
     * @return
     */
    @PostMapping("/index/{type}")
    public JsonResult<List<News>> fingByType(@PathVariable("type") Integer type) {
        if (type == null) {
            return new JsonResult<>(notFound);
        }
        List<News> result = contentService.message(type);
        return new JsonResult<>(OK, result);
    }

    @GetMapping("/getFoodList")
    public JsonResult<List<FoodList>> getFoodList() {
        List<FoodList> data = contentService.getFoodList();
        return new JsonResult<List<FoodList>>(OK, data);
    }

    //插入留言
    @PostMapping("/saveLeaveAndMessage")
    public JsonResult<Content> saveLeaveAndMessage(@RequestBody LeaveAndMessage leaveAndMessage) {
        leaveAndMessage.setCreateUser("guest");
        contentService.saveLeaveAndMessage(leaveAndMessage);
        return new JsonResult<>(OK);
    }

    //插入访客记录
    @PostMapping("/saveVisitor")
    public JsonResult<Content> saveVisitor(@RequestBody Visitors visitors) {
        visitors.setCreateUser("guest");
        contentService.saveVisitor(visitors);
        return new JsonResult<>(OK);
    }

    //获取访客记录
    @PostMapping("/getVisitor")
    public JsonResult<?> getVisitor(@RequestBody Map<String, Object> data) {
        return new JsonResult<>(OK,  contentService.getVisitor(data));
    }
    //删除访客记录
    @PostMapping("/updateVisitor")
    public JsonResult<Content> deleteVisitor(@RequestBody Visitors visitors) {
        contentService.updateVisitor(visitors);
        return new JsonResult<>(OK);
    }

}
