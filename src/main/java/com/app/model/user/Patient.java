package com.app.model.user;

import java.util.Date;

public class Patient extends User {

	private Long patientId;
	private String pesel;
	private String email;
	private String phone;
	private Date BirthDay;
	private char gender;

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthDay() {
		return BirthDay;
	}

	public void setBirthDay(Date birthDay) {
		BirthDay = birthDay;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	@Override
	public String toString() {
		return super.toString() +"\nPatient [patientId=" + patientId + ", pesel=" + pesel + ", email=" + email + ", phone=" + phone
				+ ", BirthDay=" + BirthDay + ", gender=" + gender + "]";
	}
	
	
}
