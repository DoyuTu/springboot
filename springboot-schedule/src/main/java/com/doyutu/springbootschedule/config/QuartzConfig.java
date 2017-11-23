package com.doyutu.springbootschedule.config;

import com.doyutu.springbootschedule.quartz.QuartzScheduleTasks;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

    @Bean
    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean(){
        MethodInvokingJobDetailFactoryBean jobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
        jobDetailFactoryBean.setBeanName("jobDetail");
        jobDetailFactoryBean.setGroup("job_work");
        jobDetailFactoryBean.setName("job_work_name");
        jobDetailFactoryBean.setConcurrent(false);
        jobDetailFactoryBean.setTargetObject(QuartzScheduleTasks.class);
//        jobDetailFactoryBean.setTargetMethod("execute");
        return jobDetailFactoryBean;
    }

    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(){
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setName("work_default_name");
        cronTriggerFactoryBean.setGroup("work_default");
        cronTriggerFactoryBean.setCronExpression("0/5 * * * * ?");
        cronTriggerFactoryBean.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
        return cronTriggerFactoryBean;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setTriggers(cronTriggerFactoryBean().getObject());
        return factoryBean;
    }

}
