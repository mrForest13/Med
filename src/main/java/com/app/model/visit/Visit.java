package com.app.model.visit;

import java.sql.Date;

import com.app.model.user.User;
import com.app.transaction.Money;

public class Visit {

	private Long id;
	private User patient;
	private User doctor;
	private VisitType visitType;
	private Date visitDate;
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

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
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

	public Visit(Long id, User patient, User doctor, VisitType visitType, Date visitDate, Money visitPrice,
			boolean visistConfirmed) {
		super();
		this.id = id;
		this.patient = patient;
		this.doctor = doctor;
		this.visitType = visitType;
		this.visitDate = visitDate;
		this.visitPrice = visitPrice;
		this.visistConfirmed = visistConfirmed;
	}

	@Override
	public String toString() {
		return "Visit [id=" + id + ", patient=" + patient + ", doctor=" + doctor + ", visitType=" + visitType
				+ ", visitDate=" + visitDate + ", visitPrice=" + visitPrice + ", visistConfirmed=" + visistConfirmed
				+ "]";
	}

}
