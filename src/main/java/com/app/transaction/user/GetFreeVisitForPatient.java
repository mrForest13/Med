package com.app.transaction.user;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.visit.Visit;
import com.app.model.visit.VisitType;
import com.app.model.visit.finder.VisitFinder;
import com.app.query.Criteria;
import com.app.query.QueryObject;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class GetFreeVisitForPatient extends TransactionScript {

	private static final Logger logger = LoggerFactory.getLogger(GetFreeVisitForPatient.class);

	public GetFreeVisitForPatient(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void run() throws Exception {

		VisitType visit = new VisitType();

		getRequest().setAttribute("visit", visit);

		Timestamp startSession = new Timestamp(Calendar.getInstance().getTime().getTime());

		QueryObject query = new QueryObject(VisitFinder.TABLE);

		if (getRequest().getParameter("date").isEmpty())
			query.addCriteria(Criteria.greaterThan("visit_date_from", startSession));
		else
			query.addCriteria(
					Criteria.equalsDate("TRUNC(visit_date_from)", Date.valueOf(getRequest().getParameter("date"))));

		query.addCriteria(Criteria.equalsString("visit_is_confirmed", "N"));

		List<Visit> visitsList = Registry.visitFinder().findByQueryObject(query);

		visitsList = visitsList.stream().filter(e -> e.getPatient() == null).collect(Collectors.toList());

		String doctorLastName = getRequest().getParameter("lastName");
		String visitType = getRequest().getParameter("visitType");

		if (!doctorLastName.isEmpty())
			visitsList = visitsList.stream().filter(e -> e.getDoctor().getLastName().equals(doctorLastName))
					.collect(Collectors.toList());

		if (!visitType.isEmpty())
			visitsList = visitsList.stream().filter(e -> e.getVisitType().getVisitType().equals(visitType))
					.collect(Collectors.toList());

		getRequest().setAttribute("visitList", visitsList);

	}

}
