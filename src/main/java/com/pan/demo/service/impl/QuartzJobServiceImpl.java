package com.pan.demo.service.impl;

import com.pan.demo.service.QuartzJobService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author panjb
 */
@Service
public class QuartzJobServiceImpl implements QuartzJobService {
    private static final Logger log = LoggerFactory.getLogger(QuartzJobServiceImpl.class);

    private final Scheduler scheduler;

    public QuartzJobServiceImpl(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void addJob(Class<? extends Job> jobClass, String jobName, String groupName, String cronExp, Map<String, Object> param) throws SchedulerException {
        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, groupName).build();
        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExp);
        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, groupName).withSchedule(scheduleBuilder).build();
        //获得JobDataMap，写入数据
        if (param != null) {
            trigger.getJobDataMap().putAll(param);
        }
        scheduler.scheduleJob(jobDetail, trigger);
    }

    @Override
    public void pauseJob(String jobName, String groupName) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(jobName, groupName));
    }

    @Override
    public void resumeJob(String jobName, String groupName) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(jobName, groupName));
    }

    @Override
    public void runOnce(String jobName, String groupName) throws SchedulerException {
        scheduler.triggerJob(JobKey.jobKey(jobName, groupName));
    }

    @Override
    public void updateJob(String jobName, String groupName, String cronExp, Map<String, Object> param) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, groupName);
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (cronExp != null) {
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExp);
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        }
        //修改map
        if (param != null) {
            trigger.getJobDataMap().putAll(param);
        }
        // 按新的trigger重新设置job执行
        scheduler.rescheduleJob(triggerKey, trigger);
    }

    @Override
    public void deleteJob(String jobName, String groupName) throws SchedulerException {
        //暂停、移除、删除
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobName, groupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobName, groupName));
        scheduler.deleteJob(JobKey.jobKey(jobName, groupName));
    }

    @Override
    public void startAllJobs() throws SchedulerException {
        scheduler.start();
    }

    @Override
    public void pauseAllJobs() throws SchedulerException {
        scheduler.pauseAll();
    }

    @Override
    public void resumeAllJobs() throws SchedulerException {
        scheduler.resumeAll();
    }

    @Override
    public void shutdownAllJobs() throws SchedulerException {
        if (!scheduler.isShutdown()) {
            // 需谨慎操作关闭scheduler容器
            // scheduler生命周期结束，无法再 start() 启动scheduler
            scheduler.shutdown(true);
        }
    }

    @Override
    public boolean exists(String jobName, String groupName) throws SchedulerException {
        return scheduler.checkExists(JobKey.jobKey(jobName, groupName));
    }
}
