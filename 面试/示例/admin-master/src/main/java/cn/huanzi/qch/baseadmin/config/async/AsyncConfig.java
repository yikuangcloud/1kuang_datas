package cn.huanzi.qch.baseadmin.config.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池的配置
 */
@Configuration
public class AsyncConfig {

    //最大线程数
    private static final int MAX_POOL_SIZE = 50;

    //核心线程数
    private static final int CORE_POOL_SIZE = 20;

    @Bean("asyncTaskExecutor")
    public AsyncTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor asyncTaskExecutor = new ThreadPoolTaskExecutor();
        asyncTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        asyncTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        asyncTaskExecutor.setThreadNamePrefix("async-thread-");
        asyncTaskExecutor.initialize();
        return asyncTaskExecutor;
    }
}