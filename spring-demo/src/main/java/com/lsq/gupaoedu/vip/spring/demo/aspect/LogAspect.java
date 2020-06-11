package com.lsq.gupaoedu.vip.spring.demo.aspect;

import com.lsq.gupaoedu.vip.spring.formework.aop.aspect.GPJoinPoint;

/**
 * Created by Tom.
 */
public class LogAspect {

    //在调用一个方法之前，执行before方法
    public void before(GPJoinPoint joinPoint){
        joinPoint.setUserAttribute("startTime_" + joinPoint.getMethod().getName(),System.currentTimeMillis());

    }

    //在调用一个方法之后，执行after方法
    public void after(GPJoinPoint joinPoint){

        long startTime = (Long) joinPoint.getUserAttribute("startTime_" + joinPoint.getMethod().getName());
        long endTime = System.currentTimeMillis();
        System.out.println("use time :" + (endTime - startTime));
    }

    public void afterThrowing(GPJoinPoint joinPoint, Throwable ex){

    }

}
