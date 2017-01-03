package com.app.model.visit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import com.app.db.ConnectionOracle;
import com.app.model.user.User;
import com.app.transaction.Money;

public class Visit {

	private static final String updateStatementString = "UPDATE visit Set visit_user_pacjent_id = ?, visit_is_confirmed = ? where visit_id = ?";

	private Long id;
	private User patient;
	private User doctor;
	private VisitType visitType;
	private Timestamp visitDateFrom;
	private Timestamp visitDateTo;
	private Money visitPrice;
	private boolean visistConfirmed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getPatient() {
		return patient;
	}

	public void setPatient(User patient) {
		this.patient = patient;
	}

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}

	public VisitType getVisitType() {
		return visitType;
	}

	public void setVisitType(VisitType visitType) {
		this.visitType = visitType;
	}

	public Timestamp getVisitDateFrom() {
		return visitDateFrom;
	}

	public void setVisitDate(Timestamp visitDateFrom) {
		this.visitDateFrom = visitDateFrom;
	}

	public Money getVisitPrice() {
		return visitPrice;
	}

	public void setVisitPrice(Money visitPrice) {
		this.visitPrice = visitPrice;
	}

	public boolean isVisistConfirmed() {
		return visistConfirmed;
	}

	public void setVisistConfirmed(boolean visistConfirmed) {
		this.visistConfirmed = visistConfirmed;
	}

	public Visit(Long id, User patient, User doctor, VisitType visitType, Timestamp visitDateFrom,
			Timestamp visitDateTo, Money visitPrice, boolean visistConfirmed) {
		super();
		this.id = id;
		this.patient = patient;
		this.doctor = doctor;
		this.visitType = visitType;
		this.visitDateFrom = visitDateFrom;
		this.visitDateTo = visitDateTo;
		this.visitPrice = visitPrice;
		this.visistConfirmed = visistConfirmed;
	}

	@Override
	public String toString() {
		return "Visit [id=" + id + ", patient=" + patient + ", doctor=" + doctor + ", visitType=" + visitType
				+ ", visitDateFrom=" + visitDateFrom + ", visitDateTo=" + visitDateTo + ", visitPrice=" + visitPrice
				+ ", visistConfirmed=" + visistConfirmed + "]";
	}

	public Timestamp getVisitDateTo() {
		return visitDateTo;
	}

	public void setVisitDateTo(Timestamp visitDateTo) {
		this.visitDateTo = visitDateTo;
	}

	public void update() {

		PreparedStatement insertStatement = null;
		Connection con = null;

		try {
			con = ConnectionOracle.getInstance();

			insertStatement = con.prepareStatement(updateStatementString);
			
			if (getPatient() == null)
				insertStatement.setNull(1, Types.NULL);
			else
				insertStatement.setLong(1, getPatient().getId());
			
			insertStatement.setString(2, isVisistConfirmed() ? "Y" : "N");
			insertStatement.setLong(3, getId());
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

}
