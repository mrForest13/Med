package com.app.model.lab.finder;

import java.sql.Connection;
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
import com.app.dao.IUserFinder;
import com.app.db.ConnectionOracle;
import com.app.model.lab.Lab;
import com.app.model.lab.Sample;
import com.app.model.medical.MedicalPrescription;
import com.app.model.medical.finder.MedicalPrescriptionFinder;
import com.app.model.visit.Visit;

public class LabFinder implements IFinder<Lab>, IUserFinder<Lab> {

	private static final Logger logger = LoggerFactory.getLogger(LabFinder.class);
	
	private static final String findByUserId = "Select lab.lab_id, lab.LAB_VISIT_ID, lab.LAB_IS_ADDED from lab INNER JOIN visit ON lab.LAB_VISIT_ID = visit.VISIT_ID "
			+ "where visit.VISIT_USER_PACJENT_ID = ? and visit.VISIT_DATE_TO < ? and visit.VISIT_IS_CONFIRMED = ?";
	
	private static final String findById = "Select lab.lab_id, lab.LAB_VISIT_ID, lab.LAB_IS_ADDED from lab where lab_id = ?";
	private static final String findByLabId = "Select * from Sample where sample_lab_id = ?";
	private static final String findByVisitId = "Select lab.lab_id, lab.LAB_VISIT_ID, lab.LAB_IS_ADDED from lab where lab_visit_id = ?";

	@Override
	public List<Lab> findByUserId(Long Userid) {

		Connection con = null;
		PreparedStatement getStatement = null;
		List<Lab> result = new ArrayList<Lab>();
		ResultSet rs = null;
		
		Timestamp startSession = new Timestamp(Calendar.getInstance().getTime().getTime());

		try {
			con = ConnectionOracle.getInstance();
			getStatement = con.prepareStatement(findByUserId);
			getStatement.setLong(1, Userid);
			getStatement.setTimestamp(2, startSession);
			getStatement.setString(3, "Y");
			logger.info(findByUserId);
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
	
	
	

	private Lab load(ResultSet rs) throws SQLException {

		Long id = rs.getLong(1);
		
		Visit visit = new Visit();
		visit.setId(rs.getLong(2));
		
		boolean isAdded = rs.getString(3).equals("Y") ? true : false; 
		
		Lab result = new Lab(id, visit, null, isAdded);
		
		logger.info(result.toString());
		
		return result;
	}

	@Override
	public Lab findById(Long id) {
		
		Connection con = null;
		PreparedStatement getStatement = null;
		Lab result = null;
		ResultSet rs = null;

		try {
			con = ConnectionOracle.getInstance();
			getStatement = con.prepareStatement(findById);
			getStatement.setLong(1, id);
			logger.info(findById);
			rs = getStatement.executeQuery();

			if(rs.next())
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
	
	public Lab findByVisitId(Long id) {
		
		Connection con = null;
		PreparedStatement getStatement = null;
		Lab result = null;
		ResultSet rs = null;

		try {
			con = ConnectionOracle.getInstance();
			getStatement = con.prepareStatement(findByVisitId);
			getStatement.setLong(1, id);
			logger.info(findByVisitId);
			rs = getStatement.executeQuery();

			if(rs.next())
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
	
	public static List<Sample> findByLabId(Long id) {
		
		Connection con = null;
		PreparedStatement getStatement = null;
		List<Sample> result = new ArrayList<Sample>();
		ResultSet rs = null;

		try {
			con = ConnectionOracle.getInstance();
			getStatement = con.prepareStatement(findByLabId);
			getStatement.setLong(1, id);
			logger.info(findByLabId);
			rs = getStatement.executeQuery();

			while (rs.next())
				result.add(loadLab(rs));

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

	private static Sample loadLab(ResultSet rs) throws SQLException {
		
		String name = rs.getString(3);
		String result = rs.getString(4);
		String standardPositive = rs.getString(5);
		String standardNegative = rs.getString(6);
		String unit = rs.getString(7);
		String description = rs.getString(8);
		
		Sample sample = new Sample(name, result, standardPositive, standardNegative, unit, description);
		
		logger.info(sample.toString());
				
		return sample;
	}

}
