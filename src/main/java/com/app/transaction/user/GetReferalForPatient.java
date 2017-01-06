package com.app.transaction.user;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.medical.Referal;
import com.app.model.medical.finder.ReferalFinder;
import com.app.query.Criteria;
import com.app.query.QueryObject;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class GetReferalForPatient extends TransactionScript {

	private static final Logger logger = LoggerFactory.getLogger(GetReferalForPatient.class);
	
	public GetReferalForPatient(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void run() throws Exception {
		
		Referal referal = new Referal();

		getRequest().setAttribute("referal", referal);

		Long id = (Long) getRequest().getAttribute("userId");

		QueryObject query = new QueryObject(ReferalFinder.TABLENAME);
		query.addCriteria(Criteria.equalsLong("REFERAL_USER_PACJENT_ID", id));

		List<Referal> referalList = Registry.referalFinder().findByQueryObject(query);

		List<Referal> referalListN = referalList.stream().filter(e -> !e.isUsed()).collect(Collectors.toList());

		getRequest().setAttribute("referalListN", referalListN);

		List<Referal> referalListY = referalList.stream().filter(e -> e.isUsed()).collect(Collectors.toList());

		getRequest().setAttribute("referalListY", referalListY);
	}
	
}
