package com.app.model.user.finder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.model.user.Doctor;
import com.app.model.user.Patient;
import com.app.model.user.User;

public class PatientFinder extends AbstractUserFinder implements UserFinder<Patient> {

	private final static int type = 0;
	public static String TABLENAME = "Uzytkownik_Pacjent";
	
	public Patient find(Long id) {
		return (Patient) abstractFind(id, TABLENAME);
	}

	@Override
	public int getType() {
		return type;
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

	@Override
	protected User getUserObject() {
		return new Patient();
	}
	
}
