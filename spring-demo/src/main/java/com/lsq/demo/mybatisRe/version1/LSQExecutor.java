package com.lsq.demo.mybatisRe.version1;

import com.lsq.demo.mybatisRe.version2.MapperRegister;

public interface LSQExecutor {
    /**
     * 执行JDBC的查询
     * @param statement sql语句
     * @param parameter sql参数
     * @param <T>
     * @return
     */
    public <T> T query(String statement, Object parameter);

    /**
     * 执行JDBC的查询
     * @param
     * @param parameter sql参数
     * @param <T>
     * @return
     */
    public <T> T query(MapperRegister.MapperData object, Object parameter);
}
