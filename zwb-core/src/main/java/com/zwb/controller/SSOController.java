package com.zwb.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.zwb.util.AjaxJson;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试: 同域单点登录
 * @author kong
 */
@Api(tags = "sa-token-sso")
@RestController
@RequestMapping("/sso/")
public class SSOController {

	// 测试：进行登录
	@RequestMapping("doLogin")
	public AjaxJson doLogin(@RequestParam(defaultValue = "10001") String id) {
		System.out.println("---------------- 进行登录 ");
		StpUtil.setLoginId(id);
		return AjaxJson.getSuccess("登录成功: " + id);
	}

	// 测试：是否登录
	@RequestMapping("isLogin")
	public AjaxJson isLogin() {
		System.out.println("---------------- 是否登录 ");
		boolean isLogin = StpUtil.isLogin();
		Object loginId = StpUtil.getLoginId();
		return AjaxJson.getSuccess("是否登录: " + isLogin+"aa"+loginId);
	}

}
