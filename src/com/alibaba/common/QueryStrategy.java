package com.alibaba.common;

import java.io.Serializable;

public class QueryStrategy implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	public static final String OPERATE_GREATER = ">";
	public static final String OPERATE_LESS = "<";
	public static final String OPERATE_GREATE_EQUAL = ">=";
	public static final String OPERATE_LESS_EQUAL = "<=";
	public static final String OPERATE_EQUAL = "=";
	public static final String OPERATE_UNEQUAL = "<>";
	public static final String OPERATE_LIKE = "like";
	public static final String OPERATE_IN = "in";
	public static final String OPERATE_NOT_IN = "not in";
	public static final String YES = "0";
	public static final String NO = "1";
	public static final String CHECK_YES_VALUE = "on";
	private String fieldKey = null;

	private String paramterKey = null;

	private String fieldSQLKey = null;

	private String fieldValue = null;

	private boolean useLike = false;

	private boolean typeIsTime = false;

	private boolean typeIsBoolean = false;

	private String operateStr = "=";

	private boolean useSection = false;

	private boolean ignoreCase = false;

	private boolean trim = true;

	public String getFieldKey() {
		return this.fieldKey;
	}

	public void setFieldKey(String fieldKey) {
		this.fieldKey = fieldKey;
		if ((this.fieldSQLKey != null) && (!this.fieldSQLKey.trim().equals(""))) {
			this.paramterKey = this.fieldSQLKey.replace(".", "_").replaceAll("-", "_");
		} else if ((this.fieldKey != null) && (!this.fieldKey.trim().equals("")))
			this.paramterKey = this.fieldKey.replace(".", "_").replaceAll("-", "_");
	}

	public String getFieldSQLKey() {
		return this.fieldSQLKey;
	}

	public void setFieldSQLKey(String fieldSQLKey) {
		this.fieldSQLKey = fieldSQLKey;
	}

	public String getFieldValue() {
		return this.fieldValue;
	}

	public String getParamterKey() {
		return this.paramterKey;
	}

	public void setParamterKey(String paramterKey) {
		this.paramterKey = paramterKey;
	}

	public void setFieldValue(String fieldValue) {
		if ((fieldValue != null) && (!fieldValue.equals("")) && (this.trim))
			fieldValue = fieldValue.trim();
		this.fieldValue = fieldValue;
	}

	public boolean getUseLike() {
		return this.useLike;
	}

	public void setUseLike(boolean useLike) {
		this.useLike = useLike;
	}

	public boolean getTypeIsTime() {
		return this.typeIsTime;
	}

	public void setTypeIsTime(boolean typeIsTime) {
		this.typeIsTime = typeIsTime;
	}

	public boolean getTypeIsBoolean() {
		return this.typeIsBoolean;
	}

	public void setTypeIsBoolean(boolean typeIsBoolean) {
		this.typeIsBoolean = typeIsBoolean;
	}

	public String getOperateStr() {
		return this.operateStr;
	}

	public void setOperateStr(String operateStr) {
		this.operateStr = operateStr;
	}

	public boolean getUseSection() {
		return this.useSection;
	}

	public void setUseSection(boolean useSection) {
		this.useSection = useSection;
	}

	public boolean getIgnoreCase() {
		return this.ignoreCase;
	}

	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	public boolean getTrim() {
		return this.trim;
	}

	public void setTrim(boolean trim) {
		this.trim = trim;
	}

	public int hashCode() {
		int result = 1;
		result = 31 * result + (this.fieldKey == null ? 0 : this.fieldKey.hashCode());

		result = 31 * result + (this.fieldSQLKey == null ? 0 : this.fieldSQLKey.hashCode());

		result = 31 * result + (this.fieldValue == null ? 0 : this.fieldValue.hashCode());

		result = 31 * result + (this.ignoreCase ? 1231 : 1237);
		result = 31 * result + (this.operateStr == null ? 0 : this.operateStr.hashCode());

		result = 31 * result + (this.paramterKey == null ? 0 : this.paramterKey.hashCode());

		result = 31 * result + (this.trim ? 1231 : 1237);
		result = 31 * result + (this.typeIsBoolean ? 1231 : 1237);
		result = 31 * result + (this.typeIsTime ? 1231 : 1237);
		result = 31 * result + (this.useLike ? 1231 : 1237);
		result = 31 * result + (this.useSection ? 1231 : 1237);
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QueryStrategy other = (QueryStrategy) obj;
		if (this.fieldKey == null) {
			if (other.fieldKey != null)
				return false;
		} else if (!this.fieldKey.equals(other.fieldKey))
			return false;
		if (this.fieldSQLKey == null) {
			if (other.fieldSQLKey != null)
				return false;
		} else if (!this.fieldSQLKey.equals(other.fieldSQLKey))
			return false;
		if (this.fieldValue == null) {
			if (other.fieldValue != null)
				return false;
		} else if (!this.fieldValue.equals(other.fieldValue))
			return false;
		if (this.ignoreCase != other.ignoreCase)
			return false;
		if (this.operateStr == null) {
			if (other.operateStr != null)
				return false;
		} else if (!this.operateStr.equals(other.operateStr))
			return false;
		if (this.paramterKey == null) {
			if (other.paramterKey != null)
				return false;
		} else if (!this.paramterKey.equals(other.paramterKey))
			return false;
		if (this.trim != other.trim)
			return false;
		if (this.typeIsBoolean != other.typeIsBoolean)
			return false;
		if (this.typeIsTime != other.typeIsTime)
			return false;
		if (this.useLike != other.useLike) {
			return false;
		}
		return this.useSection == other.useSection;
	}

	public String toString() {
		return "QueryStrategy [fieldKey=" + this.fieldKey + ", paramterKey=" + this.paramterKey + ", fieldSQLKey=" + this.fieldSQLKey + ", fieldValue=" + this.fieldValue + ", useLike=" + this.useLike + ", typeIsTime=" + this.typeIsTime + ", typeIsBoolean=" + this.typeIsBoolean + ", operateStr=" + this.operateStr + ", useSection=" + this.useSection + ", ignoreCase=" + this.ignoreCase + ", trim=" + this.trim + "]";
	}
}