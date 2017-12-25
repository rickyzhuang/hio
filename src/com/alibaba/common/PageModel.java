package com.alibaba.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 */
@SuppressWarnings("rawtypes")
public class PageModel implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private static final Log LOGGER = LogFactory.getLog(PageModel.class);

	private Integer pageSize = Integer.valueOf(10);
	private String currentPage = "1";
	private long allCount;
	
	private List result = new ArrayList();
	private List<QueryStrategy> strategies = new ArrayList<QueryStrategy>();
	private List<OrderStrategy> orderStrategies = new ArrayList<OrderStrategy>();
	private String pageBar = "";

	public Integer getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize.intValue() < 1) {
			LOGGER.warn(new StringBuilder().append("pageSize :").append(pageSize).append("小于1,自动设置为1").toString());
			pageSize = Integer.valueOf(1);
		}
		this.pageSize = pageSize;
	}

	public String getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(String currentPage) {
		try {
			Integer current = Integer.valueOf(currentPage);
			if (current.intValue() < 1) {
				LOGGER.warn(new StringBuilder().append("currentPage :").append(current).append("小于1,自动设置为1").toString());
				this.currentPage = "1";
			}
			this.currentPage = currentPage;
		} catch (NumberFormatException e) {
			this.currentPage = "1";
		}
	}

	public Long getAllCount() {
		return Long.valueOf(this.allCount);
	}

	public void setAllCount(Long allCount) {
		this.allCount = allCount.longValue();
		checkCurrentPage(allCount.longValue());
	}

	public List getResult() {
		return this.result;
	}

	public void setResult(List result) {
		this.result = result;
	}

	public void checkCurrentPage(long nowcount) {
		Integer current = Integer.valueOf(getCurrentPage());
		long expectCount = current.intValue() * this.pageSize.intValue();
		if (nowcount == 0L) {
			LOGGER.warn(new StringBuilder().append("allCount :").append(nowcount).append(",自动设置currentPage为0").toString());
			setCurrentPage("0");
		} else if ((current.intValue() != 1) && (expectCount - this.pageSize.intValue() >= nowcount)) {
			int nowPage = (int) (nowcount - 1L) / this.pageSize.intValue() + 1;
			LOGGER.warn(new StringBuilder().append("currentPage :").append(this.currentPage).append(",allCount:").append("allCount, currentPage与逻辑不符合,自动设置为:").append(nowPage).toString());
			setCurrentPage(String.valueOf(nowPage));
		}
	}

	public int getFirstResult() {
		Integer current = Integer.valueOf(getCurrentPage());
		int firstResult = (current.intValue() - 1) * this.pageSize.intValue();
		return firstResult < 0 ? 0 : firstResult;
	}

	public Long getMaxPage() {
		Long temp = Long.valueOf(getAllCount().longValue() / this.pageSize.intValue());
		return Long.valueOf(getAllCount().longValue() % this.pageSize.intValue() == 0L ? temp.longValue() : temp.longValue() + 1L);
	}

	public boolean getHasNextPage() {
		Integer current = Integer.valueOf(getCurrentPage());
		return current.intValue() < getMaxPage().longValue();
	}

	public boolean getHasPreviousPage() {
		Integer current = Integer.valueOf(getCurrentPage());
		return current.intValue() > 1;
	}

	public List<QueryStrategy> getStrategies() {
		return this.strategies;
	}

	public void setStrategies(List<QueryStrategy> strategies) {
		this.strategies = strategies;
	}

	public List<OrderStrategy> getOrderStrategies() {
		return this.orderStrategies;
	}

	public void setOrderStrategies(List<OrderStrategy> orderStrategies) {
		this.orderStrategies = orderStrategies;
	}

	public void setPageBar(String pageBar) {
		this.pageBar = pageBar;
	}

	public String getPageBar() {
		Integer current = Integer.valueOf(this.currentPage);
		String frmId = "frm";
		StringBuilder builder = new StringBuilder();
		if (this.allCount != 0L) {
			builder.append("<script>");
			builder.append("firstPage = function() {");
			builder.append("document.getElementById(\"currentPage\").value = 1;");
			builder.append(new StringBuilder().append("document.getElementById(\"").append(frmId).append("\").submit();").toString());
			builder.append("};");
			builder.append("endPage = function() {");
			builder.append(new StringBuilder().append("document.getElementById(\"currentPage\").value = ").append(getMaxPage()).append(";").toString());
			builder.append(new StringBuilder().append("document.getElementById(\"").append(frmId).append("\").submit();").toString());
			builder.append("};");
			builder.append("nextPage = function() {");
			builder.append(new StringBuilder().append("document.getElementById(\"currentPage\").value = ").append(current.intValue() + 1).append(";").toString());
			builder.append(new StringBuilder().append("document.getElementById(\"").append(frmId).append("\").submit();").toString());
			builder.append("};");
			builder.append("prevPage = function() {");
			builder.append(new StringBuilder().append("document.getElementById(\"currentPage\").value = ").append(current.intValue() - 1).append(";").toString());
			builder.append(new StringBuilder().append("document.getElementById(\"").append(frmId).append("\").submit();").toString());
			builder.append("};");
			builder.append("pageJump = function(pageValue,jumpType) { ");
			builder.append("if(jumpType == 1){pageValue = document.getElementById(\"pageNum\").value;}");
			builder.append("if(pageValue) {");
			builder.append("var reg = /^\\d+$/;");
			builder.append("if(!reg.test(pageValue) || pageValue==0) {");
			builder.append("return;");
			builder.append("}");
			builder.append(new StringBuilder().append("if(pageValue > ").append(getMaxPage()).append(" ) {").toString());
			builder.append("return;");
			builder.append("}");
			builder.append("document.getElementById(\"currentPage\").value = pageValue;");
			builder.append("document.getElementById(\"frm\").submit();");
			builder.append("}else{");
			builder.append("return;");
			builder.append("}");
			builder.append("};");
			builder.append("</script>");
			builder.append("<div class=\"page\"><div class=\"page-in\">");
			if (getMaxPage().longValue() == 1L) {
				builder.append("<a href=\"javascript:void(0);\" class=\"first-page\"></a>");
				builder.append("<a href=\"javascript:void(0);\" class=\"prev-page\"></a>");
				builder.append(buildPageJump(current));
				builder.append("<a href=\"javascript:void(0);\" class=\"next-page\"></a>");
				builder.append("<a href=\"javascript:void(0);\" class=\"last-page\"></a>");
				builder.append("</div>");
			} else if ((current.intValue() > 1) && (Long.valueOf(current.intValue()).equals(getMaxPage()))) {
				builder.append("<a href=\"javascript:void(0);\" onclick=\"firstPage();\" class=\"first-page-h\"></a>");
				builder.append("<a href=\"javascript:void(0);\" onclick=\"prevPage();\" class=\"prev-page-h\"></a>");
				builder.append(buildPageJump(current));
				builder.append("<a href=\"javascript:void(0);\" class=\"next-page\"></a>");
				builder.append("<a href=\"javascript:void(0);\" class=\"last-page\"></a>");
				builder.append("</div>");
			} else if ((current.intValue() > 1) && (current.intValue() < getMaxPage().longValue())) {
				builder.append("<a href=\"javascript:void(0);\" onclick=\"firstPage();\" class=\"first-page-h\"></a>");
				builder.append("<a href=\"javascript:void(0);\" onclick=\"prevPage();\" class=\"prev-page-h\"></a>");
				builder.append(buildPageJump(current));
				builder.append("<a href=\"javascript:void(0);\" onclick=\"nextPage();\" class=\"next-page-h\"></a>");
				builder.append("<a href=\"javascript:void(0);\" onclick=\"endPage();\" class=\"last-page-h\"></a>");
				builder.append("</div>");
			} else if ((getMaxPage().longValue() > 1L) && (current.intValue() == 1)) {
				builder.append("<a href=\"javascript:void(0);\" class=\"first-page\"></a>");
				builder.append("<a href=\"javascript:void(0);\" class=\"prev-page\"></a>");
				builder.append(buildPageJump(current));
				builder.append("<a href=\"javascript:void(0);\" onclick=\"nextPage();\" class=\"next-page-h\"></a>");
				builder.append("<a href=\"javascript:void(0);\" onclick=\"endPage();\" class=\"last-page-h\"></a>");
				builder.append("</div>");
			}
			builder.append("</div>");
		} else {
			builder.append("<div style='color:red;text-align:center;'>没有查到数据记录</div>");
		}
		this.pageBar = builder.toString();
		return this.pageBar;
	}

	public String buildPageJump(Integer current) {
		return new StringBuilder().append("<span>第" + current + "页，共" + this.getMaxPage() + "页</span>").toString();
		
	}

