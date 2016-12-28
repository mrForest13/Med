package com.app.model.user.finder;

public interface UserFinder<T> {
	
	public T find(Long id);
}
