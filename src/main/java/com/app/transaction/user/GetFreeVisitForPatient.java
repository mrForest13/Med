package com.app.transaction.user;

import java.sql.Timestamp;
import java.util.ArrayList;
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
		query.addCriteria(Criteria.greaterThan("visit_date_from", startSession));
		query.addCriteria(Criteria.equalsString("visit_is_confirmed", "N"));
		
		Collection<Visit> visitsList = Registry.visitFinder().findByQueryObject(query);
		
		logger.info("Wielkosc listy " + visitsList.size());

		List<Visit> results = visitsList.stream().filter(e -> e.getPatient()==null).collect(Collectors.toList());
		
		getRequest().setAttribute("visitList", results);

	}

}
