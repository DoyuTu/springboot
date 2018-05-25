package com.doyutu.springbootaop.framework.container;

import com.doyutu.springbootaop.framework.annotation.Beans;
import com.doyutu.springbootaop.framework.annotation.Component;

/**
 * @author doyutu
 * @date 2018-04-18 16:42
 * springboot
 */
public class AopContainer {

    public static final Class<?> [] beanContainer = new Class[]{Beans.class, Component.class};

}
