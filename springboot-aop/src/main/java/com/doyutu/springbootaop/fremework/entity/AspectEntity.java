package com.doyutu.springbootaop.fremework.entity;

import lombok.Data;

import java.lang.reflect.Method;

@SuppressWarnings("serial")
@Data
public class AspectEntity {

    private Class<?> annotationClass;

    private String methodMappath;

    private String classMappath;

    private Method aspectInvokeMethod;

}
