package com.lsq.demo.proxy.jdk_proxy;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;

public class TestProxyFindLove {

    public static void main(String[] args) throws Exception {
        Person person = (Person) new MeiPo().getInstance(new ZhangSan());
        // 实际上下面这个方法并不会实际执行 只会按照代理方法的invoke的顺序去执行
        // 也就是说 findlove 和 findlove2结果是一致的
        System.out.println(person.findLove());;
        System.out.println(person.findLove2());
        System.out.println(person.findLove3("test"));
        proProxyCode(person);
    }

    /**
     * 代理原理:
     * 1.拿到被代理对象的引用，然后获取他的接口
     * 2.JDK代理重新生成了一个类，同时实现我们给的代理对象的所实现的接口
     * 3.把被代理对象的引用也拿到了
     * 4.重新生成一个class字节码
     * 5.然后编译
     */
    private static void proProxyCode(Person person) throws Exception{

        File directory = new File("");
        String createPath = directory.getAbsolutePath() + "/src/main/java/com/lsq/demo/proxy/demo1/";

        byte[] data = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{person.getClass()});
        // 生成的地方在spring-util下面，也就是父目录的根目录下
        FileOutputStream os = new FileOutputStream("$Proxy0.class");
        os.write(data);
        os.close();
    }
}
