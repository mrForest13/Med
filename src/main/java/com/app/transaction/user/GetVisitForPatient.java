package com.app.transaction.user;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.model.visit.Visit;
import com.app.model.visit.VisitType;
import com.app.model.visit.finder.VisitFinder;
import com.app.query.Criteria;
import com.app.query.QueryObject;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class GetVisitForPatient extends TransactionScript {

	public GetVisitForPatient(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void run() throws Exception {

		Long id = (Long) getRequest().getAttribute("userId");

		VisitType visit = new VisitType();

		getRequest().setAttribute("visit", visit);

		Timestamp startSession = new Timestamp(Calendar.getInstance().getTime().getTime());

		QueryObject queryActualVisit = new QueryObject(VisitFinder.TABLE);
		queryActualVisit.addCriteria(Criteria.greaterThan("visit_date_from", startSession));
		queryActualVisit.addCriteria(Criteria.equalsLong("visit_user_pacjent_id", id));

		List<Visit> visitsListA = Registry.visitFinder().findByQueryObject(queryActualVisit);

		getRequest().setAttribute("visitListA", visitsListA);

		QueryObject queryNoActualVisit = new QueryObject(VisitFinder.TABLE);
		queryNoActualVisit.addCriteria(Criteria.lessThan("visit_date_from", startSession));
		queryNoActualVisit.addCriteria(Criteria.equalsLong("visit_user_pacjent_id", id));
		queryNoActualVisit.addCriteria(Criteria.equalsString("visit_is_Confirmed", "Y"));

		List<Visit> visitsListN = Registry.visitFinder().findByQueryObject(queryNoActualVisit);

		getRequest().setAttribute("visitListN", visitsListN);
	}

}
