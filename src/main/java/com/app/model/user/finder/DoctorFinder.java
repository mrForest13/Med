package com.app.model.user.finder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.dao.IFinderAll;
import com.app.db.ConnectionOracle;
import com.app.model.user.Doctor;
import com.app.model.user.Patient;
import com.app.model.user.User;

public class DoctorFinder extends UserFinder implements IFinderAll<Doctor> {

	private static final Logger logger = LoggerFactory.getLogger(DoctorFinder.class);

	public final static int TYPE = 1;
	public static final String TABLENAME = "Uzytkownik_doktor";

	private static final String getAll = "Select * from " + TABLENAME;

	@Override
	public Doctor findById(Long id) {
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
	public List<Doctor> getAll() {

		Connection con = null;
		PreparedStatement getStatement = null;
		List<Doctor> result = new ArrayList<Doctor>();
		ResultSet rs = null;

		try {
			con = ConnectionOracle.getInstance();
			getStatement = con.prepareStatement(getAll);
			logger.info(getAll);
			rs = getStatement.executeQuery();

			while(rs.next()) {
				Doctor doctor = new Doctor();
				doctor.setId(rs.getLong(2));
				load(doctor);
				result.add(doctor);
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
		return new Doctor();
	}

}
