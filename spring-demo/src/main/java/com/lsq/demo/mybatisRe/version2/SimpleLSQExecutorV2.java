package com.lsq.demo.mybatisRe.version2;

import com.lsq.demo.mybatisRe.model.TestTable;
import com.lsq.demo.mybatisRe.version1.LSQExecutor;

public class SimpleLSQExecutorV2 implements LSQExecutor {
    /**
     *
     * @param statement sql语句
     * @param parameter sql参数 实际送进来的应该是一个MAP
     * @param <T>
     * @return
     */
    @Override
    public <T> T query(String statement, Object parameter) {
        // 执行JDBC的查询
        TestTable TABLE = new TestTable();
        TABLE.setId(1);
        TABLE.setName("lsq");
        return (T) TABLE;
    }

    @Override
    public <T> T query(Object object, Object parameter) {
        // 执行JDBC的查询
        TestTable TABLE = new TestTable();
        TABLE.setId(1);
        TABLE.setName("lsq");
        return (T) TABLE;
    }
}
