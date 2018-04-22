package com.doyutu.springbootaop.fremework.servlet;

import com.doyutu.springbootaop.fremework.init.InitContext;
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
        String path = servletContextEvent.getServletContext().getInitParameter("scanPath");
        if (Strings.isNullOrEmpty(path)) {
            log.warn("启动参数scanPath为空");
            return;
        }
        String[] paths = path.split(",");
        InitContext.init(paths);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("结束contextDestroyed");
    }
}
