package com.doyutu.springbootschedule.spring;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Component
public class DynamicScheduleTasks implements SchedulingConfigurer{

    private static final Logger log = LoggerFactory.getLogger(DynamicScheduleTasks.class);

    /**
     * 默认每五秒执行
     */
    private static final String DEFAULT_CRON = "0/5 * * * * ?";

    private String cron = DEFAULT_CRON;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        //任务一
        taskRegistrar.addTriggerTask(()->
                // do something
                log.info("Trigger:"+ DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"))
        , (TriggerContext triggerContext)->{
            //修改执行周期
            CronTrigger trigger = new CronTrigger(cron);
            return trigger.nextExecutionTime(triggerContext);
        });

        //任务二
        //不会因为更改 cron 改变定时任务
        taskRegistrar.addCronTask(()->
            log.info("CronTask :" + DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"))
        , DEFAULT_CRON);
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
}
