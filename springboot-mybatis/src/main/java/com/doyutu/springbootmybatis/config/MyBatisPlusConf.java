package com.doyutu.springbootmybatis.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisPlusConf {

    /**
     * 性能拦截器
     * @return
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor interceptor = new PerformanceInterceptor();
        interceptor.setFormat(true);
        return interceptor;
    }

    /**
     * 分页插件
     * @return
     */
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor conf = new PaginationInterceptor();
        //pageHelper支持
        conf.setLocalPage(true);
//        conf.setOverflowCurrent(true);

        return conf;
    }

}
