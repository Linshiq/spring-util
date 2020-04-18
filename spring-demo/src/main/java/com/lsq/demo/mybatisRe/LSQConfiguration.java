package com.lsq.demo.mybatisRe;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LSQConfiguration {
    /**
     * 获取mapper
     * @param clazz mapper.class
     * @param sqlsession 上送的sqlsession
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz,LSQSqlsession sqlsession) {
        // 换成MeiPo写法是否可以
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{clazz}, new LSQMapperProxy(sqlsession));
    }

    /**
     * 这里乃是取巧，假设mybatis已经解析xml完毕 且装载完成后形成的一个类
     */
    static class TestMapperXML{

        public static final String namespace = "com.lsq.demo.mybatisRe.mapper.TestMapper";

        public static final Map<String,String>  methodSqlMapping = new ConcurrentHashMap<>();

        static {
            methodSqlMapping.put("selectByKey","select * from tb_test where id = %d");
        }
    }
}
