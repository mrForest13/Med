package com.app.query;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryObject {

	private String table;
	private List<Criteria> criteria = new ArrayList<Criteria>();

	public QueryObject(String table) {
		this.table = table;
	}
	
	public void addCriteria(Criteria criteria) {
		this.criteria.add(criteria);
	}

	public String generateWhereSelectStatement() {
		
		StringBuffer result = new StringBuffer("Select * from "+table+" where ");

		for (int i = 0; i < criteria.size(); i++) {

			if (i != criteria.size() - 1) {
				result = result.append(criteria.get(i)).append(" AND ");
			} else {
				result = result.append(criteria.get(i));
			}
		}
		
		return result.toString();
	}
	
	public void setQuery(PreparedStatement preparedStatement) throws SQLException {
		for (int i = 1; i <= criteria.size(); i++) {
			criteria.get(i-1).setObject(preparedStatement, i);
		}
	}
}
