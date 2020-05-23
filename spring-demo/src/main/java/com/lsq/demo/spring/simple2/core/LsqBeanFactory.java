package com.lsq.demo.spring.simple2.core;

import org.springframework.beans.BeansException;

public interface LsqBeanFactory {

    // 根据bean的名字，获取在IOC容器中的bean实例
    Object getBean(String name) throws BeansException;
    // 根据bean的名字和Class类型获取在IOC容器中的bean实例 安全机制
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
    //
    Object getBean(String var1, Object... var2) throws BeansException;
    // 根据bean的class 获取在IOC容器中的bean实例 安全机制
    <T> T getBean(Class<T> var1) throws BeansException;

    <T> T getBean(Class<T> var1, Object... var2) throws BeansException;
}
