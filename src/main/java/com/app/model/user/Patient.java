package com.app.model.user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Patient extends User {

	private static final String insertStatementString  = "INSERT INTO uzytkownik_pacjent values (UZYTKOWNIK_P_SEQ.nextval,?,?,?,?,?,?)";
	
	private Long patientId;
	private String pesel;
	private String email;
	private String phone;
	private Date birthDate;
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

	public Date getbirthDate() {
		return birthDate;
	}

	public void setbirthDate(Date birthDate) {
		this.birthDate = birthDate;
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

	public Patient() {}
	
	public Patient(Long id, int userType, String firstName, String lastName, String login, String password,
			Long patientId, String pesel, String email, String phone, Date birthDate, char gender) {
		super(id, userType, firstName, lastName, login, password);
		this.patientId = patientId;
		this.pesel = pesel;
		this.email = email;
		this.phone = phone;
		this.birthDate = birthDate;
		this.gender = gender;
	}
	
	public void insert() throws Exception {
		super.insert();
		
		PreparedStatement insertStatement = null;
		Connection con = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "root", "root");
			
			insertStatement = con.prepareStatement(insertStatementString, new String[] { "UZYTKOWNIK_PACJENT_ID" });
			insertStatement.setInt(1, getId().intValue());
			insertStatement.setString(2, getPesel());
			insertStatement.setString(3, getEmail());
			insertStatement.setString(4, getPhone());
			insertStatement.setDate(5, getbirthDate());
			insertStatement.setString(6, ""+getGender());
			insertStatement.executeUpdate();
			
			ResultSet rs = insertStatement.getGeneratedKeys();
			
			if (rs.next())
				setPatientId(rs.getLong(1));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nPatient [patientId=" + patientId + ", pesel=" + pesel + ", email=" + email
				+ ", phone=" + phone + ", birthDate=" + birthDate + ", gender=" + gender + "]";
	}
	
	

}
