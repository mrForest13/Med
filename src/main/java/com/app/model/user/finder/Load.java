package com.app.model.user.finder;

import java.sql.ResultSet;

@FunctionalInterface
public interface Load {

	public void load(ResultSet resultSet);
	
}
