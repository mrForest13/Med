package com.app.query;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import oracle.net.aso.f;

public abstract class Criteria {

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
	
	protected abstract void setObject(PreparedStatement preparedStatement, int index) throws SQLException;

	public static Criteria greaterThan(String fieldName, Timestamp timestamp) {
		return new TimestampCriteria(" > ", fieldName, timestamp);
	}
	
	public static Criteria lessThan(String fieldName, Timestamp timestamp) {
		return new TimestampCriteria(" < ", fieldName, timestamp);
	}
	
	public static Criteria equalsLong(String fieldName, Long value) {
		return new LongCriteria(" = ", fieldName, value);
	}
	
	public static Criteria equalsString(String fieldName, String value) {
		return new StringCriteria(" = ", fieldName, value);
	}
	
	public static Criteria equalsDate(String fieldName, Date timestamp) {
		return new DateCriteria(" = ", fieldName, timestamp);
	}
	
	@Override
	public String toString() {
		return field + sqlOperator + "?";
	}

}
