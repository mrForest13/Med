package com.app.model.visit;

public class VisitType {

	private String visitType;
	private Long id;

	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	public VisitType() {
	}

	public VisitType(Long id ,String visitType) {
		super();
		this.id = id;
		this.visitType = visitType;
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

}
