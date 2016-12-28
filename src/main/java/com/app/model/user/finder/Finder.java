package com.app.model.user.finder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.med.HomeController;
import com.app.model.user.User;

public abstract class Finder {

	private static final Logger logger = LoggerFactory.getLogger(Finder.class);

	public User abstractFind(Long id, String tablename) {
		boolean row = findRow(id, tablename, e -> {});

		if (!row)
			return null;
		else {
			User resultObject = getUserObject();
			resultObject.setId(id);
			load(resultObject);
			
			logger.info(resultObject.toString());
			
			return resultObject;
		}
	}

	protected abstract void load(User user);

	protected abstract User getUserObject();

	protected boolean findRow(Long id, String tableNeame, Load load) {

		Connection con = null;
		ResultSet rs = null;

		String filter = tableNeame + "_ID = " + id;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "root", "root");
			Statement st = con.createStatement();

			logger.info("Select * from " + tableNeame + " WHERE " + filter);

			rs = st.executeQuery("Select * from " + tableNeame + " WHERE " + filter);

			if (!rs.next())
				return false;
			else
				load.load(rs);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return true;
		
	}

}
