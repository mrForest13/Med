package com.app.transaction.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.dao.IFinder;
import com.app.model.visit.Visit;
import com.app.model.visit.VisitType;
import com.app.query.Criteria;
import com.app.query.QueryObject;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class ShowFreeVisitForPatient extends TransactionScript {

	public ShowFreeVisitForPatient(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void run() throws Exception {

		VisitType visit = new VisitType();

		getRequest().setAttribute("visit", visit);

		
		//QueryObject query = new QueryObject();
		//query.addCriteria(Criteria.notNull("visit_user_pacjent_id"));
		
		List<Visit> visitsList = Registry.visitFinder().getAll();

		getRequest().setAttribute("visitList", visitsList);

	}

}
