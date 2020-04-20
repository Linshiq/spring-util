package com.lsq.demo.mybatisRe.version2;

import com.lsq.demo.mybatisRe.version1.LSQConfiguration;
import com.lsq.demo.mybatisRe.version1.LSQSqlsession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LSQMapperProxyV2 implements InvocationHandler {

    private final LSQSqlsessionV2 sqlSession;

    public LSQMapperProxyV2(LSQSqlsessionV2 sqlSession) {
        this.sqlSession = sqlSession;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        MapperRegister.MapperData mapperData = sqlSession.getConfiguration().getMapperRegister().getMethodMapperMap()
                .get(method.getDeclaringClass().getName()+"."+method.getName());

        // 实际mybatis在取值时，是从MapperRegistry中获取的
        // 但这里为了方便，先这样取
        // 这样判断？为什么？
        if (mapperData != null) {
            return sqlSession.selectOne(mapperData,String.valueOf(args[0]));
        }
        return method.invoke(this,args);
    }
}
