package com.app.transaction.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.user.Patient;
import com.app.model.user.finder.PatientFinder;
import com.app.model.visit.Visit;
import com.app.model.visit.VisitType;
import com.app.model.visit.finder.VisitFinder;
import com.app.query.Criteria;
import com.app.query.QueryObject;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class GetVisitsForPatient extends TransactionScript {

	private static final Logger logger = LoggerFactory.getLogger(GetVisitsForPatient.class);

	public GetVisitsForPatient(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	@Override
	public void run() throws Exception {
	
		VisitType visit = new VisitType();

		getRequest().setAttribute("visit", visit);

		Patient user = ((PatientFinder) Registry.patientFinder()).findByPesel(getRequest().getParameter("pesel"));

		if (user != null) {

			Timestamp startSession = new Timestamp(Calendar.getInstance().getTime().getTime());

			QueryObject query = new QueryObject(VisitFinder.TABLENAME);
			query.addCriteria(Criteria.greaterThan("visit_date_from", startSession));
			query.addCriteria(Criteria.equalsLong("visit_user_pacjent_id", user.getId()));
			query.addCriteria(Criteria.equalsString("visit_is_confirmed", "N"));

			List<Visit> visitsList = Registry.visitFinder().findByQueryObject(query);

			getRequest().setAttribute("visitList", visitsList);

			getRequest().setAttribute("pesel", user.getPesel());
			
			QueryObject queryVisit = new QueryObject(VisitFinder.TABLENAME);

			if (getRequest().getParameter("date")==null || getRequest().getParameter("date").isEmpty())
				queryVisit.addCriteria(Criteria.greaterThan("visit_date_from", startSession));
			else
				queryVisit.addCriteria(
						Criteria.equalsDate("TRUNC(visit_date_from)", Date.valueOf(getRequest().getParameter("date"))));

			queryVisit.addCriteria(Criteria.equalsString("visit_is_confirmed", "N"));

			List<Visit> visitsListR = Registry.visitFinder().findByQueryObject(queryVisit);

			visitsListR = visitsListR.stream().filter(e -> e.getPatient() == null).collect(Collectors.toList());

			String doctorLastName = getRequest().getParameter("lastName");
			String visitType = getRequest().getParameter("visitType");

			if (doctorLastName!=null && !doctorLastName.isEmpty())
				visitsListR = visitsListR.stream().filter(e -> e.getDoctor().getLastName().equals(doctorLastName))
						.collect(Collectors.toList());

			if (visitType!=null && !visitType.isEmpty())
				visitsListR = visitsListR.stream().filter(e -> e.getVisitType().getVisitType().equals(visitType))
						.collect(Collectors.toList());
			
			visitsListR = checkUserReferal(visitsListR, user.getId());

			getRequest().setAttribute("visitListR", visitsListR);

		}
		
	}

}
