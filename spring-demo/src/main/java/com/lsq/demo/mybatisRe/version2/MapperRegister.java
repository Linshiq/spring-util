package com.lsq.demo.mybatisRe.version2;

import com.lsq.demo.mybatisRe.model.TestTable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapperRegister {

    private final Map<String,MapperData> methodMapperMap = new ConcurrentHashMap<>();

    public MapperRegister(){
        methodMapperMap.put("com.lsq.demo.mybatisRe.mapper.TestMapper.selectByKey",new MapperData<>("select * from tb_test where id = %d", TestTable.class));
    }

    public class MapperData<T> {

        public String sql;
        public Class<T> resutlType;

        public MapperData(String sql,Class<T> resutlType){
            this.sql = sql;
            this.resutlType = resutlType;
        }
    }

    public Map<String, MapperData> getMethodMapperMap() {
        return methodMapperMap;
    }
}
