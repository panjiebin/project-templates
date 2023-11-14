package com.pan.demo.quartz;

import com.pan.demo.quartz.job.TestJob;
import com.pan.demo.service.QuartzJobService;
import com.pan.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author panjb
 */
@Component
public class InitialSchedulerRunner implements ApplicationRunner {

    private final static Logger logger = LoggerFactory.getLogger(InitialSchedulerRunner.class);

    private final QuartzJobService quartzJobService;

    public InitialSchedulerRunner(QuartzJobService quartzJobService) {
        this.quartzJobService = quartzJobService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!quartzJobService.exists("test", "test")) {
            quartzJobService.addJob(TestJob.class, "test", "test", "0 */1 * * * ?", Collections.emptyMap());
            logger.info("initial schedule job.");
        }
    }
}
