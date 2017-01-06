package com.app.transaction;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.model.medical.Referal;
import com.app.model.medical.finder.ReferalFinder;
import com.app.model.visit.Visit;
import com.app.query.Criteria;
import com.app.query.QueryObject;
import com.app.registry.Registry;

public abstract class TransactionScript {

	private HttpServletRequest request; 
	private HttpServletResponse response;

	public TransactionScript(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}
	
	public TransactionScript() {}
	
	public abstract void run() throws Exception;

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
	

	protected List<Visit> checkUserReferal(List<Visit> visitList, Long Userid) {

		QueryObject queryReferal = new QueryObject(ReferalFinder.TABLENAME);
		queryReferal.addCriteria(Criteria.equalsLong("REFERAL_USER_PACJENT_ID", Userid));
		queryReferal.addCriteria(Criteria.equalsString("REFERAL_IS_USED", "N"));

		List<Referal> referalList = Registry.referalFinder().findByQueryObject(queryReferal);

		List<Visit> results = new ArrayList<Visit>();

		for (Visit v : visitList) {
			if (!v.getVisitType().isReferalRequired())
				results.add(v);
			else {
				
				Referal r = new Referal();
				r.setVisitType(v.getVisitType());
				
				if (referalList.contains(r))
					results.add(v);
			}
		}

		return results;
	}
	
}
