package com.app.model.medical;

import com.app.model.user.User;
import com.app.model.visit.VisitType;
import com.app.registry.Registry;

public class Referal {

	private Long id;
	private User patient;
	private User doctor;
	private VisitType visitType;
	private boolean used;

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
	
	public Referal() {}

	public Referal(Long id, User patient,User doctor, VisitType visitType, boolean used) {
		super();
		this.id = id;
		this.patient = patient;
		this.doctor = doctor;
		this.visitType = visitType;
		this.used = used;
	}

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "Referal [id=" + id + ", patient=" + patient + ", doctor=" + doctor + ", visitType=" + visitType
				+ ", used=" + used + "]";
	}

}
