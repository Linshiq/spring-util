package com.lsq.demo.spring.mvc;

import java.util.Map;

public class LSQModelAndView {

    private String viewName;

    private Map<String,Object> modle;

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModle() {
        return modle;
    }

    public void setModle(Map<String, Object> modle) {
        this.modle = modle;
    }
}
