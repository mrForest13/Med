package com.app.query;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StringCriteria extends Criteria {

	public StringCriteria(String sqlOperator, String field, String value) {
		super(sqlOperator, field, value);
	}

	@Override
	protected void setObject(PreparedStatement preparedStatement, int index) throws SQLException {
		preparedStatement.setString(index, (String) getValue());
	}

}
