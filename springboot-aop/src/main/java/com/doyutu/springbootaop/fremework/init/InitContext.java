package com.doyutu.springbootaop.fremework.init;

import com.doyutu.springbootaop.fremework.annotation.Component;
import com.doyutu.springbootaop.fremework.annotation.Inject;
import com.doyutu.springbootaop.fremework.container.ContextContainer;
import com.doyutu.springbootaop.fremework.container.FieldContainer;
import com.doyutu.springbootaop.fremework.proxy.CglibProxy;
import com.doyutu.springbootaop.fremework.util.ClassUtil;
import com.doyutu.springbootaop.service.AopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 初始化服务
 * @author doyutu
 * @date 2018-04-20 18:41
 * springboot
 */
@Slf4j
public class InitContext {

    private static CglibProxy proxy = new CglibProxy();

    public static void init(String [] paths) {
        Set<Class<?>> cls = new HashSet<>();
        Arrays.stream(paths).forEach((String c) -> cls.addAll(ClassUtil.getClasses(c)));
        if (CollectionUtils.isEmpty(cls)) {
            return;
        }
        initComponent(cls);
        initInjection();
//        initClass(cls);

    }

    private static void initComponent(Set<Class<?>> cls) {
        if (CollectionUtils.isEmpty(cls)) {
            return;
        }
        cls.parallelStream().filter(k -> k.isAnnotationPresent(Component.class)).forEach(k -> {
            String clsName = k.getName().substring(k.getName().lastIndexOf(".") + 1);
            //bean ID
            String beanId = String.valueOf(clsName.charAt(0)).toLowerCase() + clsName.substring(1);
            try {
                ContextContainer.putContext(k, k.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    private static void initInjection() {
        ContextContainer.getContextContainer().entrySet()
                .parallelStream()
                .filter((k) -> !Objects.isNull(k.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                .forEach((k, v) -> {
                    for (Field field : k.getDeclaredFields()) {
                        if (field.isAnnotationPresent(Inject.class)) {
                            //需要注入的类
                            Class<?> type = field.getType();
                            try {
                                field.setAccessible(true);
                                //属性注入
                                field.set(v, type.newInstance());
                                FieldContainer.putContent(field.getName(), field.get(v));
                            } catch (IllegalAccessException | InstantiationException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> cls = Class.forName("com.doyutu.springbootaop.service.AopService");
        AopService o = (AopService) cls.newInstance();
        System.out.println(o.getAop());

    }
}
