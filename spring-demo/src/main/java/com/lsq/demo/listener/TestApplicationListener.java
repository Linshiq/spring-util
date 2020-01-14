package com.lsq.demo.listener;

import com.lsq.demo.listener.entity.TestNotifyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

public abstract class TestApplicationListener implements ApplicationListener<TestNotifyEvent> {

    private static final Logger logger = LoggerFactory.getLogger(TestApplicationListener.class);

    @Override
    public void onApplicationEvent(TestNotifyEvent testNotifyEvent) {
        logger.info("开始执行TestApplicationListener-TestNotifyEvent事件");
        doProcess(testNotifyEvent);
    }

    public abstract void doProcess(TestNotifyEvent testNotifyEvent);
}
