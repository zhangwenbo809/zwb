package com.zwb.service.impl;

import com.zwb.pojo.UserRole;
import com.zwb.mapper.UserRoleMapper;
import com.zwb.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author zwb
 * @since 2021-06-08
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
