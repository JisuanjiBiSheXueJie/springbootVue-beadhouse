package com.example.nursinghomeapplication.controller;

import com.example.nursinghomeapplication.entity.config.ConfigType;
import com.example.nursinghomeapplication.entity.personnel.PersonUser;
import com.example.nursinghomeapplication.entity.user.User;
import com.example.nursinghomeapplication.service.PersonService;
import com.example.nursinghomeapplication.service.UserService;
import com.example.nursinghomeapplication.util.JsonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/15
 * \* 时间: 14:32
 * \* 乌漆嘛黑码神保佑没有bug:
 * \客户管理控制
 */
@RestController
@RequestMapping("/person")
public class PersonController extends BaseController {
    @Resource
    PersonService personService;
    @Resource
    UserService userService;

    /**
     * 根据当前用户获取全部客户信息
     *
     * @return 客户集合
     */
    @GetMapping("/getPersonListByUser")
    public JsonResult<List<PersonUser>> getPersonListByUser(HttpServletRequest request) {
        User user = userService.getUser(request);
        return new JsonResult<>(OK, personService.getPersonList(user.getId()));
    }

    /**
     * 根据客户id获取药物信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getPersonnelDrug/{id}")
    public JsonResult<?> getPersonnelDrug(@PathVariable Integer id) {
        return new JsonResult<>(OK, personService.getPersonDrug(id));
    }

    @GetMapping("/getPerFoodsById/{id}")
    public JsonResult<?> getPerFoodsById(@PathVariable Integer id) {
        return new JsonResult<>(OK, personService.getPerFoodsById(id));
    }

    @GetMapping("/getPersonnelUser/{id}")
    public JsonResult<?> getPersonnelUser(@PathVariable Integer id) {
        return new JsonResult<>(OK, personService.getPersonnelUser(id));
    }

    @PostMapping("/getPersonPage")
    public JsonResult<?> getPersonList(@RequestBody Map<String, Object> data) {
        return new JsonResult<>(OK, personService.getPersonPage(data));
    }

    @GetMapping("/getPersonMessById/{id}")
    public JsonResult<?> getPersonMessById(@PathVariable Integer id) {
        return new JsonResult<>(OK, personService.getPersonMessById(id));
    }

    @PostMapping("/delPersonById/{id}")
    public JsonResult<?> delPersonById(@PathVariable Integer id, HttpServletRequest request) {
        PersonUser personUser = new PersonUser();
        personUser.setModifiedUser(userService.getUser(request).getName());
        personUser.setId(id);
        return new JsonResult<>(OK, personService.delPersonById(personUser));
    }

    //添加客户
    @PostMapping("/addPerson")
    public JsonResult<?> addPerson(@RequestBody PersonUser personUser, HttpServletRequest request) {
        if (personUser.getId() == null) {
            personUser.setCreateUser(userService.getUser(request).getName());
        } else {
            personUser.setModifiedUser(userService.getUser(request).getName());
        }
        return new JsonResult<>(OK, personService.addPerson(personUser));
    }
    //根据id获取客户信息
    @PostMapping("/getPersonById/{id}")
    public JsonResult<?> getPersonById(@PathVariable Integer id) {
        return new JsonResult<>(OK, personService.getPersonById(id));
    }
}
