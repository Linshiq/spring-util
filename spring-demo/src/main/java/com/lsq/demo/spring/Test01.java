package com.lsq.demo.spring;

import com.lsq.demo.spring.simple.controller.ControllerA;
import com.lsq.demo.spring.simple.controller.ControllerOneService;
import com.lsq.demo.spring.simple2.context.LsqApplicationContext;

public class Test01 {

    public static void main(String[] args) {
        LsqApplicationContext context = new LsqApplicationContext("application.properties");
        ControllerOneService controllerOne = (ControllerOneService) context.getBean("controllerOneService");
        controllerOne.add("1");
        System.out.println(context.getBean("controllerOneService"));
        ControllerA controllerOneA = (ControllerA) context.getBean("controllerA");
        controllerOneA.add("1");
    }
}
