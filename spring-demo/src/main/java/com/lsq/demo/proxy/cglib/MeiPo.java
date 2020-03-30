package com.lsq.demo.proxy.cglib;

import com.lsq.demo.proxy.jdk_proxy.Person;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理实例化工具类
 */
public class MeiPo implements MethodInterceptor {

    public Object getInstance(Object obj){

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     * 与jdk代理一样
     * 都是字节重组
     * @param o 被代理对象
     * @param method ？ 有啥用？？
     * @param objects 传参
     * @param methodProxy 被代理对象方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("这里是cglib代理 我进来了");
        //  String resutl = targer.findLove();
        // 执行代理
        Object obj;
        // 这里不能写成methodProxy.invoke 会进入死循环，原因暂时不明 ， 可能是自己调用了自己
        // 而invokeSuper是直接调用了被代理类的原有方法
        obj = methodProxy.invokeSuper(o,objects);

        System.out.println(obj);
        System.out.println("结束");
        return null;
    }
}
