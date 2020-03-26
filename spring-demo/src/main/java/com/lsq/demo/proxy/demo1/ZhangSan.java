package com.lsq.demo.proxy.demo1;

public class ZhangSan implements Person {

    private String sex = "男";
    private String name = "张三";

    @Override
    public String getSex() {
        return sex;
    }
    @Override
    public String getName() {
        return name;
    }

    public String findLove(){

        System.out.println("我叫张三执行 findLove");

        return "张三的寻爱之旅";
    }

    @Override
    public String findLove2() {
        System.out.println("我叫张三执行 findLove2");
        return "null";
    }
}
