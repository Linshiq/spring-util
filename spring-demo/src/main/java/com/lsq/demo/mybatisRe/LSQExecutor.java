package com.lsq.demo.mybatisRe;

public interface LSQExecutor {
    /**
     * 执行JDBC的查询
     * @param statement sql语句
     * @param parameter sql参数
     * @param <T>
     * @return
     */
    public <T> T query(String statement, Object parameter);
}
