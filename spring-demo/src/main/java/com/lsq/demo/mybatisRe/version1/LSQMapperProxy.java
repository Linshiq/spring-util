package com.lsq.demo.mybatisRe.version1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LSQMapperProxy implements InvocationHandler {

    private final LSQSqlsession sqlSession;

    public LSQMapperProxy(LSQSqlsession sqlSession) {
        this.sqlSession = sqlSession;
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

    public static void main(String[] args) {
        int i = 0;
        System.out.println(++i);

        System.out.println(i);

        System.out.println(i++);

        System.out.println(i);
    }
}
