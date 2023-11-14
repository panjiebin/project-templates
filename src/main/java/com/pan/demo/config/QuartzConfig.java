package com.pan.demo.config;

import com.pan.demo.quartz.QuartzAutowireCapableJobFactory;
import org.quartz.Scheduler;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;

/**
 * 注册调度工厂
 *
 * @author panjb
 */
@Configuration
public class QuartzConfig {

    private final QuartzAutowireCapableJobFactory jobFactory;

    public QuartzConfig(QuartzAutowireCapableJobFactory jobFactory) {
        this.jobFactory = jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        //获取配置属性
        PropertiesFactoryBean propsFactoryBean = new PropertiesFactoryBean();
        propsFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        //在quartz.properties中的属性被读取并注入后再初始化对象
        propsFactoryBean.afterPropertiesSet();
        //创建SchedulerFactoryBean
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setQuartzProperties(propsFactoryBean.getObject());
        //支持在JOB实例中注入其他的业务对象
        factory.setJobFactory(jobFactory);
        factory.setApplicationContextSchedulerContextKey("applicationContextKey");
        //这样当spring关闭时，会等待所有已经启动的quartz job结束后spring才能完全shutdown。
        factory.setWaitForJobsToCompleteOnShutdown(true);
        //是否覆盖己存在的Job
        factory.setOverwriteExistingJobs(false);
        //QuartzScheduler 延时启动，应用启动完后 QuartzScheduler 再启动
        factory.setStartupDelay(10);

        return factory;
    }

    /**
     * 通过SchedulerFactoryBean获取Scheduler的实例
     */
    @Bean(name = "scheduler")
    public Scheduler scheduler() throws IOException {
        return schedulerFactoryBean().getScheduler();
    }
}