package com.zwb.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zwb.pojo.User;
import com.zwb.service.UserService;
import com.zwb.util.YunResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author zwb
 * @since 2021-06-08
 */
@Api(tags = "sa-token登录")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    // 查询用户
    @ApiOperation(value = "查询用户" ,notes="查询用户",httpMethod="GET")
    @RequestMapping("/user/getById")
    public User adminLogin(@RequestParam("id")  Long id){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        User one = userService.getOne(queryWrapper);
        return one;
    }
    // 登录
    @ApiOperation(value = "登录" ,notes="登录",httpMethod="GET")
    @RequestMapping(value="/login/admin_login",produces = "application/json;charset=utf-8")
    public YunResult adminLogin(@RequestParam("username") String username, @RequestParam("password") String password){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);

        queryWrapper.eq("password", password);
        User one = userService.getOne(queryWrapper);
        StpUtil.setLoginId(one.getUsername());
        return YunResult.createBySuccess("success",one);
    }
}
