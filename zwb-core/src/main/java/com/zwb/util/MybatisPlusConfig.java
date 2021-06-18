package com.zwb.util;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@MapperScan("com.zwb.mapper")//这个注解，作用相当于下面的@Bean MapperScannerConfigurer，2者配置1份即可
public class MybatisPlusConfig {


	  /**
     * 分页插件
     */
    @SuppressWarnings("deprecation")
	@Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    //防sql注入攻击 目前高版本MybatisPlus不需要设置自带
    /*@Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }*/
}
