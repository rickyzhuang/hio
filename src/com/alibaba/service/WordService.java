/**
 * 
 */
package com.alibaba.service;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.alibaba.dao.WordMapper;
import com.alibaba.model.Word;
import com.alibaba.model.easyui.PageHelper;


/**
 * 
 * WordService
 * Copyright:Copyright*(c)2015 Company:智业软件股份有限公司
 *
 * @author zhuangjiayin
 * @date 2016-3-3 下午1:48:45
 * @version V1.0
 */
@Service
public class WordService {

	@Resource
	private WordMapper wordMapper;
	
	@Resource(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate;
	
	/**
	 * @param wordname
	 * @return
	 */
	public Word findWordByid(int id) {
		return wordMapper.findWordByid(id);
	}
	
	

	/**
	 *获取数量
	 * @param word
	 * @return
	 */
	public Long getDataGridTotal(Word word) {
		return wordMapper.getDataGridTotal(word);  
	}

	/**
	 * 获取列表
	 * @param page
	 * @return
	 */
	public List<Word> getDataGridWord(Map<String,Object> map) {
//		page.setStart((page.getPage()-1)*page.getRows());
//		page.setEnd(page.getPage()*page.getRows());
		return wordMapper.getDataGridWord(map);  
	}

	/**
	 * 新增记录
	 * @param word
	 */
	public void add(Word word) {
		wordMapper.addWord(word);  
	}

	/**
	 * 编辑记录
	 * @param word
	 */
	public void edit(Word word) {
		wordMapper.editWord(word);  
	}  
    
	/**
	 * 编辑记录
	 * @param word
	 */
	public void deleteById(int id) {
		wordMapper.deleteById(id);  
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
		String sql="select * from word order by recode_Date  asc ";
		list=	jdbcTemplate.queryForList(sql);
		return list;
	}
    
}
