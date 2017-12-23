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
public class Baby {

			/**
			 * SELECT
			baby.id,
			baby.height,
			baby.weight,
			baby.head,
			baby.recode_date,
			baby.remark,
			baby.operator,
			baby.create_time,
			baby.modifitor,
			baby.modify_time
			FROM baby
			 */
	private int id;
	private float height;
	private float weight;
	private float head;
	private Timestamp recodeDate;
	private String remark;
	private String operator;
	private Timestamp createTime;
	private String modifitor;
	private Timestamp modifyTime;
	//偷懒写法，用它来存时间字符串
	private String recordDateStr;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getHead() {
		return head;
	}
	public void setHead(float head) {
		this.head = head;
	}
	public Timestamp getRecodeDate() {
		return recodeDate;
	}
	public void setRecodeDate(Timestamp recodeDate) {
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
	
	public String getRecordDateStr() {
		return recordDateStr;
	}
	public void setRecordDateStr(String recordDateStr) {
		this.recordDateStr = recordDateStr;
		this.recodeDate=DateUtil.StrTotimestamp(recordDateStr);
	}
	
	
	
}
