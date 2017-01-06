package com.app.model.visit;

public class VisitType {

	private String visitType;
	private Long id;
	private boolean referalRequired;

	public String getVisitType() {
		return visitType;
	}

	public VisitType() {
	}

	public VisitType(Long id ,String visitType, boolean referalRequired) {
		super();
		this.id = id;
		this.visitType = visitType;
		this.referalRequired = referalRequired;
	}

	@Override
	public String toString() {
		return "VisitType [visitType=" + visitType + ", id=" + id + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isReferalRequired() {
		return referalRequired;
	}

	public void setReferalRequired(boolean referalRequired) {
		this.referalRequired = referalRequired;
	}

}
