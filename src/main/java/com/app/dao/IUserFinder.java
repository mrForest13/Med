package com.app.dao;

import java.util.List;

public interface IUserFinder<T> {
	
	public List<T> findByUserId(Long id);
}
