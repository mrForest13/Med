package com.app.model.visit.finder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.dao.IFinder;
import com.app.dao.IQueryFinder;
import com.app.db.ConnectionOracle;
import com.app.model.user.Doctor;
import com.app.model.user.Patient;
import com.app.model.visit.Visit;
import com.app.model.visit.VisitType;
import com.app.query.QueryObject;
import com.app.registry.Registry;
import com.app.transaction.Money;

public class VisitFinder implements IQueryFinder<Visit> {

	private static final Logger logger = LoggerFactory.getLogger(VisitFinder.class);

	private static final String findById = "Select * from VISIT where visit_id = ?";
	private static final String getAll = "Select * from VISIT";
	public static final String TABLENAME = "Visit";

	public List<Visit> getAll() {

		Connection con = null;
		PreparedStatement getStatement = null;
		List<Visit> result = new ArrayList<Visit>();
		ResultSet rs = null;

		try {
			con = ConnectionOracle.getInstance();
			getStatement = con.prepareStatement(getAll);
			logger.info(getAll);
			rs = getStatement.executeQuery();

			while (rs.next())
				result.add(load(rs));

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

	private Visit load(ResultSet rs) throws SQLException {

		Long id = rs.getLong(1);
		Long userId = rs.getLong(4);

		Doctor doctor = Registry.doctorFinder().findById(rs.getLong(2));

		Patient patient = userId != null ? Registry.patientFinder().findById(userId) : null;

		VisitType visitType = Registry.visitTypeFinder().findById(rs.getLong(3));

		boolean visistConfirmed = rs.getString(8).equals("N") ? false : true;

		Visit visit = new Visit(id, patient, doctor, visitType, rs.getTimestamp(5), rs.getTimestamp(6), new Money(),
				visistConfirmed);

		logger.info(visit.toString());

		return visit;

	}

	@Override
	public Visit findById(Long id) {

		Connection con = null;
		PreparedStatement getStatement = null;
		Visit result = null;
		ResultSet rs = null;

		try {
			con = ConnectionOracle.getInstance();
			getStatement = con.prepareStatement(findById);
			getStatement.setLong(1, id);
			logger.info("Select * from VISIT where visit_id = " + id);
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
	public List<Visit> findByQueryObject(QueryObject query) {

		Connection con = null;
		PreparedStatement getStatement = null;
		List<Visit> result = new ArrayList<Visit>();
		ResultSet rs = null;

		try {
			con = ConnectionOracle.getInstance();
			getStatement = con.prepareStatement(query.generateWhereSelectStatement());
			query.setQuery(getStatement);
			logger.info(query.generateWhereSelectStatement());
			rs = getStatement.executeQuery();

			while (rs.next())
				result.add(load(rs));

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

}
