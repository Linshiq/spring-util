package com.lsq.gupaoedu.vip.spring.formework;

import com.lsq.demo.spring.aop.LSQAopProxyUtils;
import com.lsq.demo.spring.controller.ControllerOneService;
import com.lsq.gupaoedu.vip.spring.demo.action.MyAction;
import com.lsq.gupaoedu.vip.spring.formework.context.GPApplicationContext;

public class Test02 {

    public static void main(String[] args) throws Exception{
        GPApplicationContext context =  new GPApplicationContext("application.properties");
        MyAction controllerOne = (MyAction) context.getBean("myAction");
        controllerOne.add();
        System.out.println(context.getBean("myAction"));
    }
}
