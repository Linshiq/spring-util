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
        // 这里不能写成methodProxy.invoke 会进入死循环，
        // 原因： 代理类调用了 findLove 触发了 代理的  intercept 然后 接着调用 （methodProxy).invoke (等于又调用了intercept) 所以死循环
        // 等于下面的testDie()方法
        // 而invokeSuper是直接调用了被代理类的原有方法
        obj = methodProxy.invokeSuper(o,objects);

        System.out.println(obj);
        System.out.println("结束");
        return null;
    }

    private void testDie(){
        this.testDie();
    }
}