//	public String buildPageJumpBlock(Integer currentPageNum) {
//		Integer preNum = Integer.valueOf(currentPageNum.intValue() - 1);
//		Integer nextNum = Integer.valueOf(currentPageNum.intValue() + 1);
//		Integer preTwoNum = Integer.valueOf(currentPageNum.intValue() - 2);
//		Integer nextTwoNum = Integer.valueOf(currentPageNum.intValue() + 2);
//		StringBuilder temp = new StringBuilder();
//		
//		if ((nextNum.intValue() > getMaxPage().longValue()) && (preTwoNum.intValue() - 2 > 0)) {
//			temp.append(buildPageJump(Integer.valueOf(preTwoNum.intValue() - 2), false));
//		}
//		
//		if ((nextTwoNum.intValue() > getMaxPage().longValue()) && (preNum.intValue() - 2 > 0)) {
//			temp.append(buildPageJump(Integer.valueOf(preNum.intValue() - 2), false));
//		}
//		
//		if (preTwoNum.intValue() > 0) {
//			temp.append(buildPageJump(preTwoNum, false));
//		}
//		
//		if (preNum.intValue() > 0) {
//			temp.append(buildPageJump(preNum, false));
//		}
//		
//		temp.append(buildPageJump(currentPageNum, true));
//		
//		if (nextNum.intValue() <= getMaxPage().longValue()) {
//			temp.append(buildPageJump(nextNum, false));
//		}
//		
//		if (nextTwoNum.intValue() <= getMaxPage().longValue()) {
//			temp.append(buildPageJump(nextTwoNum, false));
//		}
//		
//		if ((preTwoNum.intValue() <= 0) && (nextNum.intValue() + 2 <= getMaxPage().longValue())) {
//			temp.append(buildPageJump(Integer.valueOf(nextNum.intValue() + 2), false));
//		}
//		
//		if ((preNum.intValue() <= 0) && (nextTwoNum.intValue() + 2 <= getMaxPage().longValue())) {
//			temp.append(buildPageJump(Integer.valueOf(nextTwoNum.intValue() + 2), false));
//		}
//
//		return temp.toString();
//	}
	
}