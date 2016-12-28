package com.app.model.user.finder;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.user.Doctor;
import com.app.model.user.User;

public class DoctorFinder extends AbstractUserFinder implements UserFinder<Doctor> {

	private static final Logger logger = LoggerFactory.getLogger(DoctorFinder.class);
	
	private final static int type = 1;
	public static String TABLENAME = "Uzytkownik_doktor";
	
	public Doctor find(Long id) {
		return (Doctor) abstractFind(id, TABLENAME);
	}
	
	
	@Override
	public int getType() {
		return type;
	}
	
	public void load(User user) {

		Doctor doctor = (Doctor) user;
		
		Load e = new Load() {
			
			@Override
			public void load(ResultSet rs) {
				try {
					doctor.setId(rs.getLong(2));
					doctor.setDoctorId(rs.getLong(1));
					doctor.setSpec(rs.getString(3));
					doctor.setDegree(rs.getString(4));
				} catch (SQLException e) {
					e.printStackTrace();
				}			
			}
		};
		
		findRow(user.getId(), TABLENAME, e);
		
		super.load(user);
	
	}

	@Override
	protected User getUserObject() {
		return new Doctor();
	}

}
