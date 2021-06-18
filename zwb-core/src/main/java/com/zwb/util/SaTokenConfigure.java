package com.zwb.util;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.exception.DisableLoginException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouterUtil;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.MultipartConfigElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * [Sa-Token 权限认证] 配置类 
 * @author kong
 *
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

	/**
	 * 注册sa-token的拦截器，打开注解式鉴权功能 
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 注册路由拦截器，自定义验证规则
		registry.addInterceptor(new SaRouteInterceptor((request, response, handler)->{

			if (!request.getMethod().equals(HttpMethod.OPTIONS.toString())) {
				//SaRouterUtil.match("/**", () -> StpUtil.checkLogin());
				// 根据路由划分模块，不同模块不同鉴权

				/*List<String> patterns=new ArrayList<>();
				patterns.add("/**");
				List<String> excludPatterns=new ArrayList<>();
				excludPatterns.add("/doc.html/**");
				excludPatterns.add("/favicon.ico");
				excludPatterns.add("/swagger**");
				excludPatterns.add("/v2/**");
				excludPatterns.add("/webjars/**");
				excludPatterns.add("/login/admin_login");
				SaRouterUtil.match(patterns, excludPatterns, () -> StpUtil.checkLogin());*/
				// 登录验证 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
				SaRouterUtil.match("/**", "/login/admin_login", () -> StpUtil.checkLogin());
				// 角色认证 -- 拦截以 admin 开头的路由，必须具备[admin]角色或者[super-admin]角色才可以通过认证
				SaRouterUtil.match("/test/**", () -> StpUtil.checkRoleOr("admin", "super-admin"));
				// 权限认证 -- 不同模块, 校验不同权限
				//SaRouterUtil.match("/test/**", () -> StpUtil.checkPermission("admin"));
				// 匹配RESTful风格路由
				SaRouterUtil.match("/article/get/{id}", () -> StpUtil.checkPermission("article"));
				SaRouterUtil.match("/user/**",() -> StpUtil.checkPermission("zwb:permisson:admin"));
			}
		})).addPathPatterns("/**").excludePathPatterns("/favicon.ico","/static/**","/swagger**","/swagger-resources/**","/doc.html/**","/v2/**","/webjars/**");
		//此上为
		//
		// 排除的路径，需要自定义其验证，包括login请求
	}
	
	/**
     * 注册 [sa-token全局过滤器] 
     */
    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
        		
        		// 指定 [拦截路由] 与 [放行路由]
        		.addInclude("/**").addExclude("/favicon.ico","/doc.html/**","/swagger-resources/**","/v2/**","/webjars/**")

        		// 认证函数: 每次请求执行
        		.setAuth(r -> {
					System.out.println("---------- sa全局认证");
					System.out.println(SaHolder.getRequest().getRequestPath());
					// 路由为/test/tes的访问用户必须要有0001的权限
					//SaRouterUtil.match("/user/{id}", () -> StpUtil.checkPermission("0001"));
					SaRouterUtil.match("/user/**", () -> StpUtil.checkPermission("zwb:permisson:admin"));
					// 角色认证 -- 拦截以 admin 开头的路由，必须具备[admin]角色或者[super-admin]角色才可以通过认证
					SaRouterUtil.match("/test/**", () -> StpUtil.checkRoleOr("admin", "super-admin"));

					/*boolean login = StpUtil.isLogin();
					if (!login){
						System.out.println("放行.. ");
					}else{
					}*/
					// SaRouter.match("/test/test", () -> new Object());
				})

        		// 异常处理函数：每次认证函数发生异常时执行此函数 
        		.setError(e -> {
					System.out.println("---------- sa全局异常 ");
        			return AjaxJson.getError(e.getMessage());
        		})
        		
        		// 前置函数：在每次认证函数之前执行
        		.setBeforeAuth(r -> {
        			// ---------- 设置一些安全响应头 ----------
        			SaHolder.getResponse()
        			// 服务器名称 
        			.setServer("sa-server")
        			// 是否可以在iframe显示视图： DENY=不可以 | SAMEORIGIN=同域下可以 | ALLOW-FROM uri=指定域名下可以 
        			.setHeader("X-Frame-Options", "SAMEORIGIN")
        			// 是否启用浏览器默认XSS防护： 0=禁用 | 1=启用 | 1; mode=block 启用, 并在检查到XSS攻击时，停止渲染页面
        			.setHeader("X-Frame-Options", "1; mode=block")
        			// 禁用浏览器内容嗅探 
        			.setHeader("X-Content-Type-Options", "nosniff")
        			;
        		})
        		;
    }


	/**
	 * 跨域配置
	 *
	 * @param registry
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				// 设置允许跨域请求的域名
				.allowedOriginPatterns("*")
				.allowedMethods("POST", "GET", "PUT", "DELETE", "OPTIONS")
				.maxAge(3600)
				// 是否允许发送Cookie
				.allowCredentials(true)
				.allowedHeaders("*");
	}

	/*@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//文件最大KB,MB
		factory.setMaxFileSize(10240000000);
		//设置总上传数据总大小
		factory.setMaxRequestSize();
		return factory.createMultipartConfig();

	}*/
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.ignoreAcceptHeader(true)  // 这里一定要设置为true，才能覆盖原来的默认的xml format
				.defaultContentType(MediaType.APPLICATION_JSON);
	}
}
