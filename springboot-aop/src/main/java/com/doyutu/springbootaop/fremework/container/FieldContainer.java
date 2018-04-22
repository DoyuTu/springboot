package com.doyutu.springbootaop.fremework.container;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 属性注入容器
 *
 * @author doyutu
 * @date 2018-04-22 02:49
 * springboot
 */
@Slf4j
public class FieldContainer {

    @Getter
    private static Map<String, Object> fiedlContainer = new ConcurrentHashMap<>();

    public static void putContent(String contextName, Object obj) {
        fiedlContainer.put(contextName, obj);
    }

}
