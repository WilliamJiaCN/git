package com.hivescm.tms.finance.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 支付公钥私钥配置
 * @author 杨彭伟
 * @date 2018-02-24 15:34
 */
@Data
@Component
@ConfigurationProperties("pay")
public class PaySignKeyProperties {
    private boolean enable;

    private String publicKey;

    private String privateKey;
}
