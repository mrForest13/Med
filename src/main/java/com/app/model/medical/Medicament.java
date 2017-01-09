package com.app.model.medical;

public class Medicament {

	private Long id;
	private String lekName;
	private int refundPercent;

	public String getLekName() {
		return lekName;
	}

	public void setLekName(String lekName) {
		this.lekName = lekName;
	}

	public int getRefundPercent() {
		return refundPercent;
	}

	public void setRefundPercent(int refundPercent) {
		this.refundPercent = refundPercent;
	}

	public Medicament(Long id ,String lekName, int refundPercent) {
		super();
		this.id = id;
		this.lekName = lekName;
		this.refundPercent = refundPercent;
	}
	
	public Medicament() {}

	@Override
	public boolean equals(Object obj) {
		return ((Medicament) obj).getLekName().equals(this.lekName) ? true : false;
	}

	@Override
	public String toString() {
		return "Medicament [lekName=" + lekName + ", refundPercent=" + refundPercent + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
