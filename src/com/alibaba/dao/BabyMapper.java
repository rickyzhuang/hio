/**
 * 
 */
package com.alibaba.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.model.Baby;
import com.alibaba.model.easyui.PageHelper;

/**
 * @author tfj
 * 2014-8-2
 */
public interface BabyMapper {

	public Baby  findBabyByid(@Param("id") int id);
	
	public Long getDataGridTotal(Baby Baby);

	public List<Baby> getDataGridBaby(PageHelper page);

	public void addBaby(Baby baby);

	public void editBaby(Baby baby);
	
	public void deleteById(@Param("id") int id);

}
