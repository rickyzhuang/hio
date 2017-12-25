package com.alibaba.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 */
public class StringUtil {

	private static final char SEPARATOR = '_';
	
	/**
	 * 判断String在trim()后是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if(null==str||"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 判断字符串是否为null或者在trim()后是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		return str == null || isEmpty(str) || "null".equals(str);
	}

	/**
	 * list转数组
	 * 
	 * @param list
	 * @return
	 */
	public static String[] listToStrArray(List list) {
		if (list == null || list.size() == 0)
			return null;

		Iterator iterator = list.iterator();
		String[] strArray = new String[list.size()];
		int i = 0;

		while (iterator.hasNext()) {
			strArray[i] = String.valueOf(iterator.next());
			i++;
		}
		return strArray;
	}

	//----------------------------------字符串与List、Map等的相互转换工具--------------------------------------------------------
	/**
	 * 定义分割常量 （#在集合中的含义是每个元素的分割，|主要用于map类型的集合用于key与value中的分割）
	 */
	private static final String SEP1 = "#";
	private static final String SEP2 = "|";

	/**
	 * List转换String
	 * 
	 * @param list
	 *            :需要转换的List
	 * @return String转换后的字符串
	 */
	public static String ListToString(List<?> list) {
		try {
			
	
		StringBuffer sb = new StringBuffer();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) == null || list.get(i) == "") {
					continue;
				}
				// 如果值是list类型则调用自己
				if (list.get(i) instanceof List) {
					sb.append(ListToString((List<?>) list.get(i)));
					sb.append(SEP1);
				} else if (list.get(i) instanceof Map) {
					sb.append(MapToString((Map<?, ?>) list.get(i)));
					sb.append(SEP1);
				} else {
					sb.append(list.get(i));
					sb.append(SEP1);
				}
			}
		}
		return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Map转换String
	 * 
	 * @param map
	 *            :需要转换的Map
	 * @return String转换后的字符串
	 */
	public static String MapToString(Map<?, ?> map) {
		StringBuffer sb = new StringBuffer();
		// 遍历map
		for (Object obj : map.keySet()) {
			if (obj == null) {
				continue;
			}
			Object key = obj;
			Object value = map.get(key);
			if (value instanceof List<?>) {
				sb.append(key.toString() + SEP1 + ListToString((List<?>) value));
				sb.append(SEP2);
			} else if (value instanceof Map<?, ?>) {
				sb.append(key.toString() + SEP1
						+ MapToString((Map<?, ?>) value));
				sb.append(SEP2);
			} else {
				try {
					sb.append(((key==null)?"":key.toString()) + SEP1 + ((value==null)?"null":value.toString()));
				} catch (Exception e) {
					System.out.println("value: "+(value==null)+"    ----   "+value);
					e.printStackTrace();
				}
				
				sb.append(SEP2);
			}
		}
		return sb.toString();
	}

	/**
	 * String转换Map
	 * 
	 * @param mapText
	 *            :需要转换的字符串
	 * @param KeySeparator
	 *            :字符串中的分隔符每一个key与value中的分割
	 * @param ElementSeparator
	 *            :字符串中每个元素的分割
	 * @return Map<?,?>
	 */
	public static Map<String, Object> StringToMap(String mapText) {

		if (mapText == null || mapText.equals("")) {
			return null;
		}
		mapText = mapText.substring(1);

		Map<String, Object> map = new HashMap<String, Object>();
		String[] text = mapText.split("\\" + SEP2); // 转换为数组
		for (String str : text) {
			String[] keyText = str.split(SEP1); // 转换key与value的数组
			if (keyText.length < 1) {
				continue;
			}
			String key = keyText[0]; // key
			String value = keyText[1]; // value
			if (value.charAt(0) == 'M') {
				Map<?, ?> map1 = StringToMap(value);
				map.put(key, map1);
			} else if (value.charAt(0) == 'L') {
				List<?> list = StringToList(value);
				map.put(key, list);
			} else {
				map.put(key, value);
			}
		}
		return map;
	}

	/**
	 * String转换List
	 * 
	 * @param listText
	 *            :需要转换的文本
	 * @return List<?>
	 */
	public static List<Object> StringToList(String listText) {
		if (listText == null || listText.equals("")) {
			return null;
		}
		listText = listText.substring(1);

		List<Object> list = new ArrayList<Object>();
		String[] text = listText.split(SEP1);
		for (String str : text) {
			if (str.charAt(0) == 'M') {
				Map<?, ?> map = StringToMap(str);
				list.add(map);
			} else if (str.charAt(0) == 'L') {
				List<?> lists = StringToList(str);
				list.add(lists);
			} else {
				list.add(str);
			}
		}
		return list;
	}
