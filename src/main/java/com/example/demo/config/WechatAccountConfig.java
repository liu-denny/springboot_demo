package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yudong
 * @create 2019-08-06 18:06
 */
@Component
@Data
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    /**
     * 公众号平台Id
     */
    private String mpAppId;

    /**
     * 公众号密钥
     */
    private String mpAppSecret;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 微信支付异步通知地址
     */
    private String notifyUrl;

    /**
     * 商户证书路径
     */
    private String keyPath;
}
