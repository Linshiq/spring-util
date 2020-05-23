package com.lsq.demo.spring.simple.controller;

import com.lsq.demo.spring.annotation.LSQAutowired;
import com.lsq.demo.spring.annotation.LSQController;

@LSQController
public class ControllerOne {

    @LSQAutowired
    public ServiceTest serviceTest;
}
