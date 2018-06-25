package com.hivescm.tms.finance.server.startup;

import com.hivescm.common.listener.SpringBootAdminListener;
import com.hivescm.common.listener.SpringBootPreparedEventListener;
import com.hivescm.common.listener.SpringBootStartedEventListener;
import com.hivescm.common.log.EnableFeignLog;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageCashSerialMapper;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageCashTransferMapper;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageReceiptMapper;
import com.hivescm.tms.finance.server.dao.mapper.sign.*;
import com.hivescm.tsharding.config.EnableTSharding;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动入口类
 * EnableAsync注解（异步回调）让@Async注解能够生效,不能加在静态方法上
 */
@EnableAsync
@EnableScheduling
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(value = "com.hivescm")
@ServletComponentScan(value = "com.hivescm")
@EnableEurekaClient
//@ImportResource({"classpath:disconf.xml"})
@EnableTSharding(
        mapperPackage = {
                "com.hivescm.tms.finance.server.dao.mapper.sign",
                "com.hivescm.tms.finance.server.dao.mapper.finance"
        },
        configLocation = "classpath:mybatis-config.xml",
        enhancedMappers = {
                GoodsDetailsDOMapper.class,
                SignRefuseDOMapper.class,
                SignFeeMapper.class,
                SignDetailDOMapper.class,
                SignMapper.class,
                OrderPaymentInfoDOMapper.class,
                FinanceManageReceiptMapper.class,
                FinanceManageCashSerialMapper.class,
                FinanceManageCashTransferMapper.class

        },
        mapperLocations = "classpath*:sqlmap/**/*-mapper.xml")
@EnableFeignClients(
        {
            "com.hivescm.framework.logger.api.feign",
            "com.hivescm.tms.intranet.gateway.api.feign",
            "com.hivescm.tms.finance.server.feign",
            "com.hivescm.framework.feign"
        }
)


@EnableFeignLog
public class TmsFinanceServerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application.listeners(new SpringBootStartedEventListener(), new SpringBootPreparedEventListener(),new SpringBootAdminListener());
        return application.sources(TmsFinanceServerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TmsFinanceServerApplication.class);
        app.addListeners(new SpringBootStartedEventListener());
        app.addListeners(new SpringBootPreparedEventListener());
        app.addListeners(new SpringBootAdminListener());
        app.run(args);
    }
}
