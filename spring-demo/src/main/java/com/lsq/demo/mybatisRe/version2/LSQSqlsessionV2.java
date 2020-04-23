package com.lsq.demo.mybatisRe.version2;

import antlr.PythonCharFormatter;
import com.lsq.demo.mybatisRe.version1.LSQConfiguration;
import com.lsq.demo.mybatisRe.version1.LSQExecutor;

public class LSQSqlsessionV2 {

    private LSQExecutor executor;

    private LSQConfigurationV2 configuration;

    public LSQSqlsessionV2(LSQExecutor executor, LSQConfigurationV2 configuration){
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

    public LSQConfigurationV2 getConfiguration(){
        return configuration;
    }

    /**
     *
     * @param statement sql语句
     * @param parameter 参数
     * @param <T>
     * @return
     */
    public <T> T selectOne(MapperRegister.MapperData obj, Object parameter) {
        return executor.query(obj,parameter);
    }
}
