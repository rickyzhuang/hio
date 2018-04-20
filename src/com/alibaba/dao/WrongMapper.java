/**
 * 
 */
package com.alibaba.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.model.Wrong;
import com.alibaba.model.easyui.PageHelper;

/**
 * @author tfj
 * 2014-8-2
 */
public interface WrongMapper {

	public Wrong  findWrongByid(@Param("id") int id);
	
	public Long getDataGridTotal(Wrong Wrong);

	public List<Wrong> getDataGridWrong(Map<String,Object> map);

	public void addWrong(Wrong wrong);

	public void editWrong(Wrong wrong);
	
	public void deleteById(@Param("id") int id);
	
	public Wrong  getByWrong(Wrong Wrong);

}
