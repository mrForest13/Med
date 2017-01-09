package com.app.model.medical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.app.db.ConnectionOracle;
import com.app.model.medical.finder.MedicalPrescriptionFinder;
import com.app.model.user.User;

public class MedicalPrescription {

	private static final String insertStatementString = "INSERT INTO recepta values (RECEPTA_SEQ.nextval,?,?,?,?)";
	private static final String insert2StatementString = "INSERT INTO recepta_lek values (RECEPTA_LEK_SEQ.nextval,?,?)";
	
	private Long id;
	private User patient;
	private User doctor;
	private Timestamp dateOfIssue;
	private boolean additionalRight;
	private Set<Medicament> medicamentList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getPatient() {
		return patient;
	}

	public void setPatient(User patirnt) {
		this.patient = patirnt;
	}

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}

	public Timestamp getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(Timestamp dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public boolean isAdditionalRight() {
		return additionalRight;
	}

	public void setAdditionalRight(boolean additionalRight) {
		this.additionalRight = additionalRight;
	}

	public void addMedicament(Medicament med) {
		getMedicamentList().add(med);
	}

	public void removeMedicament(Medicament med) {
		getMedicamentList().remove(med);
	}

	public Set<Medicament> getMedicamentList() {
		return medicamentList;
	}

	public void setMedicamentList(Set<Medicament> medicamentList) {
		this.medicamentList = medicamentList;
	}

	public MedicalPrescription(Long id, User patient, User doctor, Timestamp dateOfIssue, boolean additionalRight) {
		super();
		this.id = id;
		this.patient = patient;
		this.doctor = doctor;
		this.dateOfIssue = dateOfIssue;
		this.additionalRight = additionalRight;
		this.medicamentList = new HashSet<Medicament>();
	}
	
	public MedicalPrescription() {}

	public void insert() {
		
		PreparedStatement insertStatement = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			con = ConnectionOracle.getInstance();

			insertStatement = con.prepareStatement(insertStatementString, new String[] { "RECEPTA_ID" });
			insertStatement.setLong(1, getPatient().getId());
			insertStatement.setLong(2, getDoctor().getId());
			insertStatement.setTimestamp(3, getDateOfIssue());
			insertStatement.setString(4, isAdditionalRight() ? "Y" : "N");
			insertStatement.executeUpdate();

			rs = insertStatement.getGeneratedKeys();

			if (rs.next())
				setId(rs.getLong(1));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				insertStatement.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void insertMedicament(Medicament medicament) {
		
		PreparedStatement insertStatement = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			con = ConnectionOracle.getInstance();

			insertStatement = con.prepareStatement(insert2StatementString, new String[] { "RECEPTA_LEK_ID" });
			insertStatement.setLong(1, medicament.getId());
			insertStatement.setLong(2, getId());
			insertStatement.executeUpdate();

			rs = insertStatement.getGeneratedKeys();

			if (rs.next())
				setId(rs.getLong(1));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				insertStatement.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public String toString() {
		return "MedicalPrescription [id=" + id + ", patient=" + patient + ", doctor=" + doctor + ", dateOfIssue="
				+ dateOfIssue + ", additionalRight=" + additionalRight + ", medicamentList=" + medicamentList + "]";
	}
}
