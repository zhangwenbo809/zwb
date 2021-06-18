package com.zwb.mapper;

import com.zwb.pojo.Permission;
import com.zwb.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author zwb
 * @since 2021-06-08
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("select p.* from zwb_permisson p RIGHT  JOIN zwb_role_permisson  rp on p.menu_id=rp.permisson_id LEFT JOIN zwb_role r on r.role_id=rp.role_id LEFT JOIN\n" +
            " zwb_user_role zr on r.role_id=zr.role_id LEFT JOIN zwb_user z on z.user_id =zr.user_id where z.username =#{0} ")
    List<Permission> findPermissionByUserName(String username);
}
