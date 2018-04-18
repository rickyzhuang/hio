/**
 * 
 */
package com.alibaba.model;

import java.sql.Timestamp;
import java.util.Date;

import com.alibaba.common.DateUtil;

/**
 * 
 * Title:Baby
 * Copyright:Copyright*(c)2015 Company:智业软件股份有限公司
 *
 * @author zhuangjiayin
 * @date 2016-3-3 上午11:10:09
 * @version V1.0
 */
public class Word {

			/**
			 * 
			  `id` int(200) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `sound` varchar(200) DEFAULT NULL,
  `mean` varchar(200) DEFAULT NULL,
  `frequency` int(10) DEFAULT NULL,
  `recode_date` datetime DEFAULT NULL,
  `remark` varchar(2000) DEFAULT NULL,
  `operator` varchar(200) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modifitor` varchar(200) DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
			 */
	private int id;
	private String name;
	private String sound;
	private String mean;
	private int frequency;
	private Date recodeDate;
	private String remark;
	private String operator;
	private Timestamp createTime;
	private String modifitor;
	private String recodeDateStr;
	private Timestamp modifyTime;
	
	private String keyword;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSound() {
		return sound;
	}
	public void setSound(String sound) {
		this.sound = sound;
	}
	public String getMean() {
		return mean;
	}
	public void setMean(String mean) {
		this.mean = mean;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public Date getRecodeDate() {
		return recodeDate;
	}
	public void setRecodeDate(Date recodeDate) {
		this.recodeDate = recodeDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getModifitor() {
		return modifitor;
	}
	public void setModifitor(String modifitor) {
		this.modifitor = modifitor;
	}
	public Timestamp getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}


	public String getRecodeDateStr() {
		return recodeDateStr;
	}
	public void setRecodeDateStr(String recodeDateStr) {
		this.recodeDateStr = recodeDateStr;
		if(recodeDateStr!=null){
			this.recodeDate=DateUtil.StrTotimestamp(recodeDateStr);
		}
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	

	
	
	
	
}
