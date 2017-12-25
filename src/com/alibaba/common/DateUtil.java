package com.alibaba.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 */
public class DateUtil {

	private static Calendar calendar = Calendar.getInstance();

	/**
	 * 截取到短日期
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date trunc(Date date) throws Exception {
		return DateUtil.toDate(DateUtil.toStringFormat(date, "yyyy-MM-dd"), "yyyy-MM-dd");
	}

	/**
	 * 将数据库中获取到 字符串转换成 日期格式转换成格式字符串(yyyy-MM-dd HH:mm:ss) 约定格式 必须是 yyyy-MM-dd 短日期
	 * 或者长日期格式
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String strDateByStr(String date) throws Exception {
		// 约定格式 必须是 yyyy-MM-dd 短日期 或者长日期格式
		try {
			if (StringUtil.isEmpty(date) || date.length() < 3) {
				return date;
			}
			if (date.length() < 19) {
				int a = date.trim().length();
				switch (a) {
				case 10:
					date += " 00:00:00";
					break;
				case 13:
					date += ":00:00";
					break;
				case 16:
					date += ":00";
					break;
				default:
					date += " :00:00:00";
					break;
				}
			}
			// return date;

			Date newDate = toDate(date, "yyyy-MM-dd HH:mm:ss");
			return toLongStringFormat(newDate);

		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	/**
	 * 日期格式转换成格式字符串(yyyy-MM-dd)
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String toShortStringFormat(Date date) throws Exception {
		return DateUtil.toStringFormat(date, "yyyy-MM-dd");
	}

	/**
	 * 日期格式转换成格式字符串(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String toLongStringFormat(Date date) throws Exception {
		return DateUtil.toStringFormat(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 日期格式转换成指定格式字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String toStringFormat(Date date, String pattern) throws Exception {
		try {
			DateFormat dateFormat = new SimpleDateFormat(pattern);
			return dateFormat.format(date);
		} catch (Exception e) {
			throw new Exception("日期转换成文本格式失败！");
		}
	}

	/**
	 * 文本转换成日期格式
	 * 
	 * @param str
	 * @param pattern
	 * @return
	 * @throws Exception
	 */
	public static Date toDate(String str, String pattern) throws Exception {
		try {
			DateFormat dateFormat = new SimpleDateFormat(pattern);
			return dateFormat.parse(str);
		} catch (Exception e) {
			throw new Exception("日期转换格式失败！");
		}
	}

	/**
	 * 计算日期间隔(日)
	 * 
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static long betweenDays(Date start, Date end) throws Exception {
		return DateUtil.between(start, end, DateType.Days);
	}

	/**
	 * 计算日期间隔
	 * 
	 * @param start
	 * @param end
	 * @param dateType
	 * @return
	 * @throws Exception
	 */
	public static long between(Date start, Date end, DateType dateType) throws Exception {
		long dividend = 1;
		switch (dateType) {
		case Days:
			dividend = 1000 * 60 * 60 * 24;
			break;
		case Hours:
			dividend = 1000 * 60 * 60;
			break;
		case Minutes:
			dividend = 1000 * 60;
			break;
		case Seconds:
			dividend = 1000;
			break;
		case Milliseconds:
			dividend = 1;
			break;
		default:
			throw new Exception("无效的日期类型枚举值！");
		}
		return (end.getTime() - start.getTime()) / dividend;
	}

	/**
	 * 日期加减方法(年)
	 * 
	 * @param date
	 * @param years
	 * @return
	 * @throws Exception
	 */
	public static Date addYears(Date date, int years) throws Exception {
		return add(date, years, Calendar.YEAR);
	}

	/**
	 * 日期加减方法(月)
	 * 
	 * @param date
	 * @param months
	 * @return
	 * @throws Exception
	 */
	public static Date addMonths(Date date, int months) throws Exception {
		return add(date, months, Calendar.MONTH);
	}

	/**
	 * 日期加减方法(日)
	 * 
	 * @param date
	 * @param days
	 * @return
	 * @throws Exception
	 */
	public static Date addDays(Date date, int days) throws Exception {
		return add(date, days, Calendar.DAY_OF_YEAR);
	}

	/**
	 * 日期加减方法
	 * 
	 * @param date
	 * @param adds
	 * @param field
	 * @return
	 * @throws Exception
	 */
	public static Date add(Date date, int adds, int field) throws Exception {
		calendar.setTime(date);
		try {
			calendar.add(field, adds);
		} catch (Exception e) {
			throw new Exception("无效的日期加减类型！");
		}

		return calendar.getTime();
	}

	/**
	 * 把日期的时分秒转化成00:00:00
	 * 
	 * @author linwang
	 * @createTime 2014-1-10
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date setTimeFormatToZero(Date date) throws Exception {
		try {
			date.setHours(0);
			date.setMinutes(0);
			date.setSeconds(0);
		} catch (Exception e) {
			throw new Exception("日期转换失败！");
		}
		return date;
	}

	/**
	 * @createTime 2013-12-19
	 * @user jiangsz
	 * @param start
	 * @param end
	 * @return -1:start &lt; end; 0:start==end;1:start &gt; end
	 * @throws Exception
	 */
	public static int dateVerfiy(String start, String end) throws Exception {
		if (StringUtil.isEmpty(start) || StringUtil.isEmpty(end))
			return -2;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(toDate(start, "yyyy-MM-dd hh:mm:ss"));
		c2.setTime(toDate(end, "yyyy-MM-dd hh:mm:ss"));
		int i = c1.compareTo(c2);
		return i;
	}

	public static boolean isDate(String date) throws Exception {
		int length = date.length();

		try {
			DateUtil.toDate(date, "yyyy-mm-dd");
			return true;
		} catch (Exception e) {
			try {
				DateUtil.toDate(date, "yyyy/mm/dd");
				return true;
			} catch (Exception e2) {
				return false;
			}
		}

	}

	public static void main(String[] args) {
		System.out.println("1990-02-02".length());
		System.out.println("1990-2-2".indexOf("-"));
	}

	/**
	 * 日期类型枚举
	 * 
	 * @author Sugar
	 * 
	 */
	public enum DateType {
		/**
		 * 日
		 */
		Days,

		/**
		 * 小时
		 */
		Hours,

		/**
		 * 分钟
		 */
		Minutes,

		/**
		 * 秒
		 */
		Seconds,

		/**
		 * 毫秒
		 */
		Milliseconds
	}

	/**
	 * java.sql.Timestamp类型数据转换成String
	 * 
	 * @author kongxd
	 */

	public static String timestampToString(Object time) {
		String tsStr = "";
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (time != null || time != "") {
				tsStr = sdf.format(sdf.parse(time.toString()));
				return tsStr;
			} else {
				return "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 
	 *  函数功能说明:将date 时间类型 转换成Timestamp 类型的时间  @author:KongXD
	 *  @createTime:2015年4月1日上午10:57:17
	 * 
	 * @param date
	 * @return     Timestamp   @throws
	 */
	public static Timestamp DateTotimestamp(Date date) {

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (date != null) {
				Timestamp timestamp = Timestamp.valueOf(sdf.format(date));

				return timestamp;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Timestamp StrTotimestamp(String str){
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (str != null) {
				Date d=sdf.parse(str);
				return 	DateTotimestamp(d);
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
