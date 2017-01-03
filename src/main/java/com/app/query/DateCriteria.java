package com.app.query;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DateCriteria extends Criteria {

	public DateCriteria(String sqlOperator, String field, Date value) {
		super(sqlOperator, field, value);
	}

	@Override
	protected void setObject(PreparedStatement preparedStatement, int index) throws SQLException {
		preparedStatement.setDate(index, (Date) getValue());	
	}

}
