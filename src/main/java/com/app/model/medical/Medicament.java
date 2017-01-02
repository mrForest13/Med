package com.app.model.medical;

public class Medicament {

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

	public Medicament(String lekName, int refundPercent) {
		super();
		this.lekName = lekName;
		this.refundPercent = refundPercent;
	}

	@Override
	public boolean equals(Object obj) {
		return ((Medicament) obj).getLekName().equals(this.lekName) ? true : false;
	}

	@Override
	public String toString() {
		return "Medicament [lekName=" + lekName + ", refundPercent=" + refundPercent + "]";
	}

}
