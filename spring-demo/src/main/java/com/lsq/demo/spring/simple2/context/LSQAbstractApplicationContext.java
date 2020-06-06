package com.lsq.demo.spring.simple2.context;

import org.springframework.beans.BeansException;

public abstract class LSQAbstractApplicationContext {

    protected void onRefresh() throws BeansException {
    }

    protected abstract void refreshBeanFactory();
}
