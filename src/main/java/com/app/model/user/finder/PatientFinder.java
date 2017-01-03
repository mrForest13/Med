package com.app.model.user.finder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.db.ConnectionOracle;
import com.app.model.user.Doctor;
import com.app.model.user.Patient;
import com.app.model.user.User;

public class PatientFinder extends UserFinder implements IUserFinder<Patient> {

	private static final Logger logger = LoggerFactory.getLogger(PatientFinder.class);
	
	public final static int TYPE = 0;
	public static final String TABLENAME = "Uzytkownik_Pacjent";
	private static final String getByPesel = "Select * from Uzytkownik_pacjent where uzytkownik_pacjent_pesel = ?";
	
	public Patient find(Long id) {
		return (Patient) abstractFind(id, TABLENAME);
	}
	
	public void load(User user) {
		super.load(user);
		Patient patient = (Patient) user;
		
		Load e = new Load() {
			
			@Override
			public void load(ResultSet rs) {
				try {
					patient.setPatientId(rs.getLong(1));
					patient.setPesel(rs.getString(3));
					patient.setEmail(rs.getString(4));
					patient.setPhone(rs.getString(5));
					patient.setbirthDate(rs.getDate(6));
					patient.setGender(rs.getString(7).charAt(0));
				} catch (SQLException e) {
					e.printStackTrace();
				}			
			}
		};
		
		findRow(user.getId(), TABLENAME, e);
	
	}
	
	public Patient findByPesel(String pesel) {
		
		Connection con = null;
		PreparedStatement getStatement = null;
		Patient result = null;
		ResultSet rs = null;

		try {
			con = ConnectionOracle.getInstance();
			getStatement = con.prepareStatement(getByPesel);
			getStatement.setString(1, pesel);
			logger.info("Select * from Uzytkownik_pacjent where uzytkownik_pacjent_pesel = " + pesel);
			rs = getStatement.executeQuery();

			
			
			if (rs.next()){
				result = new Patient();
				result.setId(rs.getLong(2));
				load(result);
			}

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
	protected User getUserObject() {
		return new Patient();
	}
	
}
