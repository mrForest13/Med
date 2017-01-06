package com.app.dao;

import java.util.List;

public interface IFinderAll<T> extends IFinder<T> {

	public List<T> getAll();
}
