package com.lsq.demo.spring.aop;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * 模仿 AopProxyUtils
 */
public class LSQAopProxyUtils {

    public static Object getTargetObject(Object proxy) throws Exception {
        if (!isAopProxy(proxy)) {
            return proxy;
        }

        if (AopUtils.isJdkDynamicProxy(proxy)) {
            proxy = getJdkDynamicProxyTargetObject(proxy);
        } else {
            proxy = getCglibProxyTargetObject(proxy);
        }
        return proxy;
    }

    public static boolean isAopProxy(Object proxy) {
        return Proxy.isProxyClass(proxy.getClass());
    }

    public static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {

        // 代理对象会将原来的对象保存在自身的h属性中
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        LSQAopProxy aopProxy = (LSQAopProxy) h.get(proxy);
        // 这里如何取更好点？
        Field target = aopProxy.getClass().getDeclaredField("targer");
        target.setAccessible(true);
        return target.get(aopProxy);
    }

    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);
        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
        return target;
    }
}
