package com.app.model.medical.finder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.dao.IFinderAll;
import com.app.db.ConnectionOracle;
import com.app.model.medical.Medicament;

public class MedicamentFinder implements IFinderAll<Medicament> {

	private final String getAll = "Select * from Lek";
	private final String findById = "Select * from Lek where lek_id = ?";
	
	@Override
	public Medicament findById(Long id) {
		
		Connection con = null;
		PreparedStatement getStatement = null;
		ResultSet rs = null;
		Medicament result = null;

		try {
			con = ConnectionOracle.getInstance();
			getStatement = con.prepareStatement(findById);
			getStatement.setLong(1, id);
			rs = getStatement.executeQuery();

			while (rs.next())
				result = new Medicament(rs.getLong(1), rs.getString(2), rs.getInt(3));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				getStatement.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
		
	}

	@Override
	public List<Medicament> getAll() {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<Medicament> result = new ArrayList<Medicament>();

		try {
			con = ConnectionOracle.getInstance();
			st = con.createStatement();
			rs = st.executeQuery(getAll);

			while (rs.next())
				result.add(new Medicament(rs.getLong(1), rs.getString(2), rs.getInt(3)));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
		
	}

}
