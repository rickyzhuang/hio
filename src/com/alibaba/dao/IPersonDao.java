package com.alibaba.dao;

import java.util.List;
import java.util.Map;

public interface IPersonDao extends IBaseDao {
	
	public   List<Map<String,Object>> testQuery() throws Exception;

}
