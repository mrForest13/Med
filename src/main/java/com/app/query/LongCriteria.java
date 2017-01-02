package com.app.query;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LongCriteria extends Criteria {

	public LongCriteria(String sqlOperator, String field, Long value) {
		super(sqlOperator, field, value);
	}

	@Override
	protected void setObject(PreparedStatement preparedStatement, int index) throws SQLException {
		preparedStatement.setLong(index, (Long) getValue());	
	}

}
