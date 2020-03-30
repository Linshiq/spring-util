package com.lsq.demo.proxy.jdk_proxy;

public class Zhangxingxing implements Person {

    private String sex = "女";
    private String name = "妹子1号";

    @Override
    public String getSex() {
        return sex;
    }
    @Override
    public String getName() {
        return name;
    }

    public String findLove(){

        System.out.println("我叫张三");

        return "妹子1号的寻爱之旅";
    }

    @Override
    public String findLove2() {
        System.out.println("失策2号");
        return null;
    }

    @Override
    public String findLove3(String str) {
        System.out.println("我叫张三执行 findLove3");
        return "有参数的";
    }
}
