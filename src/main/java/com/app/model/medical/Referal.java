package com.app.model.medical;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.app.db.ConnectionOracle;
import com.app.model.user.User;
import com.app.model.visit.VisitType;
import com.app.registry.Registry;

public class Referal {

	private static final String insertStatementString = "INSERT INTO referal values (REFERAL_SEQ.nextval,?,?,?,?,?)";
	private static final String updateStatementString = "UPDATE referal Set REFERAL_IS_USED = ? where REFERAL_ID = ?";

	private Long id;
	private User patient;
	private User doctor;
	private VisitType visitType;
	private boolean used;
	private Date dateOfissue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getPatient() {

		if (patient.getLogin() == null)
			patient = Registry.patientFinder().findById(patient.getId());

		return patient;
	}

	public void setPatient(User patient) {
		this.patient = patient;
	}

	public VisitType getVisitType() {
		return visitType;
	}

	public void setVisitType(VisitType visitType) {
		this.visitType = visitType;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public Referal() {
	}

	public Referal(Long id, User patient, User doctor, VisitType visitType, boolean used, Date dateOfissue) {
		super();
		this.id = id;
		this.patient = patient;
		this.doctor = doctor;
		this.visitType = visitType;
		this.used = used;
		this.dateOfissue = dateOfissue;
	}

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}

	public void update() {

		PreparedStatement insertStatement = null;
		Connection con = null;

		try {
			con = ConnectionOracle.getInstance();

			insertStatement = con.prepareStatement(updateStatementString);
			insertStatement.setString(1, isUsed() ? "Y" : "N");
			insertStatement.setLong(2, getId());
			insertStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				insertStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insert() {

		PreparedStatement insertStatement = null;
		Connection con = null;

		try {
			con = ConnectionOracle.getInstance();

			insertStatement = con.prepareStatement(insertStatementString, new String[] { "REFERAL_ID" });
			insertStatement.setLong(1, getPatient().getId());
			insertStatement.setLong(2, getDoctor().getId());
			insertStatement.setLong(3, getVisitType().getId());
			insertStatement.setString(4, isUsed() ? "Y" : "N");
			insertStatement.setDate(5, getDateOfissue());
			insertStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				insertStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public boolean equals(Object obj) {
		return ((Referal) obj).getVisitType().equals(this.getVisitType());
	}

	public Date getDateOfissue() {
		return dateOfissue;
	}

	public void setDateOfissue(Date dateOfissue) {
		this.dateOfissue = dateOfissue;
	}

	@Override
	public String toString() {
		return "Referal [id=" + id + ", patient=" + patient + ", doctor=" + doctor + ", visitType=" + visitType
				+ ", used=" + used + ", dateOfissue=" + dateOfissue + "]";
	}

}
