package com.lsq.demo.spring.aop;

import com.lsq.demo.proxy.jdk_proxy.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 模仿 AopProxy
 */
// 默认使用JDK动态代理
public class LSQAopProxy implements InvocationHandler {
    private Object targer;
    private LSQAopConfig config;
    public Object getInstance(Object instance){
        this.targer = instance;
        Class clazz = instance.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 调用前要做的事情（增强）
        if (this.config.contain(method)){
            LSQAopConfig.LSQAspect aspest = config.get(method);
            aspest.getPoints()[0].invoke(aspest.getAspect());
        }
        Object obj = method.invoke(this.targer,args);
        // 调用后要做的事情（增强）
        if (this.config.contain(method)){
            LSQAopConfig.LSQAspect aspest = config.get(method);
            aspest.getPoints()[1].invoke(aspest.getAspect());
        }
        return obj;
    }

    public LSQAopConfig getConfig() {
        return config;
    }

    public void setConfig(LSQAopConfig config) {
        this.config = config;
    }
}
