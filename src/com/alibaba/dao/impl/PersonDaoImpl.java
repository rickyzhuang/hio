package com.alibaba.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.dao.IPersonDao;
import com.alibaba.dao.UserMapper;


@Repository("personDao")
public class PersonDaoImpl   implements IPersonDao {
	
	@Resource
	private UserMapper userMapper;
	
	@Resource(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate;
	
	Logger log = Logger.getLogger("BaseDaoImpl");

	public void setLogger(Logger log) {
		this.log = log;
	}

	@Override
	public <T> List<T> qryListBySql(String sql, Class obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> boolean add(T obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public   List<Map<String,Object>> testQuery() throws Exception {
		List list=null;
		String sql="select * from sysmenu";
		list=	jdbcTemplate.queryForList(sql);
		return list;
	}
	
}
