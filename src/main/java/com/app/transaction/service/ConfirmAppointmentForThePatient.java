package com.app.transaction.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.model.user.Patient;
import com.app.model.visit.Visit;
import com.app.path.PathVariable;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class ConfirmAppointmentForThePatient extends TransactionScript {

	private static final Logger logger = LoggerFactory.getLogger(ConfirmAppointmentForThePatient.class);
	private RedirectAttributes redirectAttributes;

	public ConfirmAppointmentForThePatient(HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		super(request, response);
		this.redirectAttributes = redirectAttributes;
	}

	@Override
	public void run() throws Exception {

		Long id = PathVariable.getIdFromUrl(getRequest().getRequestURL().toString());

		Visit visit = Registry.visitFinder().findById(id);

		visit.setVisistConfirmed(true);

		logger.info(visit.toString());

		visit.update();
		
		redirectAttributes.addAttribute("pesel", getRequest().getParameter("pesel"));

	}

}
