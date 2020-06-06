package com.lsq.demo.spring.simple2.beans;

import com.lsq.demo.spring.aop.LSQAopProxy;
import com.lsq.demo.spring.simple2.support.LSQBeanPostProcessor;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class LsqBeanWrapper implements FactoryBean {

    // 1.支持事件响应
    private LSQBeanPostProcessor postProcessor;
    private LSQAopProxy aopProxy = new LSQAopProxy();
    private Object wrapperInstance;

    // 原始的通过反射new出来，包装并且存下来
    private Object originalInstance;

    public LsqBeanWrapper(Object instance){
        this.wrapperInstance = aopProxy.getInstance(instance);
        this.originalInstance = instance;
    }

    public Object getWrapperInstance(){
        return this.wrapperInstance;
    }

    @Override
    public Object getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
