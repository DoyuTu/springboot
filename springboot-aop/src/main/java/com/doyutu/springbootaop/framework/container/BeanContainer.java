package com.doyutu.springbootaop.framework.container;

import com.google.common.base.Strings;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author doyutu
 * @date 2018-04-18 10:44
 * springboot
 */

@Slf4j
@SuppressWarnings("unchecked")
public class BeanContainer {

    @Getter
    private static final Map<String, Object> beanMap = new ConcurrentHashMap<>();

    public static void putBean(String beanName,Object bean) {
        beanMap.put(beanName, bean);
    }

    public static boolean containsBean(String beanName) {
        return beanMap.containsKey(beanName);
    }

    public static <T> T getBean(Class<?> cls) {
        String beanName = getBeanName(cls);
        if (Strings.isNullOrEmpty(beanName)) {
            throw new RuntimeException("找不到Bean：" + cls.getName());
        }
        return (T) beanMap.get(beanName);
    }

    public static <T> T getBean(String beanName) {
        if (Strings.isNullOrEmpty(beanName)) {
            throw new RuntimeException("找不到Bean：" + beanName);
        }
        return (T) beanMap.get(beanName);
    }

    private static String getBeanName(Class<?> cls) {
        for (Class annotationCls : AopContainer.beanContainer) {
            Annotation annotation = cls.getAnnotation(annotationCls);
            if (annotation == null) {
                continue;
            }
            String beanName = cls.getName();
            if (!Strings.isNullOrEmpty(beanName)) {
                return beanName;
            }
            return cls.getName();
        }
        throw new RuntimeException("找不到Bean：" + cls.getName());
    }

}
