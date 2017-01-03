package com.app.model.session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.app.db.ConnectionOracle;
import com.app.model.user.User;
import com.app.model.user.finder.UserFinder;
import com.app.registry.Registry;

public class Session {

	private static final String insertStatementString = "INSERT INTO uzytkownik_session values (SESSION_SEQ.nextval,?,?,?,?)";
	private static final String updateStatementString = "UPDATE uzytkownik_session Set session_hash = ? , session_is_active = ? , "
			+ "session_date = ? where session_uzytkownik_uz_id = ?";

	private Long id;
	private User user;
	private String sessionHash;
	private Timestamp sessionDate;
	private boolean sessionIsActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {

		if (user.getLogin() == null)
			user = Registry.userFinder().abstractFind(user.getId(), UserFinder.TABLENAME);

		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSessionHash() {
		return sessionHash;
	}

	public void setSessionHash(String sessionHash) {
		this.sessionHash = sessionHash;
	}

	public Timestamp getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(Timestamp sessionDate) {
		this.sessionDate = sessionDate;
	}

	public boolean isSessionIsActive() {
		return sessionIsActive;
	}

	public void setSessionIsActive(boolean sessionIsActive) {
		this.sessionIsActive = sessionIsActive;
	}

	public Session(Long id, User user, String sessionHash, Timestamp sessionDate, boolean sessionIsActive) {
		super();
		this.id = id;
		this.user = user;
		this.sessionHash = sessionHash;
		this.sessionDate = sessionDate;
		this.sessionIsActive = sessionIsActive;
	}

	public Session() {
	}

	@Override
	public String toString() {
		return "Session [id=" + id + ", user=" + user + ", sessionHash=" + sessionHash + ", sessionDate=" + sessionDate
				+ ", sessionIsActive=" + sessionIsActive + "]";
	}

	public void insert() {

		PreparedStatement insertStatement = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			con = ConnectionOracle.getInstance();

			insertStatement = con.prepareStatement(insertStatementString, new String[] { "SESSION_ID" });
			insertStatement.setLong(1, getUser().getId());
			insertStatement.setString(2, getSessionHash());
			insertStatement.setTimestamp(3, getSessionDate());
			insertStatement.setString(4, isSessionIsActive() ? "Y" : "N");
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

		try {
			con = ConnectionOracle.getInstance();

			insertStatement = con.prepareStatement(updateStatementString);
			insertStatement.setString(1, getSessionHash());
			insertStatement.setString(2, isSessionIsActive() ? "Y" : "N");
			insertStatement.setTimestamp(3, getSessionDate());
			insertStatement.setLong(4, getUser().getId());
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
