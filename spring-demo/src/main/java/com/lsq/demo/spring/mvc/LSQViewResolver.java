package com.lsq.demo.spring.mvc;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.RandomAccessFile;

public class LSQViewResolver {

    private String name ;

    private File template;

    public LSQViewResolver(String name,File template){
        this.name = name;
        this.template = template;
    }

    public String viewResolver(LSQModelAndView mc) throws Exception{
        StringBuffer sb = new StringBuffer();
        // 把模板（也就是页面内容）内容读进去
        RandomAccessFile ra = new RandomAccessFile(this.template,"r");

        String line = null;
        while (null != (line = ra.readLine())){
            //开始解析每一行
        }

        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getTemplate() {
        return template;
    }

    public void setTemplate(File template) {
        this.template = template;
    }
}
