package com.app.model.user.finder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.model.user.User;

public abstract class AbstractUserFinder extends Finder {

	public static String TABLENAME = "Uzytkownik";
	
	public abstract String getType();
	
	protected void load(User user) {
		
		Load e = new Load() {
			
			@Override
			public void load(ResultSet rs) {
				try {
					user.setId(rs.getLong(1));
					user.setFirstName(rs.getString(3));
					user.setLastName(rs.getString(4));
					user.setLogin(rs.getString(5));
					user.setPassword(rs.getString(6));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		};
			
		findRow(user.getId(), TABLENAME, e);
	}
}
