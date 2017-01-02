package com.app.model.medical;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.app.model.medical.finder.MedicalPrescriptionFinder;
import com.app.model.user.User;

public class MedicalPrescription {

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

		if (medicamentList == null)
			medicamentList = MedicalPrescriptionFinder.getMedicamentsByMedicalPrescriptionId(id);

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

	@Override
	public String toString() {
		return "MedicalPrescription [id=" + id + ", patient=" + patient + ", doctor=" + doctor + ", dateOfIssue="
				+ dateOfIssue + ", additionalRight=" + additionalRight + ", medicamentList=" + medicamentList + "]";
	}
}
