package com.lsq.demo.spring.simple2.aspect;

public class LogAspect {

     public void before(){
         System.out.println("before - 我被调用了");
     }

     public void after(){
         System.out.println("after - 我被调用了");
     }
}
