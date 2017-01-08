package com.app.transaction.payment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.model.visit.Visit;
import com.app.path.PathVariable;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;
import com.app.transaction.service.GetVisitsForPatient;

public class SendRequestToPaymentSysystem extends TransactionScript {

	private static final Logger logger = LoggerFactory.getLogger(SendRequestToPaymentSysystem.class);

	private RedirectAttributes redirectAttributes;

	public SendRequestToPaymentSysystem(HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		super(request, response);
		this.redirectAttributes = redirectAttributes;
	}

	@Override
	public void run() throws Exception {

		Long id = PathVariable.getIdFromUrl(getRequest().getRequestURL().toString());

		Visit visit = Registry.visitFinder().findById(id);

		Money money = visit.getVisitPrice();

		PaymentMock paymentMock = new PaymentMock(money);
		
		if(paymentMock.buildAndExecuteRequest(getRequest().getParameter("pesel"))) {
			visit.setVisitPrice(money);
			visit.update();
		}
		
		logger.info(visit.toString());

		redirectAttributes.addAttribute("pesel", getRequest().getParameter("pesel"));

	}

}
