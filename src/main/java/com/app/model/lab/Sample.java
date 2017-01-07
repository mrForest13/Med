package com.app.model.lab;

public class Sample {

	private String name;
	private String result;
	private String standardPositive;
	private String standardNegative;
	private String unit;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getStandardPositive() {
		return standardPositive;
	}

	public void setStandardPositive(String standardPositive) {
		this.standardPositive = standardPositive;
	}

	public String getStandardNegative() {
		return standardNegative;
	}

	public void setStandardNegative(String standardNegative) {
		this.standardNegative = standardNegative;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Sample(String name, String result, String standardPositive, String standardNegative, String unit,
			String description) {
		super();
		this.name = name;
		this.result = result;
		this.standardPositive = standardPositive;
		this.standardNegative = standardNegative;
		this.unit = unit;
		this.description = description;
	}

	public Sample() {
	}

	@Override
	public String toString() {
		return "Sample [name=" + name + ", result=" + result + ", standardPositive=" + standardPositive
				+ ", standardNegative=" + standardNegative + ", unit=" + unit + ", description=" + description + "]";
	}

}
