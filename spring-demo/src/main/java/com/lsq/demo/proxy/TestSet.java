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
        Person person = (Person) new CglibMeiPo().getInstance(new ZhangSan().getClass());
       person.findLove();
        TestSet testSet = new TestSet();
        LSQAopProxyUtils.getTargetObject(person);
        Field[] fields = testSet.getClass().getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            field.set(testSet,person);
        }
        System.out.println("11"+testSet.getZhangSan().getSex());
    }
}
