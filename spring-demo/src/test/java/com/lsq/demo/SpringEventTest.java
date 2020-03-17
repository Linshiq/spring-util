package com.lsq.demo;


import com.lsq.demo.listener.entity.TestNotifyEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringEventTest {

    @Autowired
    WebApplicationContext webApplicationContext;

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
}
