package com.lsq.demo.mybatisRe.version1;

import com.lsq.demo.mybatisRe.mapper.TestMapper;
import com.lsq.demo.mybatisRe.model.TestTable;

public class MybatisTest {

    public static void main(String[] args) {
        LSQSqlsession sqlsession = new LSQSqlsession(new SimpleLSQExecutor(),new LSQConfiguration());
        TestMapper mapper = sqlsession.getMapper(TestMapper.class);
        TestTable str = mapper.selectByKey(1,"123");

        System.out.println(str.getName());
    }
}
