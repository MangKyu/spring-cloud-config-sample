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
public class LocalConfig {
    // {application-profiles}.yml 에 정의한 내용을 해당 변수에 넣어줌
    @Value("${com.mangkyu.profile}")
    private String profile;

    @Value("${com.mangkyu.region}")
    private String region;

}