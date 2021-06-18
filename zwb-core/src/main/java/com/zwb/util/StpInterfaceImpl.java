package com.zwb.util;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zwb.mapper.RoleMapper;
import com.zwb.mapper.UserMapper;
import com.zwb.mapper.UserRoleMapper;
import com.zwb.pojo.Permission;
import com.zwb.pojo.Role;
import com.zwb.pojo.User;
import com.zwb.pojo.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限验证接口扩展 
 */
@Component	// 打开此注解，保证此类被springboot扫描，即可完成sa-token的自定义权限验证扩展 
public class StpInterfaceImpl implements StpInterface {
	@Resource
	private UserMapper userDao;
	@Resource
	private RoleMapper roleMapper;
	@Resource
	private UserRoleMapper userroleMapper;
	/**
	 * 返回一个账号所拥有的权限码集合 
	 */
	@Override
	public List<String> getPermissionList(Object username, String loginKey) {
		// 查出当前用户的权限信息
		List<String> list = new ArrayList<String>();
		List<Permission> permissions = userDao.findPermissionByUserName(username.toString());
		permissions.forEach(l -> {
			list.add(l.getPerms());
		});
		return list;
	}

	/**
	 * 返回一个账号所拥有的角色标识集合 
	 */
	@Override
	public List<String> getRoleList(Object loginId, String loginKey) {
		// 本list仅做模拟，实际项目中要根据具体业务逻辑来查询角色
		List<String> list = new ArrayList<String>();
		Long userId = userDao.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, loginId.toString())).getUserId();
		List<UserRole> user_roles = userroleMapper.selectList(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, userId));
		user_roles.forEach( l -> {
			Long roleId = l.getRoleId();
			Role role = roleMapper.selectOne(Wrappers.<Role>lambdaQuery().eq(Role::getRoleId, roleId));
			list.add(role.getRoleName());
		});
		return list;
	}

}
