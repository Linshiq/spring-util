package com.lsq.demo.proxy;

import com.lsq.demo.proxy.cglib.CglibMeiPo;
import com.lsq.demo.proxy.jdk_proxy.JDKMeiPo;
import com.lsq.demo.proxy.jdk_proxy.Person;
import com.lsq.demo.proxy.jdk_proxy.ZhangSan;
import com.lsq.demo.spring.aop.LSQAopProxyUtils;

import java.lang.reflect.Field;

public class TestSet {

    private Person zhangSan;

    public Person getZhangSan() {
        return zhangSan;
    }

    public void setZhangSan(Person zhangSan) {
        this.zhangSan = zhangSan;
    }

    public static void main(String[] args) throws Exception{
        Object obj = new JDKMeiPo().getInstance(new ZhangSan());
        TestSet testSet = new TestSet();
        Field[] fields = testSet.getClass().getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            field.set(testSet,obj);
        }
        System.out.println("11"+testSet.getZhangSan().getSex());
    }
}
