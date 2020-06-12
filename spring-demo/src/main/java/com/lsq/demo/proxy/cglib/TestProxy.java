package com.lsq.demo.proxy.cglib;

import com.lsq.demo.proxy.Person;
import org.springframework.cglib.core.DebuggingClassWriter;

public class TestProxy {

    public static void main(String[] args) {
        // 将新生产的代理class丢到指定目录
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\\\classes");

        Person person = (Person) new CglibMeiPo().getInstance(Child.class);

        person.findLove();
        person.findLove3("123");
    }
}
