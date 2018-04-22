package com.doyutu.springbootaop.web.controller;

import com.doyutu.springbootaop.fremework.container.ContextContainer;
import com.doyutu.springbootaop.fremework.init.InitContext;
import org.junit.Assert;
import org.junit.Test;


/**
 * 依赖注入测试
 *
 * @author doyutu
 * @date 2018-04-22 20:02
 * springboot
 */
public class InjuectTest {

    @Test
    public void testInject() {
        InitContext.init(new String[]{"com.doyutu.springbootaop.web.controller"});
        AopController aopController = ContextContainer.getContext(AopController.class);
        Assert.assertEquals("Inject", aopController.getService());
    }

}