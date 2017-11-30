package com.doyutu.springbootcloudbus.sender;

import com.doyutu.springbootcloudbus.SpringbootCloudBusApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SenderTest extends SpringbootCloudBusApplicationTests {

    @Autowired
    Sender sender;

    /**
     * 测试RabbitMQ是否可用
     *
     * @throws Exception
     */
    @Test
    public void send() throws Exception {
        sender.send();
    }

}