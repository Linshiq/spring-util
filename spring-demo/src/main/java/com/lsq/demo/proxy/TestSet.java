package com.lsq.demo.proxy;

import com.lsq.demo.proxy.cglib.MeiPo;
import com.lsq.demo.proxy.jdk_proxy.Person;
import com.lsq.demo.proxy.jdk_proxy.ZhangSan;

import java.lang.reflect.Field;

public class TestSet {

    private ZhangSan zhangSan;

    public ZhangSan getZhangSan() {
        return zhangSan;
    }

    public void setZhangSan(ZhangSan zhangSan) {
        this.zhangSan = zhangSan;
    }

    public static void main(String[] args) throws Exception{
        Person person = (ZhangSan) new MeiPo().getInstance(new ZhangSan().getClass());
        person.findLove();
        TestSet testSet = new TestSet();

        Field[] fields = testSet.getClass().getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            field.set(testSet,person);
        }
    }
}
