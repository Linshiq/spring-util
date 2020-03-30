package com.lsq.demo.proxy.jdk_proxy;

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
        return "我找真爱的第二条路";
    }

    @Override
    public String findLove3(String str) {
        System.out.println("我叫张三执行 findLove3,参数是"+str);
        return "有参数的";
    }
}
