package com.lsq.demo.proxy.demo1;

public class TestProxyFindLove {

    public static void main(String[] args) {
        Person person = (Person) new MeiPo().getInstance(new ZhangSan());
        // 实际上下面这个方法并不会实际执行 只会按照代理方法的invoke的顺序去执行
        // 也就是说 findlove 和 findlove2结果是一致的
        System.out.println(person.findLove());;
        System.out.println(person.findLove2());
    }
}
