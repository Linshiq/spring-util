package com.lsq.demo.proxy.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK代理
 */
public class JDKMeiPo implements InvocationHandler {

    private Object targer;

    public Object getInstance(ZhangSan person){
        this.targer = person;
        Class clazz = person.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我进来了"+this.targer.getClass());
      //  String resutl = targer.findLove();
        // 执行代理
        Object obj;
        if (args != null){
            obj = method.invoke(targer,args);
        } else {
            obj = method.invoke(targer);
        }
        System.out.println(obj);
        System.out.println("结束");
        return obj.toString();
    }
}
