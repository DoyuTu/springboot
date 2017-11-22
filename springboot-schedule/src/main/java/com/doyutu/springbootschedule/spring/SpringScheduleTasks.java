package com.doyutu.springbootschedule.spring;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class SpringScheduleTasks {

    private static final Logger log = LoggerFactory.getLogger(SpringScheduleTasks.class);

    /**
     * 每两秒打印出一次当前系统日期
     * cron: *  *  *  *  *  ?
     *       秒 分 时 日 月 周
     *  ? 表示忽略
     *  * 表示所有
     *  fixedRate = 3000L  开启时立即执行一次，然后每三秒运行一次任务，无论前一次是否执行完成
     *  fixedDelay = 3000L 开启时立即执行一次，然后在前一次任务完成后三秒运行
     */
    @Scheduled(cron = "*/2 * * * * ?")
    public void reportTime1() {
        if (log.isInfoEnabled()) {
            log.info("cron1 :" + DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
        }
    }

    /**
     * 每小时的第1、3、4、5、6分钟，从第30秒开始，每两秒运行一次
     */
    @Scheduled(cron = "30/2 1,3,4-6 * * * ?")
    public void reportTime2() {
        if (log.isInfoEnabled()) {
            log.info("cron2 :" + DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
        }
    }

    /**
     * 每两秒运行一次，忽略执行时间
     */
    @Scheduled(fixedRate = 2000L)
    public void reportFixedRate() throws InterruptedException {
        if (log.isInfoEnabled()) {
            log.info("fixedRate :"+ DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
        }
        Thread.sleep(1000L);
    }

    /**
     * 定时每三秒运行一次，加上程序执行时间(sleep)，将会每四秒运行一次
     */
    @Scheduled(fixedDelay = 3000L)
    public void reportFixedDelay() throws InterruptedException {
        if (log.isInfoEnabled()) {
            log.info("fixedDelay :"+ DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
        }
        Thread.sleep(1000L);
    }
}
