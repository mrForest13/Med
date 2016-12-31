package com.app.model.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.db.ConnectionOracle;

public class User {

	private static final String insertStatementString = "INSERT INTO uzytkownik values (UZYTKOWNIK_SEQ.nextval,?,?,?,?,?)";
	private static final String updateStatementString = "UPDATE uzytkownik Set uzytkownik_login = ?, uzytkownik_user_password = ? where uzytkownik_uz_id = ?";
	
	private Long id;
	private int userType;
	private String firstName;
	private String lastName;
	private String login;
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public User() {
	}

	public User(Long id, int userType, String firstName, String lastName, String login, String password) {
		super();
		this.id = id;
		this.userType = userType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
	}

	public void insert() throws Exception {

		PreparedStatement insertStatement = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			con = ConnectionOracle.getInstance();

			insertStatement = con.prepareStatement(insertStatementString, new String[] { "UZYTKOWNIK_UZ_ID" });
			insertStatement.setInt(1, getUserType());
			insertStatement.setString(2, getFirstName());
			insertStatement.setString(3, getLastName());
			insertStatement.setString(4, getLogin());
			insertStatement.setString(5, getPassword());
			insertStatement.executeUpdate();

			rs = insertStatement.getGeneratedKeys();

			if (rs.next())
				setId(rs.getLong(1));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				insertStatement.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		
		PreparedStatement insertStatement = null;
		Connection con = null;

		try
		{
			con = ConnectionOracle.getInstance();

			insertStatement = con.prepareStatement(updateStatementString);
			insertStatement.setString(1, getLogin());
			insertStatement.setString(2, getPassword());
			insertStatement.setLong(3, getId());
			insertStatement.executeUpdate();

		}catch(
		Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try {
				insertStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userType=" + userType + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", login=" + login + ", password=" + password + "]";
	}



}
