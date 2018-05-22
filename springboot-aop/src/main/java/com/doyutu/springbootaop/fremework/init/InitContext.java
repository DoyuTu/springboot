package com.doyutu.springbootaop.fremework.init;

import com.doyutu.springbootaop.fremework.annotation.Around;
import com.doyutu.springbootaop.fremework.annotation.Component;
import com.doyutu.springbootaop.fremework.annotation.Inject;
import com.doyutu.springbootaop.fremework.container.AspectContainer;
import com.doyutu.springbootaop.fremework.container.BeanContainer;
import com.doyutu.springbootaop.fremework.container.ContextContainer;
import com.doyutu.springbootaop.fremework.container.FieldContainer;
import com.doyutu.springbootaop.fremework.point.AspectPoint;
import com.doyutu.springbootaop.fremework.proxy.CglibProxy;
import com.doyutu.springbootaop.fremework.util.ClassUtil;
import com.doyutu.springbootaop.service.AopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
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

    /**
     * key拦截方法，value拦截器的方法
     */
    public static final Map<String, AspectPoint> INTERCEPT_MAP = new ConcurrentHashMap<>();

    public static void init(String [] paths) {
        Set<Class<?>> cls = new HashSet<>();
        Arrays.stream(paths).forEach((String c) -> cls.addAll(ClassUtil.getClasses(c)));
        if (CollectionUtils.isEmpty(cls)) {
            return;
        }
        initComponent(cls);
        initAspect();
        initIntercept();
        initInjection();
//        initClass(cls);

    }

    private static void initIntercept() {
        ContextContainer.getContextContainer()
                .entrySet()
                .parallelStream()
                .forEach(c -> {
                    //扫描所有包含aspectMap中的方法
                    for (Method method : c.getKey().getDeclaredMethods()) {
                        for (Annotation annotation : method.getDeclaredAnnotations()) {
                            if (annotation == null) {
                                continue;
                            }
                            String name = annotation.annotationType().getName();
                            Set<Method> methods = AspectContainer.aspectMap.get(name);
                            if (methods == null || methods.size() == 0) {
                                continue;
                            }
                            List<Method> o = new ArrayList<>(methods);
                            AspectPoint point = new AspectPoint();
                            loadAspectPoint(point, o, method);
                            INTERCEPT_MAP.put(c.getKey().getName(), point);
                            Object proxy = InitContext.proxy.getProxy(c.getKey());
                            BeanContainer.putBean(c.getKey().getName(), proxy);
                        }
                    }
                });
    }

    private static void loadAspectPoint(AspectPoint point ,List<Method> methods, Method m) {
        if (CollectionUtils.isEmpty(methods)) {
            return;
        }
        Method aspectMethod = methods.get(0);
        point.setAspectMethod(aspectMethod);
        point.setAspectBean(aspectMethod.getDeclaringClass());
        point.setClazz(m.getDeclaringClass());
        methods.remove(0);
        if (CollectionUtils.isEmpty(methods)) {
            point.setChildPoint(null);
        }
        loadAspectPoint(point, methods, m);
    }

    private static void initAspect() {
        ContextContainer.getContextContainer()
                .entrySet()
                .parallelStream()
                .forEach(c ->{
                    for (Method method : c.getKey().getDeclaredMethods()) {
                        Around around = method.getDeclaredAnnotation(Around.class);
                        if (around == null) {
                            continue;
                        }
                        String name;
                        Class<?> [] value = around.value();
                        for (Class<?> ar : value) {
                            if (ar == null) {
                                continue;
                            }
                            name = ar.getName();
                            Set<Method> methods = AspectContainer.aspectMap.get(name);
                            if (methods != null) {
                                methods.add(method);
                            } else {
                                AspectContainer.aspectMap.put(name, Collections.singleton(method));
                            }
                        }

                    }
                });
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
                                String name = type.getName();
                                Object bean = BeanContainer.getBean(name);
                                if (Objects.isNull(bean)) {
                                    throw new RuntimeException("找不到的Bean：" + name);
                                }
                                field.set(v,bean);
                                FieldContainer.putContent(field.getName(), field.get(k));
                            } catch (IllegalAccessException e) {
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
