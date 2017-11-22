package com.doyutu.springbootschedule.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzScheduleTasks {

    private static final Logger log = LoggerFactory.getLogger(QuartzScheduleTasks.class);

    public void quartzTask() {
        if (log.isInfoEnabled()) {
            log.info("Quartz start");
        }
    }
}
