package com.app.model.visit;

public class VisitType {
	
	private final String getAll = "select visit_type from visit_type";

	private String visitType;

	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	public VisitType() {
	}

	public VisitType(String visitType) {
		super();
		this.visitType = visitType;
	}

	@Override
	public String toString() {
		return "VisitType [visitType=" + visitType + "]";
	}

}
