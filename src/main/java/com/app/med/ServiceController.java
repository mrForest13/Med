package com.app.med;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.model.user.Patient;
import com.app.model.user.User;
import com.app.model.user.finder.PatientFinder;
import com.app.model.visit.Visit;
import com.app.model.visit.VisitType;
import com.app.model.visit.finder.VisitFinder;
import com.app.path.PathVariable;
import com.app.query.Criteria;
import com.app.query.QueryObject;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;
import com.app.transaction.service.CancelAppointmentForThePatient;
import com.app.transaction.service.ConfirmAppointmentForThePatient;

@Controller
@RequestMapping("/service")
public class ServiceController {

	private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String showPatient(HttpServletRequest request, HttpServletResponse response) {

		return "patientsearch";
	}

	@RequestMapping(value = "/patient", method = { RequestMethod.POST, RequestMethod.GET })
	public String getPatient(HttpServletRequest request, HttpServletResponse response) {

		VisitType visit = new VisitType();

		request.setAttribute("visit", visit);

		Patient user = ((PatientFinder) Registry.patientFinder()).findByPesel(request.getParameter("pesel"));

		if (user != null) {

			Timestamp startSession = new Timestamp(Calendar.getInstance().getTime().getTime());

			QueryObject query = new QueryObject(VisitFinder.TABLE);
			query.addCriteria(Criteria.greaterThan("visit_date_from", startSession));
			query.addCriteria(Criteria.equalsLong("visit_user_pacjent_id", user.getId()));
			query.addCriteria(Criteria.equalsString("visit_is_confirmed", "N"));

			List<Visit> visitsList = Registry.visitFinder().findByQueryObject(query);

			request.setAttribute("visitList", visitsList);

			request.setAttribute("pesel", user.getPesel());

		}

		return "patientsearch";
	}

	@RequestMapping(value = "/cancel/{id}", method = RequestMethod.GET)
	public String cancelAppointment(HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) throws Exception {

		TransactionScript transactionScript = new CancelAppointmentForThePatient(request, response, redirectAttributes);

		transactionScript.run();

		return "redirect:/service/patient";
	}

	@RequestMapping(value = "/confirm/{id}", method = RequestMethod.GET)
	public String confirmAppointment(HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) throws Exception {

		TransactionScript transactionScript = new ConfirmAppointmentForThePatient(request, response,
				redirectAttributes);

		transactionScript.run();

		return "redirect:/service/patient";
	}

}
