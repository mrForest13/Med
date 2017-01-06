package com.app.model.medical.finder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.dao.IQueryFinder;
import com.app.db.ConnectionOracle;
import com.app.model.medical.Referal;
import com.app.model.user.Doctor;
import com.app.model.user.User;
import com.app.model.visit.VisitType;
import com.app.query.QueryObject;
import com.app.registry.Registry;

public class ReferalFinder implements IQueryFinder<Referal> {

	public static final String TABLENAME = "Referal";
	private static final Logger logger = LoggerFactory.getLogger(ReferalFinder.class);
	
	@Override
	public Referal findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Referal> findByQueryObject(QueryObject query) {
		
		Connection con = null;
		PreparedStatement getStatement = null;
		List<Referal> result = new ArrayList<Referal>();
		ResultSet rs = null;

		try {
			con = ConnectionOracle.getInstance();
			getStatement = con.prepareStatement(query.generateWhereSelectStatement());
			query.setQuery(getStatement);
			logger.info(query.generateWhereSelectStatement());
			rs = getStatement.executeQuery();

			while (rs.next())
				result.add(load(rs));

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

	private Referal load(ResultSet rs) throws SQLException {
		
		Long id = rs.getLong(1);
		
		User user = new User();
		user.setId(rs.getLong(2));
		
		VisitType visitType = Registry.visitTypeFinder().findById(rs.getLong(4));
		
		boolean used = rs.getString(5).equals("Y") ? true : false;
		
		User doctor = Registry.doctorFinder().findById(rs.getLong(3));
		
		Referal result = new Referal(id, user,doctor, visitType, used);
		
		logger.info(result.toString());
		
		return result;
	}

}
