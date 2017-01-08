package com.app.model.visit.finder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.dao.IFinderAll;
import com.app.db.ConnectionOracle;
import com.app.model.visit.VisitType;

public class VisitTypeFinder implements IFinderAll<VisitType> {

	private static final Logger logger = LoggerFactory.getLogger(VisitTypeFinder.class);

	private static final String byId = "Select * from VISIT_TYPES where visit_type_id = ?";
	private final String getAll = "Select * from VISIT_TYPES";

	@Override
	public List<VisitType> getAll() {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<VisitType> result = new ArrayList<VisitType>();

		try {
			con = ConnectionOracle.getInstance();
			st = con.createStatement();
			rs = st.executeQuery(getAll);

			while (rs.next())
				result.add(new VisitType(rs.getLong(1), rs.getString(2), rs.getString(3).equals("Y") ? true : false));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public VisitType findById(Long id) {

		Connection con = null;
		VisitType result = null;
		PreparedStatement findStatement = null;
		ResultSet rs = null;

		try {
			con = ConnectionOracle.getInstance();
			findStatement = con.prepareStatement(byId);
			findStatement.setLong(1, id);
			rs = findStatement.executeQuery();

			if (rs.next()) {
				result = new VisitType(rs.getLong(1), rs.getString(2), rs.getString(3).equals("Y") ? true : false);
				logger.info(result.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				findStatement.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

}
