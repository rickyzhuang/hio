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

import com.alibaba.model.Baby;
import com.alibaba.model.easyui.DataGrid;
import com.alibaba.model.easyui.Json;
import com.alibaba.model.easyui.PageHelper;
import com.alibaba.service.BabyService;

/**
 * Title:BabyController
 * Copyright:Copyright*(c)2015 Company:智业软件股份有限公司
 *
 * @author zhuangjiayin
 * @date 2016-3-3 上午10:22:48
 * @version V1.0
 */
//@RequestMapping("/baby")
@Controller
public class BabyController {
	private final Logger log = LoggerFactory.getLogger(BabyController.class);
	@Resource
	private BabyService babyService;
	
	
	/**
	 * 宝贝管理主页面
	 * @return
	 */
	@RequestMapping(value = "/baby/list",method = RequestMethod.GET)
    public String babyList(Model model) {
        return "baby/list";
    }
	/**
	 * 身高管理
	 * @return
	 */
	@RequestMapping(value = "/baby/height",method = RequestMethod.GET)
    public String babyHeight(Model model) {
        return "baby/height";
    }
	/**
	 * 体重管理
	 * @return
	 */
	@RequestMapping(value = "/baby/weight",method = RequestMethod.GET)
    public String babyWeight(Model model) {
        return "baby/weight";
    }
	/**
	 * 头围管理
	 * @return
	 */
	@RequestMapping(value = "/baby/head",method = RequestMethod.GET)
    public String babyHead(Model model) {
        return "baby/head";
    }
	
	/**
	 * 生长状况
	 * @author zhuangjiayin
	 * @createtime 2016-3-3 下午2:00:42
	 * @param page
	 * @param baby
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/baby/getDataGridBaby", method = RequestMethod.POST)
	public DataGrid getDataGridBaby(PageHelper page,Baby baby) {
		DataGrid dg = new DataGrid();
		dg.setTotal(babyService.getDataGridTotal(baby));
		List<Baby> babyList = babyService.getDataGridBaby(page);
		dg.setRows(babyList);
		
		return dg;
	}
	
	/**
	 * 新增用户
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/baby/addBaby",method = RequestMethod.POST)
    public Json addBaby(Baby baby) {
		Json j = new Json();
		
		try {
            babyService.add(baby);
            j.setSuccess(true);
            j.setMsg("用户新增成功！");
            j.setObj(baby);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }
	
	/**
     * 修改用户
     * 
     * @param baby
     * @return
     */
	@ResponseBody
    @RequestMapping(value = "/baby/editBaby",method = RequestMethod.POST)
    public Json editBaby(Baby baby) {
        Json j = new Json();
        log.debug("传过来的babyID为："+baby.getId());
        try {
            babyService.edit(baby);
            j.setSuccess(true);
            j.setMsg("编辑成功！");
            j.setObj(baby);
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
	@RequestMapping(value = "/baby/deleteById",method = RequestMethod.POST)
    public Json deleteById(int id) {
		Json j = new Json();
		
		try {
            babyService.deleteById(id);
            j.setSuccess(true);
            j.setMsg("删除记录成功！");
            j.setObj(null);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }
	
	
	@ResponseBody
	@RequestMapping(value = "/baby/test",method = RequestMethod.GET)
    public  List<Map<String,Object>> test(Model model) {
		List list=new ArrayList();
		  try {
			   list=babyService.testQuery();
			   System.out.println(list);
			  Map<String,Object> m=new HashMap<String ,Object>();
	        } catch (Exception e) {
	             e.printStackTrace();
	        }
	        return list;
		
    }
	
}
