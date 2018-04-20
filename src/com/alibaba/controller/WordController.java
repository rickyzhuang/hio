/**
 * 
 */
package com.alibaba.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.model.Word;
import com.alibaba.model.easyui.DataGrid;
import com.alibaba.model.easyui.Json;
import com.alibaba.model.easyui.PageHelper;
import com.alibaba.service.WordService;

/**
 * Title:WordController
 * Copyright:Copyright*(c)2015 Company:智业软件股份有限公司
 *
 * @author zhuangjiayin
 * @date 2016-3-3 上午10:22:48
 * @version V1.0
 */
//@RequestMapping("/word")
@Controller
public class WordController {
	private final Logger log = LoggerFactory.getLogger(WordController.class);
	@Resource
	private WordService wordService;
	
	
	/**
	 * 宝贝管理主页面
	 * @return
	 */
	@RequestMapping(value = "/word/list",method = RequestMethod.GET)
    public String wordList(Model model) {
        return "word/list";
    }
	/**
	 * 做题
	 * @return
	 */
	@RequestMapping(value = "/word/exam",method = RequestMethod.GET)
    public String wordHeight(Model model) {
		Long total=wordService.getDataGridTotal(null);
		List<Word> list=new ArrayList<Word>();
		Random r=new Random();
		for(int i=0;i<10;i++){
			int k=r.nextInt(total.intValue()-1);
			list.add(wordService.findWordByid(k));
		}
		model.addAttribute("list",list);
        return "word/exam";
    }
	/**
	 * 体重管理
	 * @return
	 */
	@RequestMapping(value = "/word/weight",method = RequestMethod.GET)
    public String wordWeight(Model model) {
        return "word/weight";
    }
	/**
	 * 头围管理
	 * @return
	 */
	@RequestMapping(value = "/word/head",method = RequestMethod.GET)
    public String wordHead(Model model) {
        return "word/head";
    }
	
	/**
	 * 生长状况
	 * @author zhuangjiayin
	 * @createtime 2016-3-3 下午2:00:42
	 * @param page
	 * @param word
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/word/getDataGridWord", method = RequestMethod.POST)
	public DataGrid getDataGridWord(PageHelper page,Word word) {
		DataGrid dg = new DataGrid();
		page.setStart((page.getPage()-1)*page.getRows());
		page.setEnd(page.getPage()*page.getRows());
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("condition", word);
		map.put("page", page);
		dg.setTotal(wordService.getDataGridTotal(word));
		List<Word> wordList = wordService.getDataGridWord(map);
		dg.setRows(wordList);
		return dg;
	}
	
	/**
	 * 新增用户
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/word/addWord",method = RequestMethod.POST)
    public Json addWord(Word word) {
		Json j = new Json();
		
		try {
			wordService.add(word);
            j.setSuccess(true);
            j.setMsg("用户新增成功！");
            j.setObj(word);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }
	
	/**
     * 修改用户
     * 
     * @param word
     * @return
     */
	@ResponseBody
    @RequestMapping(value = "/word/editWord",method = RequestMethod.POST)
    public Json editWord(Word word) {
        Json j = new Json();
        log.debug("传过来的wordID为："+word.getId());
        try {
        	wordService.edit(word);
            j.setSuccess(true);
            j.setMsg("编辑成功！");
            j.setObj(word);
        } catch (Exception e) {
            // e.printStackTrace();
            j.setMsg(e.getMessage());
        }
        return j;
    }
	
	/**
	 * 新增用户
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/word/deleteById",method = RequestMethod.POST)
    public Json deleteById(int id) {
		Json j = new Json();
		
		try {
			wordService.deleteById(id);
            j.setSuccess(true);
            j.setMsg("删除记录成功！");
            j.setObj(null);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }
	
	
	@ResponseBody
	@RequestMapping(value = "/word/test",method = RequestMethod.GET)
    public  List<Map<String,Object>> test(Model model) {
		List list=new ArrayList();
		  try {
			   list=wordService.testQuery();
			   System.out.println(list);
			  Map<String,Object> m=new HashMap<String ,Object>();
	        } catch (Exception e) {
	             e.printStackTrace();
	        }
	        return list;
		
    }
	
}
