package com.app.med;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.transaction.TransactionScript;
import com.app.transaction.service.BookAppointmentForThePatient;
import com.app.transaction.service.CancelAppointmentForThePatient;
import com.app.transaction.service.ConfirmAppointmentForThePatient;
import com.app.transaction.service.GetVisitsForPatient;

import com.app.transaction.user.GetSearchOptionForVisit;
import com.app.transaction.user.GetVisitForPatient;


@Controller
@RequestMapping("/service")
public class ServiceController {

	private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String showPatient(HttpServletRequest request, HttpServletResponse response) {

		return "patientsearch";
	}

	@RequestMapping(value = "/patient", method = { RequestMethod.POST, RequestMethod.GET })
	public String getPatient(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new GetVisitsForPatient(request, response);
		TransactionScript transactionScript2 = new GetSearchOptionForVisit(request, response);

		transactionScript.run();
		transactionScript2.run();

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
	
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public String bookAppointment(HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes) throws Exception {

		TransactionScript transactionScript = new BookAppointmentForThePatient(request, response,redirectAttributes);

		transactionScript.run();
		
		return "redirect:/service/patient";
	}

}
