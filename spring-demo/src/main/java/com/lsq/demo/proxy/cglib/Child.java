package com.lsq.demo.proxy.cglib;

import com.lsq.demo.proxy.Person;

public class Child implements Person {
    @Override
    public String getSex() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String findLove() {
        return "findLove";
    }

    @Override
    public String findLove2() {
        return "findLove2";
    }

    @Override
    public String findLove3(String str) {
        return "findLove3"+str;
    }
}
