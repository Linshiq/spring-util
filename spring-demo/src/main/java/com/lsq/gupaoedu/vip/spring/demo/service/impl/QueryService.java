package com.lsq.gupaoedu.vip.spring.demo.service.impl;

import com.lsq.gupaoedu.vip.spring.demo.service.IQueryService;
import com.lsq.gupaoedu.vip.spring.formework.annotation.GPService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 查询业务
 * @author Tom
 *
 */
@GPService
public class QueryService implements IQueryService {

	/**
	 * 查询
	 */
	public String query(String name) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		String json = "{name:\"" + name + "\",time:\"" + time + "\"}";
		return json;
	}

}
