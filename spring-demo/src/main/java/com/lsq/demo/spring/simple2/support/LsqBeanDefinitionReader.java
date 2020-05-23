package com.lsq.demo.spring.simple2.support;

import com.lsq.demo.spring.simple2.beans.LsqBeanDefinition;
import com.lsq.demo.util.IOUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 用于对配置文件进行查询 读取 解析
 */
public class LsqBeanDefinitionReader {

    private Properties config = new Properties();
    private List<String> registerBeanClassname =  new ArrayList<>();
    // 约定好要扫描的包路径配置属性名
    private final String SCAN_PACKAGE = "scanPackage";
    public LsqBeanDefinitionReader(String... location){
        // TODO 需完善
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(location[0]);

        try {
            config.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(is);
        }

        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    public List<String> loadBeanDefinitions(){
        return this.registerBeanClassname;
    }

    /**
     * 对每个bean进行注册包装
     * @return
     */
    public LsqBeanDefinition regitsterBean(String classname){
        LsqBeanDefinition beanDefinition = null;
        if (this.registerBeanClassname.contains(classname)){
            beanDefinition = new LsqBeanDefinition();
            beanDefinition.setBeanClassName(classname);
            beanDefinition.setFactoryBeanName(toLowerCaseFirstOne(classname.substring(classname.lastIndexOf(".") + 1 )));
        }
        return beanDefinition;
    }

    public Properties getConfig(){
        return this.config;
    }

    /**
     * 扫描包
     * @param packname
     */
    private void doScanner(String packname) {

        // replaceAll作用是把.替换为文件路径 例如 xxx.xxx.xx = xxx/xxx/xxx
        String path = "" + packname.replaceAll("\\.", "/");
        URL url = this.getClass().getClassLoader().getResource(path);

        File classDir = new File(url.getFile());

        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(packname);
            } else {
                registerBeanClassname.add(packname+"."+file.getName().replace(".class",""));
            }
        }
    }

    /**
     * 首字母转小写
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}
