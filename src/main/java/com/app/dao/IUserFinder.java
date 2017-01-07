package com.app.dao;

import java.util.List;

public interface IUserFinder<T> extends IFinder<T> {
	
	public List<T> findByUserId(Long id);
}
