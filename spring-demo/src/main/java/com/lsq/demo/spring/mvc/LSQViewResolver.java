package com.lsq.demo.spring.mvc;

import java.io.File;

public class LSQViewResolver {

    private String name ;

    private File template;

    public LSQViewResolver(String name,File template){
        this.name = name;
        this.template = template;
    }
}
