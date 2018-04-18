/**
 * 
 */
package com.alibaba.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.model.Word;
import com.alibaba.model.easyui.PageHelper;

/**
 * @author tfj
 * 2014-8-2
 */
public interface WordMapper {

	public Word  findWordByid(@Param("id") int id);
	
	public Long getDataGridTotal(Word Word);

	public List<Word> getDataGridWord(Map<String,Object> map);

	public void addWord(Word word);

	public void editWord(Word word);
	
	public void deleteById(@Param("id") int id);

}
