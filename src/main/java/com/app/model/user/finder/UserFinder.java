package com.app.model.user.finder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.db.ConnectionOracle;
import com.app.model.user.User;

public class UserFinder extends Finder {

	private static final Logger logger = LoggerFactory.getLogger(UserFinder.class);

	public static final String TABLENAME = "Uzytkownik";
	public static final int TYPE = 0;
	public static final String getByLogin = "Select * from Uzytkownik where uzytkownik_login = ?";

	public User findByLogin(String login) {

		Connection con = null;
		PreparedStatement getStatement = null;
		User result = null;
		ResultSet rs = null;

		try {
			con = ConnectionOracle.getInstance();
			getStatement = con.prepareStatement(getByLogin);
			getStatement.setString(1, login);
			logger.info("Select * from Uzytkownik where uzytkownik_login = " + login);
			rs = getStatement.executeQuery();

			if (rs.next())
				result = new User(rs.getLong(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				getStatement.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	protected void load(User user) {

		Load e = new Load() {

			@Override
			public void load(ResultSet rs) {
				try {
					user.setId(rs.getLong(1));
					user.setUserType(rs.getInt(2));
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

	@Override
	protected User getUserObject() {
		return new User();
	}

}
