package com.lsq.demo;


import com.lsq.demo.listener.entity.TestNotifyEvent;
import com.lsq.demo.spring.aop.LSQAopProxyUtils;
import com.lsq.demo.spring.simple.LsqDispatcherServlet;
import com.lsq.demo.spring.simple.controller.ControllerOneService;
import com.lsq.demo.spring.simple2.context.LsqApplicationContext;
import com.lsq.gupaoedu.vip.spring.formework.context.GPApplicationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringEventTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    LsqDispatcherServlet dispatcherServlet;

    /**
     * 测试事件是否多重执行
     */
    @Test
    public void test01(){
        TestNotifyEvent testNotifyEvent = new TestNotifyEvent(this,"test01");
        System.out.println("输入事件类型开始");
        webApplicationContext.publishEvent(testNotifyEvent);
        System.out.println("输入事件类型结束");
        //``
        System.out.println(testNotifyEvent.getMsg());
    }

    /**
     * 测试事件是否多重执行
     */
    @Test
    public void test02() throws Exception {
        //dispatcherServlet.init();
        LsqApplicationContext context = new LsqApplicationContext("application.properties");
        ControllerOneService controllerOne = (ControllerOneService) context.getBean("controllerOneService");
        controllerOne.add("1");
        System.out.println(context.getBean("controllerOneService"));
    }

    /**
     * 测试事件是否多重执行
     */
    @Test
    public void test03() throws Exception {
        //dispatcherServlet.init();
        GPApplicationContext context =  new GPApplicationContext("application.properties");
        ControllerOneService controllerOne = (ControllerOneService) LSQAopProxyUtils.getTargetObject(context.getBean("controllerOneService"));
        controllerOne.add("1");
        System.out.println(context.getBean("controllerOne"));
    }
}
