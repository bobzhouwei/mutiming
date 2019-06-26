package org.mutiming.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Data collection - main purpose is to load a ExecutorService object when Spring boot starting
 *
 * @author Wei.Zhou
 */
@Configuration
public class ThreadPoolConfig {

    @Bean
    public ExecutorService getThreadPool() {
        return Executors.newFixedThreadPool(20);
    }
}
