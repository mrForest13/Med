package com.app.model.visit.finder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.dao.IFinder;
import com.app.model.user.Doctor;
import com.app.model.user.Patient;
import com.app.model.visit.Visit;
import com.app.model.visit.VisitType;
import com.app.registry.Registry;
import com.app.transaction.Money;

public class VisitFinder implements IFinder<Visit> {

	private static final Logger logger = LoggerFactory.getLogger(VisitFinder.class);

	private final String getAll = "Select * from VISIT";
	
	@Override
	public List<Visit> getAll() {
		
		Connection con = null;
		List<Visit> result = new ArrayList<Visit>();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(getAll);

			while (rs.next())
				result.add(load(rs));

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
	
	private Visit load(ResultSet rs) throws SQLException {
		
		Long id = rs.getLong(1);
		Long userId = rs.getLong(4);
		
		Doctor doctor = Registry.doctorFinder().find(rs.getLong(2));
		
		Patient patient = userId!=null ? Registry.patientFinder().find(userId) : null;
		
		VisitType visitType = Registry.visitTypeFinder().findById(rs.getLong(3));
		
		boolean visistConfirmed = rs.getString(8).equals("N") ? false : true;
		
		Visit visit = new Visit(id, patient, doctor, visitType, rs.getDate(5), new Money(), visistConfirmed);
		
		logger.info(visit.toString());
		
		return visit;
		
	}

	@Override
	public Visit findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}



}
