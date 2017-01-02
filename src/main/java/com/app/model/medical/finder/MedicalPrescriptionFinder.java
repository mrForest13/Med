package com.app.model.medical.finder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.db.ConnectionOracle;
import com.app.model.medical.MedicalPrescription;
import com.app.model.medical.Medicament;
import com.app.model.user.User;
import com.app.registry.Registry;

public class MedicalPrescriptionFinder {

	private static final Logger logger = LoggerFactory.getLogger(MedicalPrescriptionFinder.class);
	
	private static final String findById = "Select * from recepta where recepta_uzytkownik_uz_id = ?";
	private static final String getMedicaments = "Select * from recepta_lek where recepta_id = ?";
	private static final String getMedicament = "Select * from lek where lek_id = ?";
	
	public List<MedicalPrescription> findByUserId(Long Userid) {
		
		Connection con = null;
		PreparedStatement getStatement = null;
		List<MedicalPrescription> result = new ArrayList<MedicalPrescription>();
		ResultSet rs = null;

		try {
			con = ConnectionOracle.getInstance();
			getStatement = con.prepareStatement(findById);
			getStatement.setLong(1, Userid);
			logger.info("Select * from recepta where recepta_uzytkownik_uz_id = " + Userid);
			rs = getStatement.executeQuery();

			while(rs.next())
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
	
	private MedicalPrescription load(ResultSet rs) throws SQLException {
		
		Long id = rs.getLong(1);
		
		User patient = Registry.patientFinder().find(rs.getLong(2));
		User doctor = Registry.doctorFinder().find(rs.getLong(3));
		Timestamp dateOfIssue = rs.getTimestamp(4);
		boolean additionalRight = rs.getString(5).equals("Y") ? true : false;
		
		MedicalPrescription result = new MedicalPrescription(id, patient, doctor, dateOfIssue, additionalRight);
		
		result.setMedicamentList(null);
		
		return result;
	}
	
	public static Set<Medicament> getMedicamentsByMedicalPrescriptionId(Long id) {
		
		Connection con = null;
		PreparedStatement getStatement = null;
		Set<Medicament> result = new HashSet<Medicament>();
		ResultSet rs = null;

		try {
			con = ConnectionOracle.getInstance();
			getStatement = con.prepareStatement(getMedicaments);
			getStatement.setLong(1, id);
			logger.info("Select * from recepta_lek where recepta_id = " + id);
			rs = getStatement.executeQuery();

			while(rs.next())
				result.add(getMedicamentById(rs.getLong(2)));

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
	
	private static Medicament getMedicamentById(Long id) {
		
		Connection con = null;
		PreparedStatement getStatement = null;
		Medicament result = null;
		ResultSet rs = null;

		try {
			con = ConnectionOracle.getInstance();
			getStatement = con.prepareStatement(getMedicament);
			getStatement.setLong(1, id);
			logger.info("Select * from lek where lek_id " + id);
			rs = getStatement.executeQuery();

			if(rs.next())
				result = new Medicament(rs.getString(2), rs.getInt(3));

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
