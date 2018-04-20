package com.doyutu.springbootaop.servlet;

import com.doyutu.springbootaop.init.InitService;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *  Servlet
 * @author doyutu
 * @date 2018-04-18 11:10
 * springboot
 */

@WebListener
@Slf4j
public class AopServlet implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("运行contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            System.out.println("运行contextDestroyed");
            String path = servletContextEvent.getServletContext().getInitParameter("scanPath");
            if (Strings.isNullOrEmpty(path)) {
                log.warn("启动参数scanPath为空");
                return;
            }
            String [] paths = path.split(",");
            InitService.init(paths);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
