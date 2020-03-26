package com.lsq.demo.proxy.demo1;

public class TestProxyFindLove {

    public static void main(String[] args) {
        Person person = (Person) new MeiPo().getInstance(new ZhangSan());
        // 实际上下面这个方法并不会实际执行
        System.out.println(person.findLove());;
        System.out.println(person.findLove2());
    }
}
