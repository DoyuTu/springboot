package com.doyutu.springbootschedule.quartz;

import com.doyutu.springbootschedule.SpringbootScheduleApplicationTests;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;

public class QuartzScheduleTasksTest extends SpringbootScheduleApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(QuartzScheduleTasksTest.class);

    @Test
    public void run() throws SchedulerException, InterruptedException, ParseException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        JobKey jobKey = new JobKey("job1", "group1");
        JobDetail job1 = JobBuilder.newJob(QuartzScheduleTasks.class)
                .withIdentity(jobKey)
                .build();
        Trigger trigger1 = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                //设置定时器，每三秒运行一次
                .withSchedule(CronScheduleBuilder.cronSchedule(new CronExpression("*/3 * * * * ?")))
                .startNow()
                .build();
        scheduler.scheduleJob(job1, trigger1);
        Thread.sleep(11000L);
        //删除任务
        scheduler.deleteJob(jobKey);
        log.info(DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss:SSS"));
        Thread.sleep(20000L);
    }

}