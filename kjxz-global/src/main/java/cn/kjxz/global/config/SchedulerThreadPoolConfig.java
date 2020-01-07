package cn.kjxz.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * 定时任务线程池配置
 */
@Configuration
public class SchedulerThreadPoolConfig {

    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        //线程数
        threadPoolTaskScheduler.setPoolSize(10);
        //线程名
        threadPoolTaskScheduler.setThreadNamePrefix("cdq_scheduler_");
        return threadPoolTaskScheduler;
    }
}