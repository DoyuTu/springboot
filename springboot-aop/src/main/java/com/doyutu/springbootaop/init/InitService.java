package com.doyutu.springbootaop.init;

import com.doyutu.springbootaop.container.BeanContainer;
import com.doyutu.springbootaop.proxy.CglibProxy;
import com.doyutu.springbootaop.util.ClassUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 初始化服务
 * @author doyutu
 * @date 2018-04-20 18:41
 * springboot
 */
@Slf4j
public class InitService {

    private static CglibProxy proxy = new CglibProxy();

    public static void init(String [] paths) {
        Set<Class<?>> cls = new HashSet<>();
        Arrays.stream(paths).forEach((String c) -> cls.addAll(ClassUtil.getClasses(c)));
        if (CollectionUtils.isEmpty(cls)) {
            return;
        }
        initClass(cls);

    }

    private static void initClass(Set<Class<?>> cls) {
        if (CollectionUtils.isEmpty(cls)) {
            return;
        }
        cls.parallelStream().forEach(c ->{
            String beanName = BeanContainer.getBeanName(c);

        });
    }
}
