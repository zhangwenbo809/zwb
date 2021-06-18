package com.zwb.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2WebMvc // 开启 swagger2 功能
public class SwaggerConfig {
    private Boolean swaggerEnabled = true;

    @Bean
    public Docket restApiDocket() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        // tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        // pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2) //
                .apiInfo(apiInfo())
                .enable(swaggerEnabled) // 是否开启
                .genericModelSubstitutes(DeferredResult.class) //
                .useDefaultResponseMessages(false) //
                .forCodeGeneration(false) //
                .pathMapping("/").select()

                .apis(RequestHandlerSelectors.basePackage("com.zwb.controller")) // 指定扫描的包路径
                .paths(PathSelectors.any())
                .build()//.securitySchemes(security() ).pathMapping("/");
                .globalOperationParameters(pars).pathMapping("/");
    }


    private List<ApiKey> security() {
        List<ApiKey> list = new ArrayList<>();
        list.add(new ApiKey("token", "token", "header"));
        return list;
    }

    /**
     * @return
     * @方法描述:构建 api文档的详细信息函数
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("zwb技术整合")
                .description("技术整合swagger信息描述")
                .version("0.9")
                .build();
    }
}