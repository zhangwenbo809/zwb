package com.zwb.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 角色与菜单对应关系
 * </p>
 *
 * @author zwb
 * @since 2021-06-08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("zwb_role_permisson")
@ApiModel(value="RolePermisson对象", description="角色与菜单对应关系")
public class RolePermisson implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private Long roleId;

    @ApiModelProperty(value = "菜单ID")
    @TableField("permisson_id")
    private Long permissonId;


}
