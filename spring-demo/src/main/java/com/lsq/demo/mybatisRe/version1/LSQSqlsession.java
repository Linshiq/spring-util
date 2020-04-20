package com.lsq.demo.mybatisRe.version1;

public class LSQSqlsession {

    private LSQExecutor executor;

    private LSQConfiguration configuration;

    public LSQSqlsession(LSQExecutor executor,LSQConfiguration configuration){
        this.executor = executor;
        this.configuration = configuration;
    }

    /**
     * 获取mapper
     * @param clazz mapper.class
     * @param <T>
     * @return
     */
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
