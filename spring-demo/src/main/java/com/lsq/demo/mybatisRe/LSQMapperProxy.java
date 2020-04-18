package com.lsq.demo.mybatisRe;

import com.lsq.demo.proxy.jdk_proxy.Person;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LSQMapperProxy implements InvocationHandler {

    private Person targer;
    private final LSQSqlsession sqlSession;

    public LSQMapperProxy(LSQSqlsession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Object getInstance(Person person){
        this.targer = person;
        Class clazz = person.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 实际mybatis在取值时，是从MapperRegistry中获取的
        // 但这里为了方便，先这样取
        // 这样判断？为什么？
        if (method.getDeclaringClass().getName().equals(LSQConfiguration.TestMapperXML.namespace)) {
            String sql = LSQConfiguration.TestMapperXML.methodSqlMapping.get(method.getName());
            return sqlSession.selectOne(sql,String.valueOf(args[0]));
        }
        return method.invoke(this,args);
    }
}
