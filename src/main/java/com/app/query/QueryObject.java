package com.app.query;

import java.util.ArrayList;
import java.util.List;

public class QueryObject {

	private List<Criteria> criteria = new ArrayList<Criteria>();

	public void addCriteria(Criteria criteria) {
		this.criteria.add(criteria);
	}

	public String generateWhereClause() {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < criteria.size(); i++) {

			if (i != criteria.size() - 1) {
				result = result.append(criteria.get(i)).append(" AND ");
			} else {
				result = result.append(criteria.get(i));
			}
		}
		
		return result.toString();
	}
}
