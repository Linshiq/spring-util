package com.lsq.demo;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MybatisTest {

    public static void main(String[] args) throws Exception {
        Thread.currentThread().getContextClassLoader();
        URL l1 =
                Thread.currentThread().getContextClassLoader().getResource("generatorConfig.xml");
        System.out.println(l1);
//
//        URL l2 =
//                Thread.currentThread().getContextClassLoader().getResource("generatorConfig.xml");
//        System.out.println(l2);
//
//        URL l3 = Thread.currentThread().getContextClassLoader().getResource("generatorConfig.xml");
//        String l4=l3.getPath();//加上getPath()则去掉前面的file:
//        System.out.println(l3);  //     file:/F:/demo/Studying/out/production/Studying/test3.xml
//        System.out.println(l4);//      /F:/demo/Studying/out/production/Studying/test3.xml

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File(l1.getFile());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
