package com.example.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "project")
@Component
public class ProjectUrlConfig {

    public String sell;
    public String wechatMpAuthorize;
    public String wechatOpenAuthorize;

}
