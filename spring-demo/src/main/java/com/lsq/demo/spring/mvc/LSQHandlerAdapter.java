package com.lsq.demo.spring.mvc;

import java.util.HashMap;
import java.util.Map;

public class LSQHandlerAdapter {

    private Map<String,Integer> paramMapping = new HashMap<>();

    public LSQHandlerAdapter(Map<String,Integer> paramMapping){
        this.paramMapping = paramMapping;
    }

}
