package com.app.model.lab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.app.db.ConnectionOracle;
import com.app.model.lab.finder.LabFinder;
import com.app.model.visit.Visit;
import com.app.registry.Registry;

public class Lab {

	private static final String insertStatementString = "INSERT INTO SAMPLE values (SAMPLE_SEQ.nextval,?,?,?,?,?,?,?)";
	private static final String updateStatementString = "UPDATE Lab Set lab_is_added = ? where lab_id = ?";

	private Long id;
	private Visit visit;
	private List<Sample> sample;
	private boolean isAdded;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Visit getVisit() {

		if (visit.getDoctor() == null)
			visit = Registry.visitFinder().findById(visit.getId());

		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	public List<Sample> getSample() {

		if (sample == null)
			sample = LabFinder.findByLabId(id);

		return sample;
	}

	public void setSample(List<Sample> sample) {
		this.sample = sample;
	}

	public boolean isAdded() {
		return isAdded;
	}

	public void setAdded(boolean isAdded) {
		this.isAdded = isAdded;
	}

	public Lab(Long id, Visit visit, List<Sample> sample, boolean isAdded) {
		super();
		this.id = id;
		this.visit = visit;
		this.sample = sample;
		this.isAdded = isAdded;
	}

	public Lab() {
	}

	@Override
	public String toString() {
		return "Lab [id=" + id + ", visit=" + visit + ", sample=" + sample + ", isAdded=" + isAdded + "]";
	}

	public void insertSample(Sample sample) {

		PreparedStatement insertStatement = null;
		Connection con = null;

		try {
			con = ConnectionOracle.getInstance();

			insertStatement = con.prepareStatement(insertStatementString, new String[] { "SAMPLE_ID" });
			insertStatement.setLong(1, getId());
			insertStatement.setString(2, sample.getName());
			insertStatement.setString(3, sample.getResult());
			insertStatement.setString(4, sample.getStandardPositive());
			insertStatement.setString(5, sample.getStandardNegative());
			insertStatement.setString(6, sample.getUnit());
			insertStatement.setString(7, sample.getDescription());
			insertStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				insertStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void update() {

		PreparedStatement insertStatement = null;
		Connection con = null;

		try {
			con = ConnectionOracle.getInstance();

			insertStatement = con.prepareStatement(updateStatementString);
			insertStatement.setString(1, isAdded() ? "Y" : "N");
			insertStatement.setLong(2, getId());
			insertStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				insertStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
