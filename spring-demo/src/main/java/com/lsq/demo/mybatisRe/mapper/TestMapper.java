package com.lsq.demo.mybatisRe.mapper;

import com.lsq.demo.mybatisRe.model.TestTable;

public interface TestMapper {

    TestTable selectByKey(int id,String name);
}