//------------------------------------------------------------------------------------------

	/**
	 * 验证[某字符串]是否存在于逗号分隔的字符串中
	 * 
	 * @param str
	 *            【abc,123,www】
	 * @param substr
	 *            【123】
	 * @param sepatator
	 *            【,】
	 * @return
	 */
	public static boolean isExist(String str, String substr, String sepatator) {
		if (str == null || str.trim().equals(""))
			return false;
		if (substr == null || substr.trim().equals(""))
			return false;
		String[] strArr = str.split(sepatator);
		int size = strArr.length;
		for (int i = 0; i < size; i++) {
			if (strArr[i].equals(substr))
				return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否相等
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equals(String str1, String str2) {
		boolean result = false;
		if (isEmpty(str1) && isEmpty(str2)) {
			result = true;
		} else if (!isEmpty(str1) && !isEmpty(str2)) {
			result = str1.equals(str2);
		}

		return result;
	}

	/**
	 * 是否纯英文字母
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEnglish(String str) {
		return isMatchRegex(str, "^[a-z|A-Z]+$");
	}

	/** 
	 * 是否为汉字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isChinese(String str) {
		return isMatchRegex(str, "[\u4E00-\u9FA5]");
	}

	/**
	 * 是否匹配正则表达式
	 * 
	 * @param str
	 * @param regex
	 * @return
	 */
	public static boolean isMatchRegex(String str, String regex) {
		boolean result = false;
		if (!isEmpty(str)) {
			Matcher matcher = Pattern.compile(regex).matcher(str);
			if (matcher.find())
				result = true;
		}

		return result;
	}

	/**
	 * 将数组合并字符串
	 * 
	 * @param strs
	 *            数组
	 * @return
	 */
	public static String join(String[] strs) {
		return join(strs, null);
	}

	/**
	 * 将数组合并字符串
	 * 
	 * @param strs
	 *            数组
	 * @param sign
	 *            间隔符
	 * @return
	 */
	public static String join(String[] strs, String sign) {
		StringBuilder sb = new StringBuilder();
		for (String str : strs) {
			sb.append(str);
			if (sign != null && sign.length() > 0)
				sb.append(sign);
		}

		return sb.toString();
	}

	/**
	 * 
	 * @param source
	 * @param oldStr
	 * @param newStr
	 * @return
	 */
	public static String replace(String source, String oldStr, String newStr) {
		return replace(source, oldStr, newStr, false);
	}

	public static String replace(String source, String oldStr, String newStr,
			boolean matchCase) {
		String result = null;
		if (isEmpty(source))
			return result;
		return null;
	}

	public static boolean isNotNull(Object str) {
		return (null != str && !"".equals(str.toString().trim())) ? true
				: false;
	}


	/**
	 * @createTime 2014-4-9
	 * @user jiangsz
	 * @method 骆驼命名法到下划线
	 * @param s
	 * @return
	 */
	public static String toUnderlineName(String s) {
		if (s == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			boolean nextUpperCase = true;

			if (i < (s.length() - 1)) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}

			if ((i >= 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					if (i > 0)
						sb.append(SEPARATOR);
				}
				upperCase = true;
			} else {
				upperCase = false;
			}

			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	/**
	 * @createTime 2014-4-9
	 * @user jiangsz
	 * @method 数据库命名法到骆驼命名法
	 * @param s
	 * @return
	 */
	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}

		s = s.toLowerCase();

		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	public static String toCapitalizeCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = toCamelCase(s);
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * 将对象属性名转换成帕斯卡风格
	 * @param fieldName 属性名
	 * @return
	 */
	public static String toPascalFieldName(String fieldName) {
		if (StringUtil.isNullOrEmpty(fieldName)) return null;
		StringBuffer sb = new StringBuffer(fieldName);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		return sb.toString();
	}
	

	/**
	 * 判断字符是否是中文
	 *
	 * @param c 字符
	 * @return 是否是中文
	 */
	public static boolean isChinese(char c) {
	    Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
	    if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
	            || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
	            || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
	            || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
	            || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
	            || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
	        return true;
	    }
	    return false;
	}
	 
	/**
	 * 判断字符串是否是乱码
	 *
	 * @param strName 字符串
	 * @return 是否是乱码
	 */
	public static boolean isMessyCode(String strName) {
	    Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
	    Matcher m = p.matcher(strName);
	    String after = m.replaceAll("");
	    String temp = after.replaceAll("\\p{P}", "");
	    char[] ch = temp.trim().toCharArray();
	    float chLength = ch.length;
	    float count = 0;
	    for (int i = 0; i < ch.length; i++) {
	        char c = ch[i];
	        if (!Character.isLetterOrDigit(c)) {
	            if (!isChinese(c)) {
	                count = count + 1;
	            }
	        }
	    }
	    float result = count / chLength;
	    if (result > 0.4) {
	        return true;
	    } else {
	        return false;
	    }
	 
	}
	

	/**
	 * 数据库Clob对象转换为String
	 */
	public static String clob2Str(Clob clob) {
		if (clob == null) {
			return null;
		}
		try {
			Reader inStreamDoc = clob.getCharacterStream();
			char[] tempDoc = new char[(int) clob.length()];
			inStreamDoc.read(tempDoc);
			inStreamDoc.close();
			return new String(tempDoc);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException es) {
			es.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 数据库Blob对象转换为String
	 */
	public  static String blob2Str(Blob blob){  
	   StringBuffer str=new StringBuffer();
	   //使用StringBuffer进行拼接
	   InputStream in=null;
	    try {  
	        in = blob.getBinaryStream();  
	        byte[] buff=new byte[(int) blob.length()];  
	        for(int i=0;(i=in.read(buff))>0;){  
	            str=str.append(new String(buff));  
	        }
	        return str.toString();
        }catch (Exception e) {  
        	e.printStackTrace();  
       } finally{
        	 try{
        	 in.close();
        	 }catch(Exception e){
        		 System.out.println("转换异常");
        		 e.printStackTrace();
        	 }
         } 
	    return null;  
	}
	
	//---------------------字符串相似度匹配------------------------------------------------------------
	private static int compare(String str, String target) {
		int d[][]; // 矩阵
		int n = str.length();
		int m = target.length();
		int i; // 遍历str的
		int j; // 遍历target的
		char ch1; // str的
		char ch2; // target的
		int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
		if (n == 0) {
			return m;
		}
		if (m == 0) {
			return n;
		}
		d = new int[n + 1][m + 1];
		for (i = 0; i <= n; i++) { // 初始化第一列
			d[i][0] = i;
		}

		for (j = 0; j <= m; j++) { // 初始化第一行
			d[0][j] = j;
		}

		for (i = 1; i <= n; i++) { // 遍历str
			ch1 = str.charAt(i - 1);
			// 去匹配target
			for (j = 1; j <= m; j++) {
				ch2 = target.charAt(j - 1);
				if (ch1 == ch2) {
					temp = 0;
				} else {
					temp = 1;
				}

				// 左边+1,上边+1, 左上角+temp取最小
				d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1]
						+ temp);
			}
		}
		return d[n][m];
	}

	private static int min(int one, int two, int three) {
		return (one = one < two ? one : two) < three ? one : three;
	}

	/**
	 * 
	 * 获取两字符串的相似度
	 * 
	 * 
	 * 
	 * @param str
	 * 
	 * @param target
	 * 
	 * @return
	 */

	public static float getSimilarityRatio(String str, String target) {
		return 1 - (float) compare(str, target)
				/ Math.max(str.length(), target.length());
	}
	//---------------------------------------------------------------------------------
	
	public static void main(String[] args) {
		String ss = "<tt><tt/><ss><ss/>";
		System.out.println(ss.replaceAll("/>", "/>\r\n"));
	}
}
