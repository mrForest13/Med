package com.app.query;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TimestampCriteria extends Criteria {

	public TimestampCriteria(String sqlOperator, String field, Timestamp value) {
		super(sqlOperator, field, value);
	}

	@Override
	protected void setObject(PreparedStatement preparedStatement, int index) throws SQLException {
		preparedStatement.setTimestamp(index, (Timestamp) getValue());
	}

}
