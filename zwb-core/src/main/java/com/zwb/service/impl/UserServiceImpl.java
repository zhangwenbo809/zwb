package com.zwb.service.impl;

import com.zwb.pojo.User;
import com.zwb.mapper.UserMapper;
import com.zwb.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author zwb
 * @since 2021-06-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    UserMapper userMapper;
}
