package com.zwb.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import com.alibaba.fastjson.JSONArray;
import com.zwb.config.RedisUtils;
import com.zwb.pojo.User;
import com.zwb.util.JsonUtils;
import com.zwb.util.YunResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zwb
 * @since 2021-03-30
 */
@Api(tags = "数据准备")
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value = "数据准备" ,notes="redis数据准备",httpMethod="GET")
    @GetMapping("/add")
    public YunResult redisTemplate( ) {
        int b=1/0;
        String dic_parent = (String) redisUtils.get("dic_parent");
        if (dic_parent==null){
            List<User> parent=new ArrayList<>();
            User a=new User();
            a.setUserId(3L);
            a.setName("ddd");
            a.setUsername("ddd");
            a.setPassword("ddd");
            a.setSalt("");
            a.setEmail("dd");
            a.setMobile("dd");
            a.setStatus(1);
            a.setDeptId(2L);
            a.setCreateTime(new Date());

            parent.add(a);
            redisUtils.set("dic_parent", JsonUtils.listJson(parent));
        }else{
            List<User> strListToList = JsonUtils.strListToList(dic_parent, User.class);
            System.out.println(strListToList);
        }
        return YunResult.createBySuccess("success");
    }
        /** 
        * @Description: SaMode.AND, 标注一组权限，会话必须全部具有才可通过校验
         * SaMode.OR, 标注一组权限，会话只要具有其一即可通过校验 
        * @Param:  
        * @return:
        * @Author: zwb
        * @Date: 2021/6/16 
        */ 
    @ApiOperation(value = "删除" ,notes="redis数据删除",httpMethod="POST")
    @SaCheckPermission(value = {"zwb:permisson:admin"}, mode = SaMode.OR)
    @PostMapping("/del")
    public YunResult del( @RequestParam("key") String key) {
            String dic_parent = (String) redisUtils.get(key);
        if (dic_parent!=null){
            redisUtils.remove(key);
        }
        return YunResult.createBySuccess("success");
    }
}
