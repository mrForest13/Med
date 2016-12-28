package com.app.model.user;

public class Doctor extends User {
	
	private Long doctorId;
	private String spec;
	private String degree;

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	
	public Doctor() {}

	@Override
	public String toString() {
		return super.toString() + "\nDoctor [doctorId=" + doctorId + ", spec=" + spec + ", degree=" + degree + "]";
	}

	
}
