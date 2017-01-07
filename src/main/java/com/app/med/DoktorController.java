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
import com.app.model.user.finder.PatientFinder;
import com.app.model.visit.Visit;
import com.app.model.visit.VisitType;
import com.app.model.visit.finder.VisitFinder;
import com.app.query.Criteria;
import com.app.query.QueryObject;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;
import com.app.transaction.doktor.ChooseAppointment;
import com.app.transaction.user.GetMedicalPrescriptionForPatient;
import com.app.transaction.user.GetReferalForPatient;


@Controller
@RequestMapping("/doktor")
public class DoktorController {

	private static final Logger logger = LoggerFactory.getLogger(DoktorController.class);

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String showPatient(HttpServletRequest request, HttpServletResponse response) {

		return "doktorpatientsearch";
	}
	
	@RequestMapping(value = "/choose/{id}", method = RequestMethod.GET)
	public String chooseAppointment(HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) throws Exception {

		TransactionScript transactionScript = new ChooseAppointment(request, response,
				redirectAttributes);

		transactionScript.run();

		return "redirect:/doktor/prescriptions";
	}

	@RequestMapping(value = "/patient", method = { RequestMethod.POST, RequestMethod.GET })
	public String getPatient(HttpServletRequest request, HttpServletResponse response) {

		VisitType visit = new VisitType();

		request.setAttribute("visit", visit);

		Patient user = ((PatientFinder) Registry.patientFinder()).findByPesel(request.getParameter("pesel"));

		if (user != null) {

			Timestamp startSession = new Timestamp(Calendar.getInstance().getTime().getTime());

			QueryObject query = new QueryObject(VisitFinder.TABLENAME);
			query.addCriteria(Criteria.greaterThan("visit_date_from", startSession));
			query.addCriteria(Criteria.equalsLong("visit_user_pacjent_id", user.getId()));
			//query.addCriteria(Criteria.equalsString("visit_is_confirmed", "Y"));

			List<Visit> visitsList = Registry.visitFinder().findByQueryObject(query);

			request.setAttribute("visitList", visitsList);

			request.setAttribute("pesel", user.getPesel());

		}

		return "doktorpatientsearch";
	}
	
	@RequestMapping(value = "/prescriptions", method = RequestMethod.GET)
	public String getMedicalPrescription(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new GetMedicalPrescriptionForPatient(request, response);

		transactionScript.run();

		return "doktorprescriptions";
	}
	
	@RequestMapping(value = "/referal", method = RequestMethod.GET)
	public String getReferals(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new GetReferalForPatient(request, response);

		transactionScript.run();

		return "doktorshowreferal";
	}

	
	@RequestMapping(value = "/note", method = RequestMethod.GET)
	public String getNotes(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new GetReferalForPatient(request, response);

		transactionScript.run();

		return "doktornote";
	}

	
	@RequestMapping(value = "/examination", method = RequestMethod.GET)
	public String getExaminations(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new GetReferalForPatient(request, response);

		transactionScript.run();

		return "doktorexamination";
	}

}
