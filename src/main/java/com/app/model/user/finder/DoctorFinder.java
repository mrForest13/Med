package com.app.model.user.finder;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.user.Doctor;
import com.app.model.user.User;

public class DoctorFinder extends UserFinder implements IUserFinder<Doctor> {

	private static final Logger logger = LoggerFactory.getLogger(DoctorFinder.class);
	
	public final static int TYPE = 1;
	public static final String TABLENAME = "Uzytkownik_doktor";
	
	public Doctor find(Long id) {
		return (Doctor) abstractFind(id, TABLENAME);
	}
	
	public void load(User user) {
		super.load(user);
		
		Doctor doctor = (Doctor) user;
		
		Load e = new Load() {
			
			@Override
			public void load(ResultSet rs) {
				try {
					doctor.setDoctorId(rs.getLong(1));
					doctor.setSpec(rs.getString(3));
					doctor.setDegree(rs.getString(4));
				} catch (SQLException e) {
					e.printStackTrace();
				}			
			}
		};
		
		findRow(user.getId(), TABLENAME, e);

	}

	@Override
	protected User getUserObject() {
		return new Doctor();
	}

}
