package com.pan.demo.service;

import org.quartz.Job;
import org.quartz.SchedulerException;

import java.util.Map;

/**
 * @author panjb
 */
public interface QuartzJobService {
    /**
     * 添加任务可以传参数
     */
    void addJob(Class<? extends Job> jobClass, String jobName, String groupName, String cronExp, Map<String, Object> param) throws SchedulerException;

    /**
     * 暂停任务
     */
    void pauseJob(String jobName, String groupName) throws SchedulerException;

    /**
     * 恢复任务
     */
    void resumeJob(String jobName, String groupName) throws SchedulerException;

    /**
     * 立即运行一次定时任务
     */
    void runOnce(String jobName, String groupName) throws SchedulerException;

    /**
     * 更新任务
     */
    void updateJob(String jobName, String groupName, String cronExp, Map<String, Object> param) throws SchedulerException;

    /**
     * 删除任务
     * @param jobName
     * @param groupName
     */
    void deleteJob(String jobName, String groupName) throws SchedulerException;

    /**
     * 启动所有任务
     */
    void startAllJobs() throws SchedulerException;

    /**
     * 暂停所有任务
     */
    void pauseAllJobs() throws SchedulerException;

    /**
     * 恢复所有任务
     */
    void resumeAllJobs() throws SchedulerException;

    /**
     * 关闭所有任务
     */
    void shutdownAllJobs() throws SchedulerException;

    boolean exists(String jobName, String groupName) throws SchedulerException;
}
