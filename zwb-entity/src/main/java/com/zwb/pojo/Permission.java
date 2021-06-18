package com.zwb.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
 * 菜单管理
 * </p>
 *
 * @author zwb
 * @since 2021-06-08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("zwb_permisson")
@ApiModel(value="Permisson对象", description="菜单管理")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    @TableField("perms")
    private String perms;

    @TableField("name")
    private String name;

    @TableField("create_time")
    private Date createTime;


}
