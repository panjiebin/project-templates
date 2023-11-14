package com.pan.demo.quartz.job;

import com.pan.demo.entity.User;
import com.pan.demo.service.UserService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author panjb
 */
public class TestJob implements Job {

    private final static Logger logger = LoggerFactory.getLogger(TestJob.class);

    @Autowired
    private UserService userService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        List<User> list = userService.list();
        logger.info("total user: [{}]", list.size());
    }
}
