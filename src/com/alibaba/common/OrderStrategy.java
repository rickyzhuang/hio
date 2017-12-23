package com.alibaba.common;

import java.io.Serializable;

public class OrderStrategy implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	private String fieldKey = null;

	private String fieldSQLKey = null;

	private String fieldValue = "asc";

	public OrderStrategy() {
	}

	public String getFieldSQLKey() {
		return this.fieldSQLKey;
	}

	public void setFieldSQLKey(String fieldSQLKey) {
		this.fieldSQLKey = fieldSQLKey;
	}

	public OrderStrategy(OrderStrategy queryStrategy) {
		this.fieldKey = queryStrategy.fieldKey;
		this.fieldValue = queryStrategy.fieldValue;
	}

	public String getFieldKey() {
		return this.fieldKey;
	}

	public void setFieldKey(String fieldKey) {
		this.fieldKey = fieldKey;
	}

	public String getFieldValue() {
		return this.fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public int hashCode() {
		int result = 1;
		result = 31 * result + (this.fieldKey == null ? 0 : this.fieldKey.hashCode());

		result = 31 * result + (this.fieldSQLKey == null ? 0 : this.fieldSQLKey.hashCode());

		result = 31 * result + (this.fieldValue == null ? 0 : this.fieldValue.hashCode());

		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderStrategy other = (OrderStrategy) obj;
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
		return true;
	}

	public String toString() {
		return "OrderStrategy [fieldKey=" + this.fieldKey + ", fieldSQLKey=" + this.fieldSQLKey + ", fieldValue=" + this.fieldValue + "]";
	}
}