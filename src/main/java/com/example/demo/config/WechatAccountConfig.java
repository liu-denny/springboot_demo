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
}
