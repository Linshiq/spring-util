package com.lsq.demo.IOC;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.core.ResolvableType;

public interface BeanFactory {

    // 对FactoryBean的转义，因为如果使用bean的名字检索FactoryBean得到的对象是工厂生成的对象
    // 如果需要得到工厂本身，需要转义
    String FACTORY_BEAN_PREFIX = "&";
    // 根据bean的名字，获取在IOC容器中的bean实例
    Object getBean(String name) throws BeansException;
    // 根据bean的名字和Class类型获取在IOC容器中的bean实例 安全机制
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
    //
    Object getBean(String var1, Object... var2) throws BeansException;
    // 根据bean的class 获取在IOC容器中的bean实例 安全机制
    <T> T getBean(Class<T> var1) throws BeansException;

    <T> T getBean(Class<T> var1, Object... var2) throws BeansException;
    // 根据bean的名字，判断bean是否存在
    boolean containsBean(String name);
    // 根据bean的名字，判断是否单例
    boolean isSingleton(String name) throws NoSuchBeanDefinitionException;

    boolean isPrototype(String var1) throws NoSuchBeanDefinitionException;

    boolean isTypeMatch(String var1, ResolvableType var2) throws NoSuchBeanDefinitionException;

    boolean isTypeMatch(String var1, Class<?> var2) throws NoSuchBeanDefinitionException;
    // 根据bean的名字，获取class类型
    Class<?> getType(String name) throws NoSuchBeanDefinitionException;
    // 获取bean的别名，如果根据别名检索，原名也会出来
    String[] getAliases(String var1);
}
