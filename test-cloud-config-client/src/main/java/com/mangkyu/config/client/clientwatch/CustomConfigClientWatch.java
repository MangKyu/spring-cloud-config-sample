package com.mangkyu.config.client.clientwatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.io.Closeable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.springframework.util.StringUtils.hasText;

@Slf4j
public class CustomConfigClientWatch implements Closeable, EnvironmentAware {

    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicReference<String> version = new AtomicReference<>();
    private final ContextRefresher refresher;
    private final ConfigServicePropertySourceLocator locator;

    private Environment environment;

    public CustomConfigClientWatch(ContextRefresher refresher, ConfigServicePropertySourceLocator locator) {
        this.refresher = refresher;
        this.locator = locator;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void start() {
        running.compareAndSet(false, true);
    }

    @Scheduled(
            initialDelayString = "${spring.cloud.config.watch.git.initialDelay:10000}",
            fixedDelayString = "${spring.cloud.config.watch.git.delay:5000}"
    )
    public void watchConfigServer() {
        if (running.get()) {
            String newVersion = fetchNewVersion();
            if (newVersion == null) {
                return;
            }

            String oldVersion = version.get();
            if (versionChanged(oldVersion, newVersion)) {
                version.set(newVersion);
                refresher.refresh();
            }
        }
    }

    private String fetchNewVersion() {
        try {
            CompositePropertySource propertySource = (CompositePropertySource) locator.locate(environment);
            return (String) propertySource.getProperty("config.client.version");
        } catch (NullPointerException e) {
            log.error("Cannot fetch from Cloud Config Server: ");
        }

        return null;
    }

    private static boolean versionChanged(String oldVersion, String newVersion) {
        return !hasText(oldVersion) && hasText(newVersion)
                || hasText(oldVersion) && !oldVersion.equals(newVersion);
    }

    @Override
    public void close() {
        running.compareAndSet(true, false);
    }
}