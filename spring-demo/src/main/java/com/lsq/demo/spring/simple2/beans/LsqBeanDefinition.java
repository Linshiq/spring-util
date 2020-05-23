package com.lsq.demo.spring.simple2.beans;

/**
 * 存储配置文件中的信息，等于保存在内存中
 */
public class LsqBeanDefinition {

    private String beanClassName;
    private String factoryBeanName;

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }
}
