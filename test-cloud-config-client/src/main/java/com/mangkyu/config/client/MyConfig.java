package com.mangkyu.config.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties("com.mangkyu")
@ConstructorBinding
@RefreshScope
@ToString
public class MyConfig {

    private final String profile;
    private final String region;

}