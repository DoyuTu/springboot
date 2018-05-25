package com.doyutu.springbootaop.framework.container;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 上下文容器
 *
 * @author doyutu
 * @date 2018-04-22 02:45
 * springboot
 */
@Slf4j
public class ContextContainer {

    @Getter
    private static Map<Class<?>, Object> contextContainer = new ConcurrentHashMap<>();

    public static void putContext(Class<?> contextName, Object cls) {
        contextContainer.put(contextName, cls);
    }

    @SuppressWarnings({"unchecked"})
    public static <T> T getContext(Class<T> cls) {
        if (cls == null) {
            return null;
        }
        return (T) contextContainer.get(cls);
    }
}
