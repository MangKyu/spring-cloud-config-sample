package com.mangkyu.config.client.cloudwatch;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.cloud.context.refresh.ConfigDataContextRefresher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ConfigClientWatchConfiguration {

    @Bean
    @ConditionalOnMissingBean(ConfigServicePropertySourceLocator.class)
    @ConditionalOnProperty(name = ConfigClientProperties.PREFIX + ".enabled", matchIfMissing = true)
    public ConfigServicePropertySourceLocator configServicePropertySource(ConfigClientProperties properties) {
        return new ConfigServicePropertySourceLocator(properties);
    }

    @Bean
    public ConfigGitClientWatch configGitClientWatch(ConfigDataContextRefresher refresher, ConfigServicePropertySourceLocator locator) {
        return new ConfigGitClientWatch(refresher, locator);
    }

}