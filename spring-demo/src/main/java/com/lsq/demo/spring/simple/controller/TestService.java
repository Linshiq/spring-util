package com.lsq.demo.spring.simple.controller;

import com.lsq.demo.spring.annotation.LSQService;

@LSQService
public class TestService {

    public void add(){
        System.out.println("TestService.add 哈哈哈哈");
    }
}
