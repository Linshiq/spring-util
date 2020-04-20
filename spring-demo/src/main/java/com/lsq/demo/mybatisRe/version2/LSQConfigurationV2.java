package com.lsq.demo.mybatisRe.version2;

import com.lsq.demo.mybatisRe.version1.LSQMapperProxy;
import com.lsq.demo.mybatisRe.version1.LSQSqlsession;

import javax.annotation.PostConstruct;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LSQConfigurationV2 {

    private MapperRegister mapperRegister = new MapperRegister();

    /**
     * 获取mapper
     * @param clazz mapper.class
     * @param sqlsession 上送的sqlsession
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz,LSQSqlsessionV2 sqlsession) {
        // 换成MeiPo写法是否可以
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{clazz}, new LSQMapperProxyV2(sqlsession));
    }

   public MapperRegister getMapperRegister(){
       return mapperRegister;
   }

   @PostConstruct
   private void loadMapperRegister(){

   }
}
