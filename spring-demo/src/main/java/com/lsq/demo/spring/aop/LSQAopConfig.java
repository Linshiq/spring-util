package com.lsq.demo.spring.aop;

import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class LSQAopConfig {

    private Map<Method,LSQAspect> points = new HashMap<>();

    public void put(Method method,Object obj,Method[] points){
        this.points.put(method,new LSQAspect(obj,points));

    }

    public LSQAspect get(Method method) {
        if (CollectionUtils.isEmpty(points)){
            return null;
        }
        return this.points.get(method);
    }

    public boolean contain(Method method){
        return this.points.containsKey(method);
    }

    public class LSQAspect{
        private Object aspect;
        private Method[] points;
        public LSQAspect(Object obj,Method[] points){
            this.aspect =obj;
            this.points = points;
        }
        public Object getAspect() {
            return aspect;
        }
        public Method[] getPoints() {
            return points;
        }
    }
}
