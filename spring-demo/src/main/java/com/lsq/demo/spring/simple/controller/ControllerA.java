package com.lsq.demo.spring.simple.controller;

import com.lsq.demo.spring.annotation.LSQAutowired;
import com.lsq.demo.spring.annotation.LSQController;

@LSQController
public class ControllerA {

    @LSQAutowired
    public TestService serviceTest;

    public void add(String a){
        serviceTest.add();
        System.out.println("ControllerA.add 啊啊啊");
    }
}
