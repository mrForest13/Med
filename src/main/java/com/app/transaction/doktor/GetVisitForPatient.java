package com.app.transaction.doktor;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.model.user.Patient;
import com.app.model.user.finder.PatientFinder;
import com.app.model.visit.Visit;
import com.app.model.visit.VisitType;
import com.app.model.visit.finder.VisitFinder;
import com.app.path.PathVariable;
import com.app.query.Criteria;
import com.app.query.QueryObject;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class GetVisitForPatient extends TransactionScript {

	private static final Logger logger = LoggerFactory.getLogger(GetVisitForPatient.class);

	public GetVisitForPatient(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void run() throws Exception {

		Long id = (Long) getRequest().getAttribute("userId");

		VisitType visit = new VisitType();

		getRequest().setAttribute("visit", visit);

		Long userId = PathVariable.getSecendIdFromUrl(getRequest().getRequestURL().toString());
		
		Patient user = Registry.patientFinder().findById(userId);

		if (user != null) {

			Timestamp startSession = new Timestamp(Calendar.getInstance().getTime().getTime());

			QueryObject query = new QueryObject(VisitFinder.TABLENAME);
			query.addCriteria(Criteria.lessThan("visit_date_from", startSession));
			query.addCriteria(Criteria.equalsLong("visit_user_pacjent_id", user.getId()));
			query.addCriteria(Criteria.equalsString("visit_is_confirmed", "Y"));

			List<Visit> visitsList = Registry.visitFinder().findByQueryObject(query).stream()
					.filter(e -> e.getDoctor().getId().equals(id)).collect(Collectors.toList());

			getRequest().setAttribute("visitList", visitsList);

			getRequest().setAttribute("pesel", user.getPesel());

		}

	}

}
