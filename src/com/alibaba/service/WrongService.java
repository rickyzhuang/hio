/**
 * 
 */
package com.alibaba.service;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.alibaba.dao.WrongMapper;
import com.alibaba.model.Wrong;
import com.alibaba.model.easyui.PageHelper;


/**
 * 
 * WrongService
 * Copyright:Copyright*(c)2015 Company:智业软件股份有限公司
 *
 * @author zhuangjiayin
 * @date 2016-3-3 下午1:48:45
 * @version V1.0
 */
@Service
public class WrongService {

	@Resource
	private WrongMapper wrongMapper;
	
	@Resource(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate;
	
	/**
	 * @param wrongname
	 * @return
	 */
	public Wrong findWrongByid(int id) {
		return wrongMapper.findWrongByid(id);
	}
	
	

	/**
	 *获取数量
	 * @param wrong
	 * @return
	 */
	public Long getDataGridTotal(Wrong wrong) {
		return wrongMapper.getDataGridTotal(wrong);  
	}

	/**
	 * 获取列表
	 * @param page
	 * @return
	 */
	public List<Wrong> getDataGridWrong(Map<String,Object> map) {
//		page.setStart((page.getPage()-1)*page.getRows());
//		page.setEnd(page.getPage()*page.getRows());
		return wrongMapper.getDataGridWrong(map);  
	}

	/**
	 * 新增记录
	 * @param wrong
	 */
	public void add(Wrong wrong) {
		wrongMapper.addWrong(wrong);  
	}

	/**
	 * 编辑记录
	 * @param wrong
	 */
	public void edit(Wrong wrong) {
		wrongMapper.editWrong(wrong);  
	}  
    
	/**
	 * 编辑记录
	 * @param wrong
	 */
	public void deleteById(int id) {
		wrongMapper.deleteById(id);  
	}  
	
	/**
	 * 测试JDBC是否可用
	 * @author zhuangjiayin
	 * @createtime 2016-3-3 下午2:07:18
	 * @return
	 * @throws Exception
	 */
	public   List<Map<String,Object>> testQuery() throws Exception {
		List list=null;
		String sql="select * from wrong order by recode_Date  asc ";
		list=	jdbcTemplate.queryForList(sql);
		return list;
	}
    
}
