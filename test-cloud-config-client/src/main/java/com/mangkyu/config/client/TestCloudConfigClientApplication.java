package com.mangkyu.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({MyConfig.class})
public class TestCloudConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestCloudConfigClientApplication.class, args);
    }

}
