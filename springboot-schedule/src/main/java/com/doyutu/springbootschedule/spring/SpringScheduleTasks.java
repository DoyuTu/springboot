package com.doyutu.springbootschedule.spring;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
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
     *  fixedRate = 3000  每三秒运行一次任务，无论前一次是否执行完成
     *  fixedDelay = 3000 在前一次任务完成后三秒运行
     */
    @Schedules({@Scheduled(cron = "*/2 * * * * ?")})
    public void reportTime() {
        if (log.isInfoEnabled()) {
            log.info(DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
        }
    }
}
