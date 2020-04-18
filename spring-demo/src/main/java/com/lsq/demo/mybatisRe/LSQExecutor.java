package com.lsq.demo.mybatisRe;

public interface LSQExecutor {
    public <T> T query(String statement, Object parameter);
}
