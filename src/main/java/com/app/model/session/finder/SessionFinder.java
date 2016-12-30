package com.app.model.session.finder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.dao.IFinder;
import com.app.db.ConnectionOracle;
import com.app.med.HomeController;
import com.app.model.session.Session;
import com.app.model.user.User;
import com.app.model.user.finder.UserFinder;
import com.app.model.visit.VisitType;
import com.app.registry.Registry;

public class SessionFinder implements IFinder<Session> {

	private static final Logger logger = LoggerFactory.getLogger(SessionFinder.class);

	public static final String getByUserId = "Select * from UZYTKOWNIK_SESSION where session_uzytkownik_uz_id = ?";
	public static final String getAll = "Select * from UZYTKOWNIK_SESSION";

	@Override
	public Session findById(Long userId) {

		Connection con = null;
		PreparedStatement getStatement = null;
		Session result = null;
		ResultSet rs = null;

		try {
			con = ConnectionOracle.getInstance();
			getStatement = con.prepareStatement(getByUserId);
			getStatement.setLong(1, userId);

			logger.info("Select * from UZYTKOWNIK_SESSION where session_uzytkownik_uz_id = " + userId);

			rs = getStatement.executeQuery();

			if (rs.next())
				result = load(rs);

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

	@Override
	public List<Session> getAll() {

		Connection con = null;
		List<Session> result = new ArrayList<Session>();
		Statement st = null;
		ResultSet rs = null;

		try {
			con = ConnectionOracle.getInstance();
			st = con.createStatement();
			rs = st.executeQuery(getAll);

			while (rs.next())
				result.add(load(rs));

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

	private Session load(ResultSet rs) throws SQLException {

		User user = Registry.userFinder().abstractFind(rs.getLong(2), UserFinder.TABLENAME);

		Session result = new Session(rs.getLong(1), user, rs.getString(3), rs.getTimestamp(4),
				rs.getString(5).equals("Y") ? true : false);

		logger.info(result.toString());

		return result;
	}

}
