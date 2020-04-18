package com.lsq.demo.mybatisRe;

import com.lsq.demo.mybatisRe.model.TestTable;

public class SimpleLSQExecutor implements LSQExecutor {
    @Override
    public <T> T query(String statement, Object parameter) {
        // 执行JDBC的查询
        TestTable TABLE = new TestTable();
        TABLE.setId(1);
        TABLE.setName("lsq");
        return (T) TABLE;
    }
}
