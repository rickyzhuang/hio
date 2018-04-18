/**
 * 
 */
package com.alibaba.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.model.Wrong;
import com.alibaba.model.easyui.DataGrid;
import com.alibaba.model.easyui.Json;
import com.alibaba.model.easyui.PageHelper;
import com.alibaba.service.WrongService;

/**
 * Title:WrongController
 * Copyright:Copyright*(c)2015 Company:智业软件股份有限公司
 *
 * @author zhuangjiayin
 * @date 2016-3-3 上午10:22:48
 * @version V1.0
 */
//@RequestMapping("/wrong")
@Controller
public class WrongController {
	private final Logger log = LoggerFactory.getLogger(WrongController.class);
	@Resource
	private WrongService wrongService;
	
	
	/**
	 * 宝贝管理主页面
	 * @return
	 */
	@RequestMapping(value = "/wrong/list",method = RequestMethod.GET)
    public String wrongList(Model model) {
        return "wrong/list";
    }
	/**
	 * 身高管理
	 * @return
	 */
	@RequestMapping(value = "/wrong/height",method = RequestMethod.GET)
    public String wrongHeight(Model model) {
        return "wrong/height";
    }
	/**
	 * 体重管理
	 * @return
	 */
	@RequestMapping(value = "/wrong/weight",method = RequestMethod.GET)
    public String wrongWeight(Model model) {
        return "wrong/weight";
    }
	/**
	 * 头围管理
	 * @return
	 */
	@RequestMapping(value = "/wrong/head",method = RequestMethod.GET)
    public String wrongHead(Model model) {
        return "wrong/head";
    }
	
	/**
	 * 生长状况
	 * @author zhuangjiayin
	 * @createtime 2016-3-3 下午2:00:42
	 * @param page
	 * @param wrong
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/wrong/getDataGridWrong", method = RequestMethod.POST)
	public DataGrid getDataGridWrong(PageHelper page,Wrong wrong) {
		DataGrid dg = new DataGrid();
		page.setStart((page.getPage()-1)*page.getRows());
		page.setEnd(page.getPage()*page.getRows());
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("condition", wrong);
		map.put("page", page);
		dg.setTotal(wrongService.getDataGridTotal(wrong));
		List<Wrong> wrongList = wrongService.getDataGridWrong(map);
		dg.setRows(wrongList);
		return dg;
	}
	
	/**
	 * 新增用户
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/wrong/addWrong",method = RequestMethod.POST)
    public Json addWrong(Wrong wrong) {
		Json j = new Json();
		
		try {
			wrongService.add(wrong);
            j.setSuccess(true);
            j.setMsg("用户新增成功！");
            j.setObj(wrong);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }
	
	/**
     * 修改用户
     * 
     * @param wrong
     * @return
     */
	@ResponseBody
    @RequestMapping(value = "/wrong/editWrong",method = RequestMethod.POST)
    public Json editWrong(Wrong wrong) {
        Json j = new Json();
        log.debug("传过来的wrongID为："+wrong.getId());
        try {
        	wrongService.edit(wrong);
            j.setSuccess(true);
            j.setMsg("编辑成功！");
            j.setObj(wrong);
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
	@RequestMapping(value = "/wrong/deleteById",method = RequestMethod.POST)
    public Json deleteById(int id) {
		Json j = new Json();
		
		try {
			wrongService.deleteById(id);
            j.setSuccess(true);
            j.setMsg("删除记录成功！");
            j.setObj(null);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }
	
	
	@ResponseBody
	@RequestMapping(value = "/wrong/test",method = RequestMethod.GET)
    public  List<Map<String,Object>> test(Model model) {
		List list=new ArrayList();
		  try {
			   list=wrongService.testQuery();
			   System.out.println(list);
			  Map<String,Object> m=new HashMap<String ,Object>();
	        } catch (Exception e) {
	             e.printStackTrace();
	        }
	        return list;
		
    }
	
}
