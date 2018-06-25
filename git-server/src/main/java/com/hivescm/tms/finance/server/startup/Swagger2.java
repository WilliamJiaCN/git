package com.hivescm.tms.finance.server.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * API文档生成工具；访问地址：
 * http://localhost:端口/swagger-ui.html
 * <p>
 * basePackage 必须包括**，否则与feign扫描冲突
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(Swagger2.class);

    @Value("${enable.swagger}")
    private boolean enableSwagger;

    @Value("${base.package.swagger}")
    private String basePackage;

    
    private ApiInfo apiTester() {
    	return new ApiInfoBuilder().title("TMS-FINANCE 系统 签收单 API文档")
    			.description("TMS-FINANCE 系统 签收单 API文档")
    			.termsOfServiceUrl("")
    			.version("1.0").build();
    }
    
    @Bean
    public Docket createTesterApi() {
        LOGGER.info("Swagger2信息: enable.swagger=" + enableSwagger + "; base.package.swagger=" + basePackage);
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enableSwagger)
                .groupName("sign")
                .apiInfo(apiTester()).select()
                .apis(RequestHandlerSelectors.basePackage(basePackage + ".sign"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiPcSign() {
    	return new ApiInfoBuilder().title("TMS-FINANCE 系统  pc签收 API文档")
    			.description("TMS-FINANCE 系统 pc签收 API文档")
    			.termsOfServiceUrl("")
    			.version("1.0").build();
    }

    @Bean
    public Docket createPcsign() {
        LOGGER.info("Swagger2信息: enable.swagger=" + enableSwagger + "; base.package.swagger=" + basePackage);
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enableSwagger)
                .groupName("pcsign")
                .apiInfo(apiPcSign()).select()
                .apis(RequestHandlerSelectors.basePackage(basePackage + ".pcsign"))
                .paths(PathSelectors.any()).build();
    }
    
    
    private ApiInfo apiFinance() {
    	return new ApiInfoBuilder().title("TMS-FINANCE 系统 财务 API文档")
    			.description("TMS-FINANCE 系统 财务 API文档")
    			.termsOfServiceUrl("")
    			.version("1.0").build();
    }

    @Bean
    public Docket createFinanceApi() {
        LOGGER.info("Swagger2信息: enable.swagger=" + enableSwagger + "; base.package.swagger=" + basePackage);
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enableSwagger)
                .groupName("finance")
                .apiInfo(apiFinance()).select()
                .apis(RequestHandlerSelectors.basePackage(basePackage + ".finance"))
                .paths(PathSelectors.any()).build();
    }
}