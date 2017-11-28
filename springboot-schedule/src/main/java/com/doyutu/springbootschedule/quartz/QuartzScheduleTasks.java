package com.doyutu.springbootschedule.quartz;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class QuartzScheduleTasks implements Job {

    private static final Logger log = LoggerFactory.getLogger(QuartzScheduleTasks.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String message = MessageFormat.format("Quartz:{0}", DateFormatUtils.format(System.currentTimeMillis(), "HH:mm:ss.SSS"));
        if (log.isInfoEnabled()) {
            log.info(message);
        } else {
            System.out.println(message);
        }
    }
}
