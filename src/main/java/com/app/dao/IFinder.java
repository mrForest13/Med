package com.app.dao;

import java.util.List;

public interface IFinder<T> {

	public List<T> getAll();
	public T findById(Long id);
	
}
