package com.lsq.demo.spring_simple.controller;

import com.lsq.demo.spring_simple.annotation.LSQAutowired;
import com.lsq.demo.spring_simple.annotation.LSQController;
import com.lsq.demo.spring_simple.annotation.LSQService;
import org.springframework.stereotype.Service;

@LSQController
public class ControllerOne {

    @LSQAutowired
    public ServiceTest serviceTest;
}
