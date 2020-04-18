package com.lsq.demo.mybatisRe;

import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.List;

public class LSQSqlsession {

    private LSQExecutor executor;

    private LSQConfiguration configuration;

    public LSQSqlsession(LSQExecutor executor,LSQConfiguration configuration){
        this.executor = executor;
        this.configuration = configuration;
    }

    public <T> T getMapper(Class<T> clazz){

        return configuration.getMapper(clazz,this);
    }

    /**
     *
     * @param statement sql语句
     * @param parameter 参数
     * @param <T>
     * @return
     */
    public <T> T selectOne(String statement, Object parameter) {
        return executor.query(statement,parameter);
    }
}
