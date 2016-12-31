package com.app.dao;

import java.util.List;

import com.app.query.QueryObject;

public interface IQueryFinder<T> extends IFinder<T> {

	public List<T>findByQueryObject(QueryObject query);
	
}
