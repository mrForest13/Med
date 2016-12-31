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

	private static final String getAll = "Select * from VISIT";
	private static final String getByQuery = "Select * from VISIT where ";
	
	@Override
	public List<Visit> getAll() {
		
		Connection con = null;
		PreparedStatement getStatement = null;
		List<Visit> result = new ArrayList<Visit>();
		ResultSet rs = null;

		try {
			con = ConnectionOracle.getInstance();
			getStatement = con.prepareStatement(getAll);
			logger.info("Select * from VISIT");
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
		
		Doctor doctor = Registry.doctorFinder().find(rs.getLong(2));
		
		Patient patient = userId!=null ? Registry.patientFinder().find(userId) : null;
		
		VisitType visitType = Registry.visitTypeFinder().findById(rs.getLong(3));
		
		boolean visistConfirmed = rs.getString(8).equals("N") ? false : true;
		
		Visit visit = new Visit(id, patient, doctor, visitType, rs.getTimestamp(5),rs.getTimestamp(6), new Money(), visistConfirmed);
		
		logger.info(visit.toString());
		
		return visit;
		
	}

	@Override
	public Visit findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visit> findByQueryObject(QueryObject query) {
		
		Connection con = null;
		PreparedStatement getStatement = null;
		List<Visit> result = new ArrayList<Visit>();
		ResultSet rs = null;

		try {
			con = ConnectionOracle.getInstance();
			getStatement = con.prepareStatement(getByQuery + query.generateWhereClause());
			logger.info(getByQuery + query.generateWhereClause());
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
