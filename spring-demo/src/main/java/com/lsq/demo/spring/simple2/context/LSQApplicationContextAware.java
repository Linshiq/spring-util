package com.lsq.demo.spring.simple2.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public interface LSQApplicationContextAware {

    void setApplicationContext(LsqApplicationContext context);
}
