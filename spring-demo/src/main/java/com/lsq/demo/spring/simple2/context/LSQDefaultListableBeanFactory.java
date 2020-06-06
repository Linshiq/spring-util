package com.lsq.demo.spring.simple2.context;

import com.lsq.demo.spring.simple2.beans.LsqBeanDefinition;
import org.springframework.beans.BeansException;

import java.util.HashMap;
import java.util.Map;

public class LSQDefaultListableBeanFactory extends LSQAbstractApplicationContext{

    protected Map<String, LsqBeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    protected void refreshBeanFactory() {

    }

    @Override
    protected void onRefresh() throws BeansException {
        super.onRefresh();
    }
}
