/**
 * 
 */
package com.alibaba.service;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.alibaba.dao.BabyMapper;
import com.alibaba.model.Baby;
import com.alibaba.model.easyui.PageHelper;


/**
 * 
 * Title:BabyService
 * Copyright:Copyright*(c)2015 Company:智业软件股份有限公司
 *
 * @author zhuangjiayin
 * @date 2016-3-3 下午1:48:45
 * @version V1.0
 */
@Service
public class BabyService {

	@Resource
	private BabyMapper babyMapper;
	
	@Resource(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate;
	
	/**
	 * @param babyname
	 * @return
	 */
	public Baby findBabyByid(int id) {
		return babyMapper.findBabyByid(id);
	}
	
	

	/**
	 *获取数量
	 * @param baby
	 * @return
	 */
	public Long getDataGridTotal(Baby baby) {
		return babyMapper.getDataGridTotal(baby);  
	}

	/**
	 * 获取列表
	 * @param page
	 * @return
	 */
	public List<Baby> getDataGridBaby(PageHelper page) {
		page.setStart((page.getPage()-1)*page.getRows());
		page.setEnd(page.getPage()*page.getRows());
		return babyMapper.getDataGridBaby(page);  
	}

	/**
	 * 新增记录
	 * @param baby
	 */
	public void add(Baby baby) {
		babyMapper.addBaby(baby);  
	}

	/**
	 * 编辑记录
	 * @param baby
	 */
	public void edit(Baby baby) {
		babyMapper.editBaby(baby);  
	}  
    
	/**
	 * 编辑记录
	 * @param baby
	 */
	public void deleteById(int id) {
		babyMapper.deleteById(id);  
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
		String sql="select * from baby order by recode_Date  asc ";
		list=	jdbcTemplate.queryForList(sql);
		return list;
	}
    
}
