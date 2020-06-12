package com.lsq.demo.spring.aop.proxy;

import com.lsq.demo.spring.aop.LSQAopConfig;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 模仿 AopProxy
 *
 * 这里使用cglid代理，不然无法注入
 * implements InvocationHandler
 */
public class LSQAopCglibProxy implements MethodInterceptor {
    private Object targer;
    private LSQAopConfig config;
//    public <T> T getInstance(Object instance){
//        this.targer = instance;
//        Class<?> clazz = instance.getClass();
//
//        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
//    }

    public Object getInstance(Class clazz){

        Enhancer enhancer = new Enhancer();
        // 告诉cglib,生成的子类需要继承哪个类
        enhancer.setSuperclass(clazz);
        // 设置回调 。 回调的方法是 intercept
        enhancer.setCallback(this);
        // 1.生成源代码
        // 2.编译成class文件
        // 3.加载jvm并返回被代理对象
        return enhancer.create();
    }

//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        Method m = this.targer.getClass().getMethod(method.getName(),method.getParameterTypes());
//        // 调用前要做的事情（增强）
//        if (this.config.contain(m)){
//            LSQAopConfig.LSQAspect aspest = config.get(method);
//            aspest.getPoints()[0].invoke(aspest.getAspect());
//        }
//        Object obj = method.invoke(this.targer,args);
//        // 调用后要做的事情（增强）
//        if (this.config.contain(m)){
//            LSQAopConfig.LSQAspect aspest = config.get(method);
//            aspest.getPoints()[1].invoke(aspest.getAspect());
//        }
//        return obj;
//    }

    public LSQAopConfig getConfig() {
        return config;
    }

    public void setConfig(LSQAopConfig config) {
        this.config = config;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Method m = method;
        // 调用前要做的事情（增强）
        if (this.config.contain(m)){
            LSQAopConfig.LSQAspect aspest = config.get(method);
            aspest.getPoints()[0].invoke(aspest.getAspect());
        }
        Object obj = methodProxy.invokeSuper(o,objects);
        // 调用后要做的事情（增强）
        if (this.config.contain(m)){
            LSQAopConfig.LSQAspect aspest = config.get(method);
            aspest.getPoints()[1].invoke(aspest.getAspect());
        }
        return obj;
    }
}
