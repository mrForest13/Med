package com.app.model;

public class Patient extends User {

	private String pesel;
	private String email;
	private String phone;
	private String BirthDay;
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

	public String getBirthDay() {
		return BirthDay;
	}

	public void setBirthDay(String birthDay) {
		BirthDay = birthDay;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

}
