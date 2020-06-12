package com.lsq.demo.spring.controller;

import com.lsq.demo.spring.annotation.LSQAutowired;
import com.lsq.demo.spring.annotation.LSQController;

@LSQController
public class ControllerOneService {

    @LSQAutowired
    public TestService serviceTest;

    public void add(String a){
//        serviceTest.add();
        System.out.println("ControllerOne.add 啊啊啊" + a);
    }
}
