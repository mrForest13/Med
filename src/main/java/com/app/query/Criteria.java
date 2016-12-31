package com.app.query;

public class Criteria {

	private String sqlOperator;
	private String field;
	private Object value;

	public String getSqlOperator() {
		return sqlOperator;
	}

	public void setSqlOperator(String sqlOperator) {
		this.sqlOperator = sqlOperator;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Criteria(String sqlOperator, String field, Object value) {
		super();
		this.sqlOperator = sqlOperator;
		this.field = field;
		this.value = value;
	}

	public static Criteria greaterThn(String fieldName, int value) {
		return new Criteria(" > ", fieldName, new Integer(value));
	}
	
	public static Criteria lessThn(String fieldName, int value) {
		return new Criteria(" < ", fieldName, new Integer(value));
	}
	
	public static Criteria isNull(String fieldName) {
		return new Criteria(" is ", fieldName, "NULL");
	}
	
	public static Criteria notNull(String fieldName) {
		return new Criteria(" is not ", fieldName, "NULL");
	}
	
	@Override
	public String toString() {
		return field + sqlOperator + value.toString();
	}

}
