package com.lsq.demo.spring.aop.proxy;

import com.lsq.demo.spring.aop.LSQAopConfig;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 模仿 AopProxy
 *
 * 这里使用cglid代理，不然无法注入
 * implements InvocationHandler
 */
public class LSQAopJDKProxy implements InvocationHandler {
    private Object targer;
    private LSQAopConfig config;
    public <T> T getInstance(Object instance){
        this.targer = instance;
        Class<?> clazz = instance.getClass();

        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method m = this.targer.getClass().getMethod(method.getName(),method.getParameterTypes());
        // 调用前要做的事情（增强）
        if (this.config.contain(m)){
            LSQAopConfig.LSQAspect aspest = config.get(method);
            aspest.getPoints()[0].invoke(aspest.getAspect());
        }
        Object obj = method.invoke(this.targer,args);
        // 调用后要做的事情（增强）
        if (this.config.contain(m)){
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
