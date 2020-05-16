package com.lsq.demo.mybatisRe.mapper;

import com.lsq.demo.mybatisRe.model.TestTable;
import org.apache.ibatis.annotations.Select;

public interface TestMapper {

    @Select("")
    TestTable selectByKey(int id,String name);
}
