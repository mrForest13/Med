package com.app.model.visit.finder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.dao.IVisitTypeFinder;
import com.app.model.visit.VisitType;

public class VisitTypeFinder implements IVisitTypeFinder {

	private final String getAll = "Select VISIT_TYPE from VISIT_TYPES";
	
	@Override
	public List<VisitType> getAll() {

		Connection con = null;
		List<VisitType> result = new ArrayList<VisitType>();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(getAll);

			while (rs.next())
				result.add(new VisitType(rs.getString(1)));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
}
