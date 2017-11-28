package com.doyutu.springbootasync.springasync;

import com.doyutu.spring.async.rest.SpringAsyncRest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringAsyncRestTest {

    @Resource
    private SpringAsyncRest springAsyncRest;

    private static final String url = "https://www.baidu.com";
    private static Map<String, String> param = new HashMap<>(8);

    @Test
    public void springAsyncRestTester() {
        param.put("aaa", "bbb");
        param.put("11", "22");
        springAsyncRest.asyncRest(url, param);
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
