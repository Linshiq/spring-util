package com.lsq.demo.listener.entity;

import org.springframework.context.ApplicationEvent;

public class TestNotifyEvent extends ApplicationEvent {

    private String key;
    private String msg;

    public TestNotifyEvent(Object source, String key) {
        super(source);
        this.key = key;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
