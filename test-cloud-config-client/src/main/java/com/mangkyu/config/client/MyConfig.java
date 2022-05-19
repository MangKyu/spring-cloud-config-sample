package com.mangkyu.config.client;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Getter
@RefreshScope
@Component
@ToString
public class MyConfig {

    @Value("${com.mangkyu.profile}")
    private String profile;

    @Value("${com.mangkyu.region}")
    private String region;

}