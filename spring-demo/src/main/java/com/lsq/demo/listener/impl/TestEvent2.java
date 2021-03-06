package com.lsq.demo.listener.impl;

import com.lsq.demo.listener.TestApplicationListener;
import com.lsq.demo.listener.entity.TestNotifyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TestEvent2 extends TestApplicationListener {

    private static final Logger logger = LoggerFactory.getLogger(TestEvent2.class);

    public void doProcess(TestNotifyEvent testNotifyEvent) {

        logger.info("执行TestEvent2");
        testNotifyEvent.setMsg("执行TestEvent2");
    }
}
