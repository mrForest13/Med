package com.app.model.user.finder;

public interface IUserFinder<T> {
	
	public T find(Long id);
}
